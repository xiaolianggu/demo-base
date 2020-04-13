package com.lagou.esper.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.lagou.esper.vo.TPSResult;

public class TP90Listener implements UpdateListener {
	public static final String SQL = "select invokeTime,methodName from TPSEvent.win:time_batch(2 sec) order by methodName,invokeTime asc";
    private List<TPSResult> resultList = new ArrayList<TPSResult>();
	@Override
	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		if(newEvents.length == 0) {
			return;
		}
		resultList = new ArrayList<TPSResult>();
		Map<String,Integer> methodNameCount = new HashMap<String,Integer>();
		for(EventBean eb : newEvents) {
			String methodName = eb.get("methodName").toString();
			if(methodNameCount.get(methodName)==null) {
				methodNameCount.put(methodName, 1);	
			}else {
				methodNameCount.put(methodName, methodNameCount.get(methodName)+1);	
			}
		}
		Map<String,Double> methodNameCountTp90 = new HashMap<String,Double>();
		for(Map.Entry<String,Integer> entry:methodNameCount.entrySet()) {
			methodNameCountTp90.put(entry.getKey(), entry.getValue()*0.9);
		}
		int count = 0;
		String currentMethodName = newEvents[0].get("methodName").toString();
		Map<String,Double> totalTp90 = new HashMap<String,Double>();
		for(EventBean eb : newEvents) {
			String methodName = eb.get("methodName").toString();
			Double invokeTime = Double.parseDouble(eb.get("invokeTime").toString());
			if(!methodName.equals(currentMethodName)) {
				count = 0;
			}
			count++;
			if(count > methodNameCountTp90.get(methodName)) {
				continue;
			}
			if(totalTp90.get(methodName)==null) {
				totalTp90.put(methodName, invokeTime);	
			}else {
				totalTp90.put(methodName, totalTp90.get(methodName)+invokeTime);	
			}
		}
		
		
		for(Map.Entry<String,Double> entry:totalTp90.entrySet()) {
			totalTp90.put(entry.getKey(), entry.getValue()/methodNameCountTp90.get(entry.getKey()));
		}
		
		for(Map.Entry<String,Double> entry:totalTp90.entrySet()) {
			TPSResult result = new TPSResult();
			result.setMethodName(entry.getKey());
			result.setAvgVal(entry.getValue());
			resultList.add(result);
		}
	}

	private void initSchedule() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
		        public void run() {
		            for(TPSResult result:resultList) {
		            	System.out.println("TP90  avg_val:"+result.getAvgVal() + " methodName:"+result.getMethodName());
		            }
		        }
		}, 5000 , 5000);
	}
	
	
	private static class Singleton{
		private static TP90Listener instance;
		static {
			instance = new TP90Listener();
			instance.initSchedule();
		}
		public static TP90Listener getInstance() {
			return instance;
		}
	}
	
	
	public static TP90Listener getInstance() {
		return Singleton.getInstance();
	}
}
