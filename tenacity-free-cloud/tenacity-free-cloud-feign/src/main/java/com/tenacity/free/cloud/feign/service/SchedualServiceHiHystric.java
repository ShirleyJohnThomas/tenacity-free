package com.tenacity.free.cloud.feign.service;

import org.springframework.stereotype.Component;

/**
 * @ProjectName: tenacity-free-cloud-feign
 * @PackageName: com.tenacity.free.cloud.feign.service
 * @ClassName: SchedualServiceHiHystric.java
 * @author: free.zhang
 * @Date: 2018年3月6日 下午3:57:04 
 * @Description: 
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {

	/** 
	 * @author: free.zhang
	 * @Date: 2018年3月6日 下午3:57:04 
	 * @Description: 
	 * @param name
	 * @return 		  
	 */
	@Override
	public String sayHiFromClientOne(String name) {

		return "sorry" + name;
	}

}
