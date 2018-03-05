package com.tenacity.free.common.list;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @project_name: tenacity-free-common
 * @package_name: com.tenacity.free.common.list
 * @file_name: ListUtils.java
 * @author: free.zhang
 * @datetime: 2018年1月13日 下午7:29:56
 * @desc: list工具类
 */
public class ListUtils {

    /**
     * @param list
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:31:34
     * @desc: 判断List是否为空
     */
    public static <T> boolean isEmpty(List<T> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * @param list
     * @param t
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:31:27
     * @desc: 判断List中是否含有某个对象
     */
    public static <T> boolean has(List<T> list, T t) {
        if (list == null || list.size() == 0) {
            return false;
        }
        if (list.contains(t)) {
            return true;
        }
        return false;
    }

    /**
     * @param list
     * @param index
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:31:20
     * @desc: 根据Index获取List中的元素
     */
    public static <T> T get(List<T> list, int index) {
        if (isEmpty(list) || index < 0 || index >= list.size()) {
            return null;
        }
        return list.get(index);
    }

    /**
     * @param list
     * @param index
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:31:12
     * @desc: 根据Index移除某个元素
     */
    public static <T> List<T> remove(List<T> list, int index) {
        if (isEmpty(list)) {
            return null;
        }
        if (index < 0 || index >= list.size()) {
            return list;
        }
        list.remove(index);
        return list;
    }

    /**
     * @param list
     * @param array
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:31:03
     * @desc: List转数组
     */
    public static <T> T[] asArray(List<T> list, T[] array) {
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.toArray(array);
    }

    /**
     * @param list
     * @param c
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:30:52
     * @desc: 排序
     */
    public static <T> List<T> sort(List<T> list, Comparator<T> c) {
        Collections.sort(list, c);
        return list;
    }

    /**
     * @param list
     * @return
     * @throws Exception
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:30:33
     * @desc: 将list转换为字符串
     */
    public static <T> String toString(List<T> list) throws Exception {
        StringBuffer sb = new StringBuffer("");
        if (isEmpty(list)) {
            return sb.toString();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int i = 0;
        for (T t : list) {
            if ((t instanceof Integer) || (t instanceof Long) || (t instanceof Short) || (t instanceof Boolean) || (t instanceof Byte) || (t instanceof String) || (t instanceof Character) || (t instanceof Float) || (t instanceof Double) || (t instanceof Date)) {
                if (t instanceof Date) {
                    sb.append((i++ == 0) ? sdf.format(t) : ("," + sdf.format(t)));
                } else {
                    sb.append((i++ == 0) ? t.toString() : ("," + t.toString()));
                }
            } else {
                try {
                    throw new Exception("List.toString()方法仅支持Integer,Short,Long,Boolean,Byte,String,Character,Float,Double,Date");
                } catch (Exception e) {
                    e.printStackTrace();
                    return sb.toString();
                }

            }
        }

        return sb.toString();
    }

}
