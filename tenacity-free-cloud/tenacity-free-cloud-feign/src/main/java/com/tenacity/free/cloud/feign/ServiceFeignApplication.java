package com.tenacity.free.cloud.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @ProjectName: tenacity-free-cloud-feign
 * @PackageName: org.tenacity.free.cloud.feign
 * @ClassName: ServiceFeignApplication.java
 * @author: free.zhang
 * @Date: 2018年3月6日 下午3:37:46 
 * @Description: 
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceFeignApplication.class, args);
	}
	
}
