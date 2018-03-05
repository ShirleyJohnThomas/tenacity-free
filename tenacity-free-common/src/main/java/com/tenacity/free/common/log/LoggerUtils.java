package com.tenacity.free.common.log;

import com.google.gson.JsonObject;
import com.tenacity.free.common.json.JsonUtils;

import net.sf.json.JSONObject;
import org.slf4j.Logger;

import java.text.MessageFormat;

/**
 * @ProjectName: tenacity-free-common
 * @PackageName: com.tenacity.free.common.util
 * @ClassName: LoggerUtils.java
 * @author: free.zhang
 * @Date: 2018年1月26日 下午2:04:34
 * @Description: 日志记录工具类
 */
public class LoggerUtils {

    private LoggerUtils() {
    }

    /**
     * @param '[logger, className, methodName, message]
     * @return void
     * @class_name LoggerUtils
     * @method loggerErrorInfo
     * @description 记录错误信息
     * @author free.zhang
     * @date 2018/3/2 14:26
     */
    public static void loggerErrorInfo(Logger logger, String className, String methodName, String message) {
        loggerErrorInfo(logger, className, methodName, message, null);
    }

    /**
     * @param logger
     * @param className
     * @param methodName
     * @param message
     * @author: free.zhang
     * @Date: 2018年1月26日 下午2:06:28
     * @Description: 记录异常信息
     * @return: void
     */
    public static void loggerErrorInfo(Logger logger, String className, String methodName, String message, JsonObject paramJson) {
        String content = MessageFormat.format("{0} Class {1} method exception: {2}", className, methodName, message);

        if (null != paramJson) {
            content += " param[ " + JsonUtils.toJSONString(paramJson) + " ]";
        }

        logger.error(content);
    }

    /**
     * @param logger
     * @param className
     * @param methodName
     * @param message
     * @author: free.zhang
     * @Date: 2018年1月26日 下午2:24:48
     * @Description: 记录正常信息
     * @return: void
     */
    public static void loggerInfo(Logger logger, String className, String methodName, String message) {
        loggerInfo(logger, className, methodName, message, null);
    }

    /**
     * @param '[logger, className, methodName, message, objects]
     * @return void
     * @class_name LoggerUtils
     * @method loggerInfo
     * @description 记录正常日志信息
     * @author free.zhang
     * @date 2018/3/2 13:54
     */
    public static void loggerInfo(Logger logger, String className, String methodName, String message, JSONObject paramJson) {
        String content = MessageFormat.format("{0} Class {1} method message: {2}", className, methodName, message);

        if (null != paramJson) {
            content += " param[ " + JsonUtils.toJSONString(paramJson) + " ]";
        }
        logger.info(content);
    }

}
