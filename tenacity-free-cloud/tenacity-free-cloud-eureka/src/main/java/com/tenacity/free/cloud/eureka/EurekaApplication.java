package com.tenacity.free.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ProjectName: tenacity-free-cloud-eureka
 * @PackageName: org.tenacity.free.cloud.eureka
 * @ClassName: EurekaApplication.java
 * @author: free.zhang
 * @Date: 2018年3月6日 上午11:09:31 
 * @Description: TODO
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {

	/** 
	 * @author: free.zhang
	 * @Date: 2018年3月6日 上午11:09:31 
	 * @Description: TODO 
	 * @return: void
	 * @param args	  
	 */
	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication.class, args);
	}

}
