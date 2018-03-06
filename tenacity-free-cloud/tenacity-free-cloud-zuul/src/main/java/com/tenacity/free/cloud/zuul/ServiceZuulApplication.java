package com.tenacity.free.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @ProjectName: tenacity-free-cloud-zuul
 * @PackageName: org.tenacity.free.cloud.zuul
 * @ClassName: ServiceZuulApplication.java
 * @author: free.zhang
 * @Date: 2018年3月6日 下午4:02:30 
 * @Description: 
 */
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class ServiceZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceZuulApplication.class, args);
	}
	
}
