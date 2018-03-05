package com.tenacity.free.common.io;

import java.io.*;
import java.nio.charset.Charset;

import org.apache.commons.io.Charsets;

/**
 * @ProjectName: tenacity-free-common
 * @PackageName: com.tenacity.free.common.util
 * @ClassName: IOUtils.java
 * @author: free.zhang
 * @Date: 2018年1月21日 上午10:29:47
 * @Description: IO操作工具
 */
public class IOUtils {

    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    /**
     * @param closeable
     * @author: free.zhang
     * @Date: 2018年1月21日 上午10:31:13
     * @Description: closeQuietly
     * @return: void
     */
    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }

    /**
     * @param input
     * @return
     * @throws IOException
     * @author: free.zhang
     * @Date: 2018年1月21日 上午10:31:03
     * @Description: InputStream to String utf-8
     * @return: String
     */
    public static String toString(InputStream input) throws IOException {
        return toString(input, Charsets.UTF_8);
    }

    /**
     * @param input
     * @param charset
     * @return
     * @throws IOException
     * @author: free.zhang
     * @Date: 2018年1月21日 上午10:30:55
     * @Description: InputStream to String
     * @return: String
     */
    public static String toString(InputStream input, Charset charset) throws IOException {
        InputStreamReader in = new InputStreamReader(input, charset);
        StringBuffer out = new StringBuffer();
        char[] c = new char[DEFAULT_BUFFER_SIZE];
        for (int n; (n = in.read(c)) != -1; ) {
            out.append(new String(c, 0, n));
        }
        IOUtils.closeQuietly(in);
        IOUtils.closeQuietly(input);
        return out.toString();
    }

    /**
     * @param input
     * @param file
     * @throws IOException
     * @author: free.zhang
     * @Date: 2018年1月21日 上午10:30:46
     * @Description: InputStream to File
     * @return: void
     */
    public static void toFile(InputStream input, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);
        int bytesRead = 0;
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        while ((bytesRead = input.read(buffer, 0, DEFAULT_BUFFER_SIZE)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        IOUtils.closeQuietly(os);
        IOUtils.closeQuietly(input);
    }

}
