package com.lagou.filter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;


@Activate(group = {CommonConstants.PROVIDER})
public class TPMonitorFilter   implements Filter {
	public static ThreadLocal<String> ipLocal = new ThreadLocal<String>();
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        try {
            // 执行方法
            return  invoker.invoke(invocation);
        } finally {
        }

    }
}
