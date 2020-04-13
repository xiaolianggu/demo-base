package com.lagou.filter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;


@Activate(group = {CommonConstants.CONSUMER,CommonConstants.PROVIDER})
public class TransportIPFilter   implements Filter {
	public static ThreadLocal<String> ipLocal = new ThreadLocal<String>();
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
    	RpcContext rpcContext = RpcContext.getContext();
    	 if (rpcContext.isProviderSide()) {
    		 String ip = rpcContext.getAttachment("ip");
    		 System.out.println("client ip:"+ip);
    	 }else {
    		
    	 }
        try {
            // 执行方法
            return  invoker.invoke(invocation);
        } finally {
        }

    }
}
