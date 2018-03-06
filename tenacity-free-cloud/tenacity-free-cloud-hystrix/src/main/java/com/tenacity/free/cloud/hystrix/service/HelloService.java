package com.tenacity.free.cloud.hystrix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @ProjectName: tenacity-free-cloud-hystrix
 * @PackageName: org.tenacity.free.cloud.hystrix.service
 * @ClassName: HelloService.java
 * @author: free.zhang
 * @Date: 2018年3月6日 下午3:50:54
 * @Description:
 */
@Service
public class HelloService {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "hiError")
	public String hiService(String name) {
		return restTemplate.getForObject("http://SERVICE-HI/hi?name=" + name, String.class);
	}

	public String hiError(String name) {
		return "hi," + name + ",sorry,error!";
	}
}
