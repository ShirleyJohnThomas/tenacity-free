package com.tenacity.free.common.string;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import net.sf.json.JSONObject;

/**
 * @project_name: tenacity-free-common
 * @package_name: com.tenacity.free.common.util
 * @file_name: StringUtils.java
 * @author: free.zhang
 * @datetime: 2018年1月13日 下午6:40:34
 * @desc: 字符串工具类
 */
public class StringUtils {

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:46:19
	 * @desc: 将字符串转为大写
	 * @param str
	 * @return
	 */
	public static String firstCharToUpperCase(String str) {
		if (Character.isUpperCase(str.charAt(0))) {
			return str;
		} else {
			char[] cs = str.toCharArray();
			cs[0] -= 32;
			return String.valueOf(cs);
		}
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:46:10
	 * @desc: 首字母转大写
	 * @param str
	 * @return
	 */
	public static String firstCharToLowerCase(String str) {
		if (Character.isLowerCase(str.charAt(0))) {
			return str;
		} else {
			char[] cs = str.toCharArray();
			cs[0] += 32;
			return String.valueOf(cs);
		}
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:46:02
	 * @desc: 去字符串两端空格
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		return str.trim();
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:45:54
	 * @desc: 字符串判断是否相等
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean equals(String str1, String str2) {
		return str1.equals(str2);
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:45:46
	 * @desc: 字符串判断是否相等,忽略大小写
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean equalsIgnoreCase(String str1, String str2) {
		return str1.equalsIgnoreCase(str2);
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:45:38
	 * @desc: 去掉所有的空格，中文空格，Tab制表符，中文全角空格
	 * @param str
	 * @return
	 */
	public static String trimAll(String str) {
		return str.trim().replace(" ", "").replace(" ", "").replace("	", "").replace("　", "");
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:45:30
	 * @desc: 格式化字符串 如 "字符串%1$s字符串%2$s"
	 * @param format
	 * @param args
	 * @return
	 */
	public static String format(String format, Object... args) {
		return String.format(format, args);
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:45:12
	 * @desc: 将字符串有某种编码转变成另一种编码
	 * @param string
	 * @param originCharset
	 * @param targetCharset
	 * @return
	 */
	public static String encodeString(String string, Charset originCharset, Charset targetCharset) {
		return string = new String(string.getBytes(originCharset), targetCharset);
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:45:04
	 * @desc: URL编码
	 * @param string
	 * @param charset
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String encodeUrl(String string, String charset) {
		if (null != charset && !charset.isEmpty()) {
			try {
				return URLEncoder.encode(string, charset);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return URLEncoder.encode(string);
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:44:55
	 * @desc: URL编码
	 * @param string
	 * @param charset
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String decodeUrl(String string, String charset) {
		if (null != charset && !charset.isEmpty()) {
			try {
				return URLDecoder.decode(string, charset);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return null;
			}
		}
		return URLDecoder.decode(string);
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:44:45
	 * @desc: 一次性判断多个或单个对象为空。
	 * @param objects
	 * @return
	 */
	public static boolean isBlank(Object... objects) {
		Boolean result = false;
		for (Object object : objects) {
			if (object == null || "".equals(object.toString().trim()) || "null".equals(object.toString().trim())
					|| "[null]".equals(object.toString().trim()) || "[]".equals(object.toString().trim())) {
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:44:36
	 * @desc: 一次性判断多个或单个对象不为空。
	 * @param objects
	 * @return
	 */
	public static boolean isNotBlank(Object... objects) {
		return !isBlank(objects);
	}

	public static String checkNullToConvert(Object obj) {
		return StringUtils.isBlank(obj) ? "" : obj.toString();
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:44:20
	 * @desc: 判断一个字符串在数组中存在几个
	 * @param baseStr
	 * @param strings
	 * @return
	 */
	public static int indexOf(String baseStr, String[] strings) {

		if (null == baseStr || baseStr.length() == 0 || null == strings) {
			return 0;

		}

		int i = 0;
		boolean result = false;
		for (String string : strings) {
			result = baseStr.equals(string);
			i = result ? ++i : i;
		}
		return i;
	}

	public static String trimToEmpty(Object str) {
		return (isBlank(str) ? "" : str.toString().trim());
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:44:10
	 * @desc: 将 Strig 进行 BASE64 编码
	 * @param str
	 * @param bf
	 * @return
	 */
	public static String getBASE64(String str, boolean... bf) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		Encoder encoder = Base64.getEncoder();
		String base64 = new String(encoder.encode(str.getBytes()));
		// 去掉 '='
		if (isBlank(bf) && bf[0]) {
			base64 = base64.replaceAll("=", "");
		}
		return base64;
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:43:56
	 * @desc: 将 BASE64 编码的字符串 s 进行解码
	 * @param s
	 * @return
	 */
	public static String getStrByBASE64(String s) {
		if (isBlank(s))
			return "";
		Decoder decoder = Base64.getDecoder();
		try {
			byte[] b = decoder.decode(s);
			return new String(b);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:43:44
	 * @desc:把Map转换成get请求参数类型,如 {"name"=20,"age"=30} 转换后变成 name=20&age=30
	 * @param map
	 * @return
	 */
	public static String mapToGet(Map<? extends Object, ? extends Object> map) {
		String result = "";
		if (map == null || map.size() == 0) {
			return result;
		}
		Set<? extends Object> keys = map.keySet();
		for (Object key : keys) {
			result += ((String) key + "=" + (String) map.get(key) + "&");
		}

		return isBlank(result) ? result : result.substring(0, result.length() - 1);
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:43:23
	 * @desc: 把一串参数字符串,转换成Map 如"?a=3&b=4" 转换为Map{a=3,b=4}
	 * @param args
	 * @return
	 */
	public static Map<String, ? extends Object> getToMap(String args) {
		if (isBlank(args)) {
			return null;
		}
		args = args.trim();
		// 如果是?开头,把?去掉
		if (args.startsWith("?")) {
			args = args.substring(1, args.length());
		}
		String[] argsArray = args.split("&");

		Map<String, Object> result = new HashMap<String, Object>();
		String[] keyValue = null;
		String key = null;
		String value = null;
		for (String ag : argsArray) {
			if (!isBlank(ag) && ag.indexOf("=") > 0) {

				keyValue = ag.split("=");
				// 如果value或者key值里包含 "="号,以第一个"="号为主 ,如 name=0=3
				// 转换后,{"name":"0=3"}, 如果不满足需求,请勿修改,自行解决.

				key = keyValue[0];
				value = "";
				for (int i = 1; i < keyValue.length; i++) {
					value += keyValue[i] + "=";
				}
				value = value.length() > 0 ? value.substring(0, value.length() - 1) : value;
				result.put(key, value);

			}
		}

		return result;
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:43:07
	 * @desc: 转换成Unicode
	 * @param str
	 * @return
	 */
	public static String toUnicode(String str) {
		String as[] = new String[str.length()];
		String s1 = "";
		for (int i = 0; i < str.length(); i++) {
			as[i] = Integer.toHexString(str.charAt(i) & 0xffff);
			s1 = s1 + "\\u" + as[i];
		}
		return s1;
	}

	public static String getDoubleTOString(Double str) {
		String money = str.toString();
		try {
			Double.parseDouble(money);
		} catch (Exception e) {
			BigDecimal bDecimal = new BigDecimal(str);
			money = bDecimal.toPlainString();
		}
		return money;

	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:42:55
	 * @desc: 把数组转换成Set 方便判断
	 * @param objs
	 * @return
	 */
	public static TreeSet<String> arrayToSet(String[] objs) {
		TreeSet<String> result = new TreeSet<String>();
		if (null == objs) {
			return result;
		}
		for (String obj : objs) {
			result.add(obj);
		}
		return result;
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:42:39
	 * @desc: 字符串转urlcode
	 * @param value
	 * @return
	 */
	public static String strToUrlcode(String value) {
		try {
			value = URLEncoder.encode(value, "utf-8");
			return value;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:42:28
	 * @desc: urlcode转字符串
	 * @param value
	 * @return
	 */
	public static String urlcodeToStr(String value) {
		try {
			value = URLDecoder.decode(value, "utf-8");
			return value;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:42:16
	 * @desc: 设置JSON字符串返回
	 * @param key
	 * @param value
	 * @return
	 */
	public static String setJsonString(String key, String value) {

		if (StringUtils.isNotBlank(key, value)) {

			JSONObject goodsJson = new JSONObject();
			goodsJson.put(key, value);
			return goodsJson.toString();
		}
		return "";
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:42:02
	 * @desc: 替换字符串
	 * @param str
	 * @param nowStr
	 * @param replaceStr
	 * @return
	 */
	public static String replaceString(String str, String nowStr, String replaceStr) {

		nowStr = StringUtils.isBlank(nowStr) ? "" : nowStr;
		replaceStr = StringUtils.isBlank(replaceStr) ? "" : replaceStr;

		if (StringUtils.isNotBlank(str)) {

			return str.replaceAll(nowStr, replaceStr);
		}
		return "";
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:41:37
	 * @desc: 对象转换为字节数组
	 * @param str
	 * @return
	 */
	public static byte[] getBytes(String str) {
		if (str != null) {
			try {
				return str.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:41:28
	 * @desc: 字节数组转换为对象
	 * @param bytes
	 * @return
	 */
	public static String toString(byte[] bytes) {
		try {
			return new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:41:10
	 * @desc: 将指定对象转换成字符串
	 * @param obj
	 * @return
	 */
	public static String toString(Object obj) {
		StringBuffer buffer = new StringBuffer();
		if (obj != null) {
			buffer.append(obj);
		}
		return buffer.toString();
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:41:02
	 * @desc: 将指定字符串首字母转换成大写字母
	 * @param str
	 * @return
	 */
	public static String firstCharUpperCase(String str) {
		StringBuffer buffer = new StringBuffer(str);
		if (buffer.length() > 0) {
			char c = buffer.charAt(0);
			buffer.setCharAt(0, Character.toUpperCase(c));
		}
		return buffer.toString();
	}

}
