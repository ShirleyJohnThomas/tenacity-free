package com.tenacity.free.cloud.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ProjectName: tenacity-free-cloud-feign
 * @PackageName: org.tenacity.free.cloud.feign.service
 * @ClassName: SchedualServiceHi.java
 * @author: free.zhang
 * @Date: 2018年3月6日 下午3:39:57 
 * @Description: 
 */
@FeignClient(value = "service-hi",fallback=SchedualServiceHiHystric.class)
public interface SchedualServiceHi {

	@RequestMapping(value="/hi",method=RequestMethod.GET)
	String sayHiFromClientOne(@RequestParam(value="name") String name);
}
