package com.tenacity.free.common.response;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import com.tenacity.free.common.exception.ExceptionUtils;
import com.tenacity.free.common.log.LoggerUtils;

/**
 * @project_name: tenacity-free-common
 * @package_name: com.tenacity.free.common.util
 * @file_name: ResultUtils.java
 * @author: free.zhang
 * @datetime: 2018年1月13日 下午4:59:09
 * @desc: 返回结果封装
 */
public class ResultUtils {

    /**
     * @param logger
     * @param throwable
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午5:51:01
     * @desc: 异常信息代码块
     */
    public static Map<String, Object> resultErrorMessage(Logger logger, String className, String methodName, String status, String message,
                                                         Throwable throwable) {
        Map<String, Object> resultJson = new HashMap<>(4);
        resultJson.put("status", status);
        resultJson.put("msg", message);
        resultJson.put("excepitonMsg", ExceptionUtils.getTrace(throwable));

        LoggerUtils.loggerErrorInfo(logger, className, methodName, resultJson.toString());
        throwable.printStackTrace();
        return resultJson;
    }

    /**
     * @param '[logger, className, methodName, throwable]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @class_name ResultUtils
     * @method resultErrorMessage
     * @description 捕获Exception异常记录
     * @author free.zhang
     * @date 2018/3/2 14:31
     */
    public static Map<String, Object> resultErrorMessage(Logger logger, String className, String methodName, Throwable throwable) {
        return resultErrorMessage(logger, className, methodName, "50003", "系统异常", throwable);
    }

    /**
     * @param logger
     * @param status
     * @param message
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午5:50:46
     * @desc: 错误信息代码
     */
    public static Map<String, Object> resultErrorMessage(Logger logger, String className, String methodName,
                                                         String status, String message) {
        Map<String, Object> resultJson = new HashMap<>(3);
        resultJson.put("status", status);
        resultJson.put("msg", message);
        LoggerUtils.loggerErrorInfo(logger, className, methodName, resultJson.toString());
        return resultJson;
    }

    /**
     * @param logger
     * @param message
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午5:50:11
     * @desc: 操作成功返回
     */
    public static Map<String, Object> resultSuccessMessage(Logger logger, String className, String methodName,
                                                           String message) {

        return resultSuccessMessage(logger, className, methodName, null, message);
    }

    /**
     * @param logger
     * @param object
     * @param message
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午5:50:28
     * @desc: 操作成功返回(带返回数据)
     */
    public static Map<String, Object> resultSuccessMessage(Logger logger, String className, String methodName,
                                                           Object object, String message) {
        Map<String, Object> resultJson = new HashMap<>(4);
        resultJson.put("status", 1);
        resultJson.put("msg", message);
        if (null != object) {
            resultJson.put("data", object);
        }
        LoggerUtils.loggerInfo(logger, className, methodName, resultJson.toString());
        return resultJson;
    }

}
