package com.tenacity.free.common.list;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tenacity.free.common.string.StringUtils;


/**
 * @project_name: tenacity-free-common
 * @package_name: com.tenacity.free.common.list
 * @file_name: ListGroupUtils.java
 * @author: free.zhang
 * @datetime: 2018年1月13日 下午7:46:11
 * @desc: list分組
 */
public class ListGroupUtils {

    /**
     * @param <T>
     * @project_name: tenacity-free-common
     * @package_name: com.tenacity.free.common.list
     * @file_name: ListGroupUtils.java
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:48:27
     * @desc: 分组依据接口，用于集合分组时获取分组依据
     */
    public interface GroupBy<T> {
        T groupby(Object obj);
    }

    /**
     * @param colls
     * @param gb
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:48:21
     * @desc: TODO
     */
    public static final <T extends Comparable<T>, D> Map<T, List<D>> group(Collection<D> colls, GroupBy<T> gb) {
        if (colls == null || colls.isEmpty()) {
            System.out.println("分組集合不能為空!");
            return null;
        }
        if (gb == null) {
            System.out.println("分組依據接口不能為Null!");
            return null;
        }
        Iterator<D> iter = colls.iterator();
        Map<T, List<D>> map = new HashMap<T, List<D>>();
        while (iter.hasNext()) {
            D d = iter.next();
            T t = gb.groupby(d);
            if (map.containsKey(t)) {
                map.get(t).add(d);
            } else {
                List<D> list = new ArrayList<D>();
                list.add(d);
                map.put(t, list);
            }
        }
        return map;
    }

    /**
     * @param list
     * @param map
     * @param clazz
     * @param methodName
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:48:02
     * @desc: 将List<V>按照V的methodName方法返回值（返回值必须为K类型）分组，合入到Map<K, List<V>>中 <br>
     * 要保证入参的method必须为V的某一个有返回值的方法，并且该返回值必须为K类型
     */
    public static <K, V> void listGroup2Map(List<V> list, Map<K, List<V>> map, Class<V> clazz, String methodName)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        // 入参非法行校验
        if (null == list || null == map || null == clazz || StringUtils.isBlank(methodName)) {
            System.out.print("CommonUtils.listGroup2Map 入参错误，list：" + list + " ；map：" + map + " ；clazz：" + clazz
                    + " ；methodName：" + methodName);
            return;
        }
        // 获取方法
        Method method = getMethodByName(clazz, methodName);
        // 非空判断
        if (null == method) {
            return;
        }

        // 正式分组
        listGroup2Map(list, map, method);
    }

    /**
     * @param clazz
     * @param methodName
     * @return
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:47:45
     * @desc: 根据类和方法名，获取方法对象
     */
    public static Method getMethodByName(Class<?> clazz, String methodName)
            throws NoSuchMethodException, SecurityException {
        Method method = null;
        // 入参不能为空
        if (null == clazz || StringUtils.isBlank(methodName)) {
            System.out.print("CommonUtils.getMethodByName 入参错误，clazz：" + clazz + " ；methodName：" + methodName);
            return method;
        }
        method = clazz.getDeclaredMethod(methodName);

        return method;
    }

    /**
     * @param list
     * @param map
     * @param method
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:47:08
     * @desc: 将List<V>按照V的某个方法返回值（返回值必须为K类型）分组，合入到Map<K, List<V>>中<br>
     * 要保证入参的method必须为V的某一个有返回值的方法，并且该返回值必须为K类型
     */
    @SuppressWarnings("unchecked")
    public static <K, V> void listGroup2Map(List<V> list, Map<K, List<V>> map, Method method)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // 入参非法行校验
        if (null == list || null == map || null == method) {
            System.out.print("CommonUtils.listGroup2Map 入参错误，list：" + list + " ；map：" + map + " ；method：" + method);
            return;
        }
        // 开始分组
        Object key;
        List<V> listTmp;
        for (V val : list) {
            key = method.invoke(val);
            listTmp = map.get(key);
            if (null == listTmp) {
                listTmp = new ArrayList<V>();
                map.put((K) key, listTmp);
            }
            listTmp.add(val);
        }
    }
}
