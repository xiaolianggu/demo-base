package com.lagou.service.impl;

import java.util.Random;

import org.apache.dubbo.config.annotation.Service;

import com.lagou.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService {
	@Override
	public String sayHello(String name) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "hello:" + name;
	}

	@Override
	public String methodA() {
		Random random = new Random();
		try {
			Thread.sleep(random.nextInt(100));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "hello:methodA";
	}

	@Override
	public String methodB() {
		Random random = new Random();
		try {
			Thread.sleep(random.nextInt(100));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "hello:methodB";
	}

	@Override
	public String methodC() {
		Random random = new Random();
		try {
			Thread.sleep(random.nextInt(100));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "hello:methodC";
	}
}
