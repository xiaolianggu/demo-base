package com.lagou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class AnnotationConsumerMain  {
    public static void main(String[] args) throws  Exception {
        System.out.println("-------------");
        SpringApplication.run(AnnotationConsumerMain.class, args);
        /*AnnotationConfigApplicationContext   context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();
        // 获取消费者组件
        ComsumerComponet  service = context.getBean(ComsumerComponet.class);
        while(true){
             System.in.read();
             RpcContext.getContext().setAttachment("ip", "127.0.0.3");
             String  hello = service.sayHello("world");
             System.out.println("result:"+hello);
        }*/
    }
    /*@Configuration
    @PropertySource("classpath:/dubbo-consumer.properties")
    @ComponentScan(basePackages = "com.lagou.bean")
    @EnableDubbo
    static  class  ConsumerConfiguration{

    }*/
    
   
}
