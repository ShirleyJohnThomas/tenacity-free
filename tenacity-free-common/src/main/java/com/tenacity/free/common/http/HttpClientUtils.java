package com.tenacity.free.common.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.tenacity.free.common.io.IOUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @project_name: tenacity-free-common
 * @package_name: com.tenacity.free.common.util
 * @file_name: HttpClientUtils.java
 * @author: free.zhang
 * @datetime: 2018年1月13日 下午9:43:31
 * @desc: http 请求工具
 */
public class HttpClientUtils extends HttpSSLClient{

    private HttpClientUtils() {
    }

    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 7000;

    static {
        // 设置连接池
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connMgr.setMaxTotal(100);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        requestConfig = configBuilder.build();

    }


    /**
     * @param url
     * @param charset
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月14日 下午10:05:44
     * @desc: 发送 GET 请求（HTTP），不带输入数据
     */
    public static String doGet(String url, Charset charset) {
        return doGet(url, charset, new HashMap<>());
    }

    /**
     * @param url
     * @param charset
     * @param params
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月14日 下午10:05:55
     * @desc: 发送 GET 请求（HTTP），K-V形式
     */
    public static String doGet(String url, Charset charset, Map<String, String> params) {

        url = buildUrl(url, params);
        String result = null;
        HttpClient httpClient = HttpClients.createSystem();
        try {
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                result = IOUtils.toString(instream, charset);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param url
     * @param charset
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月14日 下午10:06:05
     * @desc: 发送 POST 请求（HTTP），不带输入数据
     */
    public static String doPost(String url, String charset) {
        return doPost(url, charset, new HashMap<>());
    }

    /**
     * @param url
     * @param charset
     * @param params
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月14日 下午10:06:14
     * @desc: 发送 POST 请求（HTTP），K-V形式
     */
    public static String doPost(String url, String charset, Map<Object, Object> params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            httpPost.setConfig(requestConfig);
            List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
            for (Map.Entry<Object, Object> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString());
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName(charset)));
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, charset);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * @param url
     * @param charset
     * @param json
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月14日 下午10:02:46
     * @desc: 发送 POST 请求（HTTP），JSON形式
     */
    public static String doPost(String url, String charset, Object json) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(json.toString(), charset);// 解决中文乱码问题
            stringEntity.setContentEncoding(charset);
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, charset);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

}
