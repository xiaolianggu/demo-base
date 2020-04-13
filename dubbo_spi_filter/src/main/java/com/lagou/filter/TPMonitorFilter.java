package com.lagou.filter;


import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

import com.lagou.esper.event.TPSEvent;
import com.lagou.esper.handler.TPSendHandler;

@Activate(group = { CommonConstants.PROVIDER })
public class TPMonitorFilter implements Filter {

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		long startTime = System.currentTimeMillis();
		try {
			// 执行方法
			return invoker.invoke(invocation);
		} finally {
			TPSEvent event = new TPSEvent();
			event.setMethodName(invocation.getMethodName());
			event.setCurrentTime(System.currentTimeMillis());
			Long invokeTime = System.currentTimeMillis() - startTime;
			event.setInvokeTime(Integer.parseInt(invokeTime.toString()));
			TPSendHandler.getInstance().send(event);
		}

	}
}
