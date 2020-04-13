package com.lagou.esper.function;

import java.util.Random;

public class RandomChoose {
	 public static boolean choose(int persent)  
	 {  
		  Random random = new Random();
		  int randomVal = random.nextInt(100);
		  if(randomVal > persent) {
			  return false;
		  }
	 	  return true;  
     }  
}
