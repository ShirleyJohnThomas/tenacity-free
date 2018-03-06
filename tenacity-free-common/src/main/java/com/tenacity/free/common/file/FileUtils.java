package com.tenacity.free.common.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @ProjectName: tenacity-free-common
 * @PackageName: com.tenacity.free.common.util
 * @ClassName: FileUtils.java
 * @author: free.zhang
 * @Date: 2018年1月22日 上午10:51:57
 * @Description: 文件工具类
 */
public class FileUtils {

	/**
	 * @author: free.zhang
	 * @Date: 2018年3月6日 上午10:11:52
	 * @Description: 创建文件夹：创建成功返回true，否则返回false
	 * @return: boolean
	 * @param filePath
	 * @return
	 */
	public static boolean createDirs(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			return false;
		}
		if (!file.mkdirs()) {
			return false;
		}
		return true;
	}

	/**
	 * @author: free.zhang
	 * @Date: 2018年3月6日 上午10:17:42
	 * @Description: 创建文件:创建成功返回true,创建失败返回false
	 * @return: boolean
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static boolean createFile(String filePath) throws IOException {
		File file = new File(filePath);
		File parentFile = file.getParentFile();
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		if (file.createNewFile()) {
			return false;
		}
		return true;
	}

	/**
	 * @author: free.zhang
	 * @Date: 2018年3月6日 上午10:44:25 
	 * @Description: 一次性读取本地文件内容
	 * @return: void
	 * @param filePath
	 * @throws IOException
	 */
	public static String readToStr(String filePath,String encoding) throws IOException {
		File file = new File(filePath);
		Long fileLength = file.length();
		byte[] filecontent = new byte[fileLength.intValue()];  
		FileInputStream input = new FileInputStream(file);
		input.read(filecontent);
		input.close();
		return new String(filecontent, encoding);
	}
	
	/**
	 * @param buffer
	 *            buffer
	 * @param filePath
	 *            文件路径
	 * @throws IOException
	 *             异常
	 * @author: free.zhang
	 * @Date: 2018年1月22日 上午10:52:42
	 * @Description: 将文本文件中的内容读入到buffer中
	 */
	public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
		InputStream is = new FileInputStream(filePath);
		// 用来保存每行读取的内容
		String line;
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		// 读取第一行
		line = reader.readLine();
		// 如果 line 为空说明读完了
		while (line != null) {
			// 将读到的内容添加到 buffer 中
			buffer.append(line);
			// 添加换行符
			buffer.append("\n");
			// 读取下一行
			line = reader.readLine();
		}
		reader.close();
		is.close();
	}

	/**
	 * @param filePath
	 *            文件所在路径
	 * @return 文本内容
	 * @throws IOException
	 *             异常
	 * @author: free.zhang
	 * @Date: 2018年1月22日 上午10:52:53
	 * @Description: 读取文本文件内容
	 */
	public static String readFile(String filePath) throws IOException {
		StringBuffer sb = new StringBuffer();
		FileUtils.readToBuffer(sb, filePath);
		return sb.toString();
	}

}
