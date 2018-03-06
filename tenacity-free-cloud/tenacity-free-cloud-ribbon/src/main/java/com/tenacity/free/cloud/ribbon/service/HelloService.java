package com.tenacity.free.cloud.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @ProjectName: tenacity-free-cloud-ribbon
 * @PackageName: org.tenacity.free.cloud.ribbon.service
 * @ClassName: HelloService.java
 * @author: free.zhang
 * @Date: 2018年3月6日 下午3:29:42 
 * @Description: 
 */
@Service
public class HelloService {

	@Autowired
	private RestTemplate restTemplate;
	
	public String hiService(String name) {
        return restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);
    }
	
}
