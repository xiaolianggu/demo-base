package com.lagou.esper.handler;

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

public class TPSendHandler {
	private EPRuntime er = null;
    public  void send(TPSEvent event) {
    	er.sendEvent(event);
    }
    
    private void init() {
    	/* 设置配置信息 */
        Configuration config = new Configuration();
        config.addEventType("TPSEvent", TPSEvent.class); //添加事件类型定义
        config.addImport(RandomChoose.class);
        /* 创建引擎实例 */
        EPServiceProvider provider = EPServiceProviderManager.getDefaultProvider(config);
        
        /* 创建statement的管理接口实例 */
        EPAdministrator admin = provider.getEPAdministrator();
        
        TP90Listener tP90Listener = TP90Listener.getInstance();
        EPStatement statement = admin.createEPL(tP90Listener.SQL); //创建EPL查询语句实例，功能：查询所有进入的myEvent事件
        TP99Listener tP99Listener = TP99Listener.getInstance();
        statement.addListener(tP90Listener);
        statement.addListener(tP99Listener);
        er = provider.getEPRuntime();
    }

	private static class Singleton{
		private static TPSendHandler instance;
		static {
			instance = new TPSendHandler();
			instance.init();
		}
		public static TPSendHandler getInstance() {
			return instance;
		}
	}
	
	
	public static TPSendHandler getInstance() {
		return Singleton.getInstance();
	}
}
