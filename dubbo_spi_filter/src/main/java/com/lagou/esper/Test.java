package com.lagou.esper;

import java.util.Random;

import com.lagou.esper.event.TPSEvent;
import com.lagou.esper.handler.TPSendHandler;

public class Test {
   public static void main(String[] args) {
       
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
    	   TPSendHandler.getInstance().send(event);
       }
      
  }
}
