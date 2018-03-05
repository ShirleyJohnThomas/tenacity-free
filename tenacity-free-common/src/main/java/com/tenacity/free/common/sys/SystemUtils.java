package com.tenacity.free.common.sys;

/**
 * @project_name: tenacity-free-common
 * @package_name: com.tenacity.free.common.util
 * @file_name: SystemUtils.java
 * @author: free.zhang
 * @datetime: 2018年1月13日 下午6:46:58
 * @desc: 操作系统判断工具类
 */
public class SystemUtils {

	/**
	 * 获取系统名称并转为大写
	 **/
	private static final String osname = System.getProperty("os.name").toLowerCase();

	/**
	 * @return
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:47:55
	 * @desc: 判断是否是Linux系统
	 */
	public static boolean isLinux() {
		if (osname.indexOf("linux") >= 0) {
			return true;
		}
		return false;
	}

	/**
	 * @return
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:47:47
	 * @desc: 判断是否是windows系统
	 */
	public static boolean isWindows() {
		if (osname.indexOf("windows") >= 0) {
			return true;
		}
		return false;
	}

	/**
	 * @return
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:47:27
	 * @desc: 判断是否是mac系统
	 */
	public static boolean isMac() {
		if (osname.indexOf("mac") >= 0) {
			return true;
		}
		return false;
	}

	/**
	 * @return
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:47:35
	 * @desc: 返回系统名称
	 */
	public static String getOSName() {
		return osname;
	}

}
