package com.lagou.esper;

import java.util.Random;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.lagou.esper.event.TPSEvent;
import com.lagou.esper.function.RandomChoose;
import com.lagou.esper.listener.TP90Listener;
import com.lagou.esper.listener.TP99Listener;

public class Test {
   public static void main(String[] args) {
	   /* 设置配置信息 */
       Configuration config = new Configuration();
       config.addEventType("TPSEvent", TPSEvent.class); //添加事件类型定义
       config.addImport(RandomChoose.class);
       /* 创建引擎实例 */
       EPServiceProvider provider = EPServiceProviderManager.getDefaultProvider(config);
       
       /* 创建statement的管理接口实例 */
       EPAdministrator admin = provider.getEPAdministrator();
       //admin.createEPL("create schema myEvent as com.esper.test.helloesper.MyEvent");
       
       TP90Listener tP90Listener = TP90Listener.getInstance();
       EPStatement statement = admin.createEPL(tP90Listener.SQL); //创建EPL查询语句实例，功能：查询所有进入的myEvent事件
       TP99Listener tP99Listener = TP99Listener.getInstance();
       statement.addListener(tP90Listener);
       statement.addListener(tP99Listener);
       EPRuntime er = provider.getEPRuntime();
       
       for(int i=0;i<10000;i++) {
    	   try {
               Thread.sleep(100);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
    	   TPSEvent event = new TPSEvent();
    	   if(i%2==0) {
    		   event.setMethodName("methodA");
    	   }else {
    		   event.setMethodName("methodB");
    	   }
    	   Random random = new Random();
    	   event.setCurrentTime(System.currentTimeMillis());
    	   event.setInvokeTime(random.nextInt(50));
    	   er.sendEvent(event);  //发送事件
       }
      
  }
}
