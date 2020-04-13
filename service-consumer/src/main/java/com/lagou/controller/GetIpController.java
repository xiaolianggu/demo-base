package com.lagou.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.rpc.RpcContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lagou.bean.ComsumerComponet;

@RequestMapping("/api")
@RestController
public class GetIpController {
	@Autowired
	private ComsumerComponet comsumerComponet;
	@RequestMapping(value = "/getIp", method = RequestMethod.GET)
	public Object getIp(HttpServletRequest httpServletRequest) throws IOException {
		comsumerComponet.sayHello("xixi",getIpAddress(httpServletRequest));
		return "success";
	}
	
	@RequestMapping(value = "/testTp", method = RequestMethod.GET)
	public Object testTp(HttpServletRequest httpServletRequest) throws IOException {
		comsumerComponet.testTp();
		return "success";
	}
	
	 public final static String getIpAddress(HttpServletRequest request) throws IOException {
	        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
	        String ip = request.getHeader("X-Forwarded-For");
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	                ip = request.getHeader("Proxy-Client-IP");
	            }
	            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	                ip = request.getHeader("WL-Proxy-Client-IP");
	            }
	            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	                ip = request.getHeader("HTTP_CLIENT_IP");
	            }
	            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	            }
	            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	                ip = request.getRemoteAddr();
	            }
	        } else if (ip.length() > 15) {
	            String[] ips = ip.split(",");
	            for (int index = 0; index < ips.length; index++) {
	                String strIp = (String) ips[index];
	                if (!("unknown".equalsIgnoreCase(strIp))) {
	                    ip = strIp;
	                    break;
	                }
	            }
	        }
	        return ip;
	    }
}
