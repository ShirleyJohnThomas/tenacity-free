package com.tenacity.free.common.list;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @project_name: tenacity-free-common
 * @package_name: com.tenacity.free.common.list
 * @file_name: ArrayUtils.java
 * @author: free.zhang
 * @datetime: 2018年1月13日 下午7:25:11
 * @desc: 数组工具
 */
public class ArrayUtils {

    /**
     * @param array
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:25:58
     * @desc: 判断数组是否为空
     */
    public static <T> boolean isEmpty(T[] array) {
        if (array == null || array.length == 0)
            return true;
        return false;
    }

    /**
     * @param array
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:26:05
     * @desc: 获取数组长度
     */
    public static <T> int length(T[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        return array.length;
    }

    /**
     * @param array
     * @param t
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:26:14
     * @desc: 判断数组是否含有某个元素
     */
    public static <T> boolean contains(T[] array, T t) {
        if (length(array) == 0) {
            return false;
        }
        for (T _t : array) {
            if (_t == t) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param array
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:26:24
     * @desc: 数组转List
     */
    public static <T> List<T> asList(T[] array) {
        return Arrays.asList(array);
    }

    /**
     * @param array
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:26:34
     * @desc: 排序 min-max
     */
    public static int[] sort(int[] array) {
        Arrays.sort(array);
        return array;
    }

    /**
     * @param array
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:26:50
     * @desc: 排序 min-max
     */
    public static char[] sort(char[] array) {
        Arrays.sort(array);
        return array;
    }

    /**
     * @param array
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:26:58
     * @desc: 排序 min-max
     */
    public static double[] sort(double[] array) {
        Arrays.sort(array);
        return array;
    }

    /**
     * @param array
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:27:06
     * @desc: 排序 min-max
     */
    public static float[] sort(float[] array) {
        Arrays.sort(array);
        return array;
    }

    /**
     * @param array
     * @param caseInsensitive
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:27:14
     * @desc: 排序 min-max
     */
    public static String[] sort(String[] array, boolean caseInsensitive) {
        if (caseInsensitive) {
            Arrays.sort(array);
        } else {
            Arrays.sort(array, String.CASE_INSENSITIVE_ORDER);
        }
        return array;
    }

    /**
     * @param array
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:27:22
     * @desc: 排序 min-max
     */
    public static byte[] sort(byte[] array) {
        Arrays.sort(array);
        return array;
    }

    /**
     * @param array
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:27:29
     * @desc: 排序 min-max
     */
    public static short[] sort(short[] array) {
        Arrays.sort(array);
        return array;
    }

    /**
     * @param array
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:27:37
     * @desc: 排序 min-max
     */
    public static Object[] sort(Object[] array) {
        Arrays.sort(array);
        return array;
    }

    /**
     * @param array
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:27:48
     * @desc: 排序 min-max
     */
    public static Integer[] sort(Integer[] array) {
        Arrays.sort(array);
        return array;
    }

    /**
     * @param array
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:27:58
     * @desc: 排序 min-max
     */
    public static Character[] sort(Character[] array) {
        Arrays.sort(array);
        return array;
    }

    /**
     * @param array
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:28:05
     * @desc: 排序 min-max
     */
    public static Double[] sort(Double[] array) {
        Arrays.sort(array);
        return array;
    }

    /**
     * @param array
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:28:16
     * @desc: 排序 min-max
     */
    public static Float[] sort(Float[] array) {
        Arrays.sort(array);
        return array;
    }

    /**
     * @param array
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:28:24
     * @desc: 排序 min-max
     */
    public static Byte[] sort(Byte[] array) {
        Arrays.sort(array);
        return array;
    }

    /**
     * @param array
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:28:34
     * @desc: 排序 min-max
     */
    public static Short[] sort(Short[] array) {
        Arrays.sort(array);
        return array;
    }

    public static <T> T[] sort(T[] array, Comparator<T> c) {
        Arrays.sort(array, c);
        return array;
    }

    /**
     * @param array
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:28:47
     * @desc: 数组转String
     */
    public static <T> String toString(T[] array) {
        StringBuffer sb = new StringBuffer("");
        if (isEmpty(array)) {
            return sb.toString();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int i = 0;
        for (T t : array) {
            if ((t instanceof Integer) || (t instanceof Long) || (t instanceof Short) || (t instanceof Boolean)
                    || (t instanceof Byte) || (t instanceof String) || (t instanceof Character) || (t instanceof Float)
                    || (t instanceof Double) || (t instanceof Date)) {
                if (t instanceof Date) {
                    sb.append((i++ == 0) ? sdf.format(t) : ("," + sdf.format(t)));
                } else {
                    sb.append((i++ == 0) ? t.toString() : ("," + t.toString()));
                }
            } else {
                try {
                    throw new Exception(
                            "Array.toString()方法仅支持Integer,Short,Long,Boolean,Byte,String,Character,Float,Double,Date");
                } catch (Exception e) {
                    e.printStackTrace();
                    return sb.toString();
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Date[] d = {new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date()};
        System.out.println(toString(d));
    }

}
