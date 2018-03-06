package com.tenacity.free.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @ProjectName: tenacity-free-cloud-config
 * @PackageName: com.tenacity.free.cloud.config
 * @ClassName: ConfigServerApplication.java
 * @author: free.zhang
 * @Date: 2018年3月6日 下午4:11:31 
 * @Description:
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

	/** 
	 * @author: free.zhang
	 * @Date: 2018年3月6日 下午4:11:31 
	 * @Description: 
	 * @return: void
	 * @param args	  
	 */
	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
