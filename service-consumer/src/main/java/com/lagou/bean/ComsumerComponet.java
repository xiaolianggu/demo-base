package com.lagou.bean;

import com.lagou.service.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Component;

@Component
public class ComsumerComponet {
    @Reference
    private HelloService  helloService;
    public String  sayHello(String name,String ip){
    	RpcContext.getContext().setAttachment("ip",ip);
        return  helloService.sayHello(name);
    }

    
    public void testTp() {
    	for(int i=0;i<100;i++) {
    		helloService.methodA();
    		helloService.methodB();
    		helloService.methodC();
    	}
    }
}
