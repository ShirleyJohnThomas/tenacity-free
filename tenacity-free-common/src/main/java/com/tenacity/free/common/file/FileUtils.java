package com.tenacity.free.common.file;

import java.io.BufferedReader;
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
