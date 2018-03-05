package com.tenacity.free.common.email;

import java.util.HashMap;
import java.util.Map;

/**
 * @project_name: tenacity-free-common
 * @package_name: com.tenacity.free.common.email
 * @file_name: SmtpServer.java
 * @author: free.zhang
 * @datetime: 2018年1月13日 下午8:03:56
 * @desc: 常用的SMTP服务器
 */
public interface SmtpServer {

    @SuppressWarnings("serial")
    public static Map<String, String> SMTP_163 = new HashMap<String, String>() {
        {

            put("smtp", "smtp.163.com");
            put("port", "25");

        }
    };

    @SuppressWarnings("serial")
    public static Map<String, String> SMTP_SINA = new HashMap<String, String>() {
        {

            put("smtp", "smtp.sina.com");
            put("port", "25");

        }
    };

    @SuppressWarnings("serial")
    public static Map<String, String> SMTP_SOHU = new HashMap<String, String>() {
        {

            put("smtp", "smtp.sohu.com");
            put("port", "25");

        }
    };

    @SuppressWarnings("serial")
    public static Map<String, String> SMTP_126 = new HashMap<String, String>() {
        {

            put("smtp", "smtp.126.com");
            put("port", "25");

        }
    };

    @SuppressWarnings("serial")
    public static Map<String, String> SMTP_YAHOO = new HashMap<String, String>() {
        {

            put("smtp", "smtp.mail.yahoo.com");
            put("port", "25");

        }
    };

    @SuppressWarnings("serial")
    public static Map<String, String> SMTP_FOXMAIL = new HashMap<String, String>() {
        {

            put("smtp", "smtp.foxmail.com");
            put("port", "25");

        }
    };

}
