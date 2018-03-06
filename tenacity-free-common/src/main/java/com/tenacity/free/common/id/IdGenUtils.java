package com.tenacity.free.common.id;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * @project_name: tenacity-free-common
 * @package_name: com.tenacity.free.common.util
 * @file_name: IdGenUtils.java
 * @author: free.zhang
 * @datetime: 2018年1月13日 下午6:17:09
 * @desc: Id生成工具
 */
public class IdGenUtils {

	 private IdGenUtils(){}

	    private static SecureRandom random = new SecureRandom();

	   /**
	    * @author: free.zhang
	    * @datetime: 2018年1月13日 下午6:18:04
	    * @desc: 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.(32位长)
	    * @return
	    */
	    public static String get32Uuid() {
	        return UUID.randomUUID().toString().replaceAll("-", "");
	    }

	    /**
	     * @author: free.zhang
	     * @datetime: 2018年1月13日 下午6:18:13
	     * @desc: 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.(16位长)
	     * @return
	     */
	    public static String get16Uuid(){
	        int machineId = 1;
	        int hashCodeV = UUID.randomUUID().toString().hashCode();
	        if(hashCodeV < 0) {//有可能是负数
	            hashCodeV = - hashCodeV;
	        }
	        return machineId + String.format("%015d", hashCodeV);
	    }

	    /**
	     * @author: free.zhang
	     * @datetime: 2018年1月13日 下午6:18:13
	     * @desc: 使用SecureRandom随机生成Long.
	     * @return
	     */
	    public static long randomLong() {
	        return Math.abs(random.nextLong());
	    }
	
}
