package com.tenacity.free.common.json;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.tenacity.free.common.object.ObjectUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @project_name: tenacity-free-common
 * @package_name: com.tenacity.free.common.util
 * @file_name: JsonUtils.java
 * @author: free.zhang
 * @datetime: 2018年1月13日 下午6:32:26
 * @desc: TODO
 */
public class JsonUtils {

    private static volatile JSONObject resultJson = new JSONObject();

    private JsonUtils() {
    }

    /**
     * @param '[]
     * @return net.sf.json.JSONObject
     * @class_name JsonUtils
     * @method getInstall
     * @description 单例模式创建对象
     * @author free.zhang
     * @date 2018/3/2 14:01
     */
    public static JSONObject getInstance() {
        synchronized (JsonUtils.class) {
            if (null == resultJson) {
                resultJson = new JSONObject();
            }
        }
        return resultJson;
    }

    /**
     * @param '[object]
     * @return net.sf.json.JSONObject
     * @class_name JsonUtils
     * @method getInstance
     * @description 带参数的json对象实例化
     * @author free.zhang
     * @date 2018/3/2 14:06
     */
    public static JSONObject getInstance(Object... objects) {
        synchronized (JsonUtils.class) {
            if (null == resultJson) {
                resultJson = new JSONObject();
            }
        }
        if (null != objects && objects.length != 0) {
            for (int i = 0; i < objects.length; i++) {
                resultJson.put("param" + i, objects[i]);
            }
        }
        return resultJson;
    }

    /**
     * @param object
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:34:28
     * @desc: 校验是否是json格式
     */
    public static JSONObject checkFormatJson(Object object) {
        try {
            if (ObjectUtils.isEmpty(object)) {
                return null;
            }
            if (object instanceof String) {
                return JSONObject.fromObject(object);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param object
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:34:20
     * @desc: 校验是否为JsonArray格式
     */
    public static JSONArray checkFormatJsonArray(Object object) {
        try {
            if (ObjectUtils.isEmpty(object)) {
                return null;
            }
            if (object instanceof String) {
                return JSONArray.fromObject(object);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param json
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:34:12
     * @desc: 校验json是否为空 如果为空返回:true 否则返回:false
     */
    public static boolean checkJsonIsNull(JSONObject json) {
        if (json == null || json.isEmpty() || json.isNullObject()) {
            return true;
        }
        return false;
    }

    /**
     * @param json
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:34:02
     * @desc: 校验json是否为空, 如果为空返回:true 否则返回:false
     */
    public static boolean checkJsonNotIsNull(JSONObject json) {
        if (json == null || json.isEmpty() || json.isNullObject()) {
            return false;
        }
        return true;
    }

    /**
     * @param json
     * @param key
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:33:51
     * @desc: 校验key是否存在于json当中, 对应的value是否为空null或""
     */
    public static boolean checkJsonKey(JSONObject json, String key) {
        if (!json.containsKey(key) || json.getString(key) == null || "".equals(json.getString(key))) {
            return true;
        }
        return false;
    }

    /**
     * @param json
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:33:42
     * @desc: 获取json的所有key
     */
    @SuppressWarnings("unchecked")
    public static List<String> getJsonKey(JSONObject json) {
        if (ObjectUtils.isEmpty(json)) {
            return null;
        }
        Iterator<String> iterator = json.keySet().iterator();
        String key = null;
        List<String> keyList = new ArrayList<>();
        while (iterator.hasNext()) {
            key = iterator.next();
            keyList.add(key);
        }
        return keyList;
    }

    /**
     * @param json
     * @return
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午6:33:33
     * @desc: 将json转换为Map
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> jsonToMap(JSONObject json) {
        if (ObjectUtils.isEmpty(json)) {
            return null;
        }
        Iterator<String> iterator = json.keySet().iterator();
        Map<String, Object> map = new HashMap<>();
        String key = null;
        Object object = null;
        while (iterator.hasNext()) {
            key = iterator.next();
            object = json.get(key);
            map.put(key, object);
        }
        return map;
    }

    /**
     * @param '[object]
     * @return java.lang.String
     * @class_name JsonUtils
     * @method array2json
     * @description 将对象转换成JSONArray
     * @author free.zhang
     * @date 2018/3/2 14:11
     */
    public static String array2json(Object object) {

        JSONArray jsonArray = JSONArray.fromObject(object);
        return jsonArray.toString();
    }

    /**
     * @param '[json, valueClz]
     * @return java.lang.Object
     * @class_name JsonUtils
     * @method json2Array
     * @description 将JSON转换成数组, 其中valueClz为数组中存放的对象的Class
     * @author free.zhang
     * @date 2018/3/2 14:12
     */
    public static Object json2Array(String json, Class<?> valueClz) {

        JSONArray jsonArray = JSONArray.fromObject(json);
        return JSONArray.toArray(jsonArray, valueClz);
    }

    /**
     * @param '[object]
     * @return java.lang.String
     * @class_name JsonUtils
     * @method collection2json
     * @description 将Collection转换成JSON
     * @author free.zhang
     * @date 2018/3/2 14:12
     */
    public static String collection2json(Object object) {

        JSONArray jsonArray = JSONArray.fromObject(object);
        return jsonArray.toString();
    }

    /**
     * @param '[object]
     * @return java.lang.String
     * @class_name JsonUtils
     * @method map2json
     * @description 将Map转换成JSON
     * @author free.zhang
     * @date 2018/3/2 14:12
     */
    public static String map2json(Object object) {

        JSONObject jsonObject = JSONObject.fromObject(object);
        return jsonObject.toString();
    }

    /**
     * @param '[keyArray, json, valueClz]
     * @return java.util.Map
     * @class_name JsonUtils
     * @method json2Map
     * @description 将JSON转换成Map, 其中valueClz为Map中value的Class, keyArray为Map的key
     * @author free.zhang
     * @date 2018/3/2 14:12
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Map json2Map(Object[] keyArray, String json, Class valueClz) {

        JSONObject jsonObject = JSONObject.fromObject(json);
        Map classMap = new HashMap();

        for (int i = 0; i < keyArray.length; i++) {
            classMap.put(keyArray[i], valueClz);
        }

        return (Map) JSONObject.toBean(jsonObject, Map.class, classMap);
    }

    /**
     * @param '[object]
     * @return java.lang.String
     * @class_name JsonUtils
     * @method bean2json
     * @description 将POJO转换成JSON
     * @author free.zhang
     * @date 2018/3/2 14:13
     */
    public static String bean2json(Object object) {

        JSONObject jsonObject = JSONObject.fromObject(object);
        return jsonObject.toString();
    }

    /**
     * @param '[json, beanClz]
     * @return java.lang.Object
     * @class_name JsonUtils
     * @method json2Object
     * @description 将JSON转换成POJO, 其中beanClz为POJO的Class
     * @author free.zhang
     * @date 2018/3/2 14:13
     */
    public static Object json2Object(String json, Class<?> beanClz) {
        return JSONObject.toBean(JSONObject.fromObject(json), beanClz);
    }

    /**
     * @param json
     * @param valueType
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     * @author: free.zhang
     * @Date: 2018年1月26日 下午3:51:20
     * @Description: json转换为java对象
     * @return: T
     */
    public static <T> T fromJsonToObject(String json, Class<T> valueType)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, valueType);
    }

    /**
     * @param '[key, value]
     * @return java.lang.String
     * @class_name JsonUtils
     * @method string2json
     * @description 将String转换成JSON
     * @author free.zhang
     * @date 2018/3/2 14:13
     */
    public static String string2json(String key, String value) {
        JSONObject object = new JSONObject();
        object.put(key, value);
        return object.toString();
    }

    /**
     * @param '[json, key]
     * @return java.lang.String
     * @class_name JsonUtils
     * @method json2String
     * @description 将JSON转换成String
     * @author free.zhang
     * @date 2018/3/2 14:13
     */
    public static String json2String(String json, String key) {
        JSONObject jsonObject = JSONObject.fromObject(json);
        return jsonObject.get(key).toString();
    }

    /***
     * @author: free.zhang
     * @Date: 2018年1月26日 下午3:51:30
     * @Description: 将List对象序列化为JSON文本
     * @return: String
     * @param list
     * @return
     */
    public static <T> String toJSONString(List<T> list) {
        JSONArray jsonArray = JSONArray.fromObject(list);

        return jsonArray.toString();
    }

    /***
     * @author: free.zhang
     * @Date: 2018年1月26日 下午3:51:39
     * @Description: 将对象序列化为JSON文本
     * @return: String
     * @param object
     * @return
     */
    public static String toJSONString(Object object) {
        JSONArray jsonArray = JSONArray.fromObject(object);

        return jsonArray.toString();
    }

    /***
     * @author: free.zhang
     * @Date: 2018年1月26日 下午3:51:47
     * @Description: 将JSON对象数组序列化为JSON文本
     * @return: String
     * @param jsonArray
     * @return
     */
    public static String toJSONString(JSONArray jsonArray) {
        return jsonArray.toString();
    }

    /***
     * @author: free.zhang
     * @Date: 2018年1月26日 下午3:51:55
     * @Description: 将JSON对象序列化为JSON文本
     * @return: String
     * @param jsonObject
     * @return
     */
    public static String toJSONString(JSONObject jsonObject) {
        return jsonObject.toString();
    }

    /***
     * @author: free.zhang
     * @Date: 2018年1月26日 下午3:52:06
     * @Description: 将对象转换为List对象
     * @return: List
     * @param object
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List toArrayList(Object object) {
        List arrayList = new ArrayList();

        JSONArray jsonArray = JSONArray.fromObject(object);

        Iterator it = jsonArray.iterator();
        while (it.hasNext()) {
            JSONObject jsonObject = (JSONObject) it.next();
            Iterator keys = jsonObject.keys();
            while (keys.hasNext()) {
                Object key = keys.next();
                Object value = jsonObject.get(key);
                arrayList.add(value);
            }
        }

        return arrayList;
    }

    /**
     * @param object
     * @return
     * @author: free.zhang
     * @Date: 2018年1月26日 下午3:53:25
     * @Description: 将对象转换为Collection对象
     * @return: Collection
     */
    public static Collection<?> toCollection(Object object) {
        JSONArray jsonArray = JSONArray.fromObject(object);
        return JSONArray.toCollection(jsonArray);
    }

    /***
     * @author: free.zhang
     * @Date: 2018年1月26日 下午3:53:34
     * @Description: 将对象转换为JSON对象数组
     * @return: JSONArray
     * @param object
     * @return
     */
    public static JSONArray toJSONArray(Object object) {
        return JSONArray.fromObject(object);
    }

    /***
     * @author: free.zhang
     * @Date: 2018年1月26日 下午3:53:44
     * @Description: 将对象转换为JSON对象
     * @return: JSONObject
     * @param object
     * @return
     */
    public static JSONObject toJSONObject(Object object) {
        return JSONObject.fromObject(object);
    }

    /***
     * @author: free.zhang
     * @Date: 2018年1月26日 下午3:53:53
     * @Description: 将对象转换为HashMap
     * @return: HashMap
     * @param object
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> toHashMap(Object object) {
        Map<String, Object> data = new HashMap<String, Object>();
        JSONObject jsonObject = JsonUtils.toJSONObject(object);
        Iterator<String> it = jsonObject.keys();
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            Object value = jsonObject.get(key);
            data.put(key, value);
        }

        return data;
    }

    /***
     * @author: free.zhang
     * @Date: 2018年1月26日 下午3:54:01
     * @Description: 将对象转换为List<Map<String,Object>>
     * @return: List<Map<String,Object>>
     * @param object
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> toList(Object object) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        JSONArray jsonArray = JSONArray.fromObject(object);
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            Map<String, Object> map = new HashMap<String, Object>();
            Iterator<String> it = jsonObject.keys();
            while (it.hasNext()) {
                String key = (String) it.next();
                Object value = jsonObject.get(key);
                map.put((String) key, value);
            }
            list.add(map);
        }
        return list;
    }

    /***
     * @author: free.zhang
     * @Date: 2018年1月26日 下午3:54:10
     * @Description: 将JSON对象数组转换为传入类型的List
     * @return: List<T>
     * @param jsonArray
     * @param objectClass
     * @return
     */
    @SuppressWarnings({"unchecked", "deprecation"})
    public static <T> List<T> toList(JSONArray jsonArray, Class<T> objectClass) {
        return JSONArray.toList(jsonArray, objectClass);
    }

    /***
     * @author: free.zhang
     * @Date: 2018年1月26日 下午3:54:18
     * @Description: 将对象转换为传入类型的List
     * @return: List<T>
     * @param object
     * @param objectClass
     * @return
     */
    @SuppressWarnings({"unchecked", "deprecation"})
    public static <T> List<T> toList(Object object, Class<T> objectClass) {
        JSONArray jsonArray = JSONArray.fromObject(object);

        return JSONArray.toList(jsonArray, objectClass);
    }

    /***
     * @author: free.zhang
     * @Date: 2018年1月26日 下午3:54:35
     * @Description: 将JSON对象转换为传入类型的对象
     * @return: T
     * @param jsonObject
     * @param beanClass
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T toBean(JSONObject jsonObject, Class<T> beanClass) {
        return (T) JSONObject.toBean(jsonObject, beanClass);
    }

    /***
     * @author: free.zhang
     * @Date: 2018年1月26日 下午3:54:43
     * @Description: 将将对象转换为传入类型的对象
     * @return: T
     * @param object
     * @param beanClass
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T toBean(Object object, Class<T> beanClass) {
        JSONObject jsonObject = JSONObject.fromObject(object);

        return (T) JSONObject.toBean(jsonObject, beanClass);
    }

    /***
     * @author: free.zhang
     * @Date: 2018年1月26日 下午3:54:58
     * @Description: 将JSON文本反序列化为主从关系的实体
     * @param <T>
     *            泛型T 代表主实体类型
     * @param <D>
     *            泛型D 代表从实体类型
     * @param jsonString
     *            JSON文本
     * @param mainClass
     *            主实体类型
     * @param detailName
     *            从实体类在主实体类中的属性名称
     * @param detailClass
     *            从实体类型
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static <T, D> T toBean(String jsonString, Class<T> mainClass, String detailName, Class<D> detailClass)
            throws IllegalAccessException, InvocationTargetException {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        JSONArray jsonArray = (JSONArray) jsonObject.get(detailName);

        T mainEntity = JsonUtils.toBean(jsonObject, mainClass);
        List<D> detailList = JsonUtils.toList(jsonArray, detailClass);

        BeanUtils.setProperty(mainEntity, detailName, detailList);

        return mainEntity;
    }

    /**
     * @param <T>泛型T       代表主实体类型
     * @param <D1>泛型D1     代表从实体类型
     * @param <D2>泛型D2     代表从实体类型
     * @param jsonString   JSON文本
     * @param mainClass    主实体类型
     * @param detailName1  从实体类在主实体类中的属性
     * @param detailClass1 从实体类型
     * @param detailName2  从实体类在主实体类中的属性
     * @param detailClass2 从实体类型
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @author: free.zhang
     * @Date: 2018年1月26日 下午3:55:40
     * @Description: 将JSON文本反序列化为主从关系的实体
     */
    public static <T, D1, D2> T toBean(String jsonString, Class<T> mainClass, String detailName1,
                                       Class<D1> detailClass1, String detailName2, Class<D2> detailClass2)
            throws IllegalAccessException, InvocationTargetException {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        JSONArray jsonArray1 = (JSONArray) jsonObject.get(detailName1);
        JSONArray jsonArray2 = (JSONArray) jsonObject.get(detailName2);

        T mainEntity = JsonUtils.toBean(jsonObject, mainClass);
        List<D1> detailList1 = JsonUtils.toList(jsonArray1, detailClass1);
        List<D2> detailList2 = JsonUtils.toList(jsonArray2, detailClass2);

        BeanUtils.setProperty(mainEntity, detailName1, detailList1);
        BeanUtils.setProperty(mainEntity, detailName2, detailList2);

        return mainEntity;
    }

    /**
     * @param <T>泛型T       代表主实体类型
     * @param <D1>泛型D1     代表从实体类型
     * @param <D2>泛型D2     代表从实体类型
     * @param jsonString   JSON文本
     * @param mainClass    主实体类型
     * @param detailName1  从实体类在主实体类中的属性
     * @param detailClass1 从实体类型
     * @param detailName2  从实体类在主实体类中的属性
     * @param detailClass2 从实体类型
     * @param detailName3  从实体类在主实体类中的属性
     * @param detailClass3 从实体类型
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @author: free.zhang
     * @Date: 2018年1月26日 下午3:56:32
     * @Description: 将JSON文本反序列化为主从关系的实体
     */
    public static <T, D1, D2, D3> T toBean(String jsonString, Class<T> mainClass, String detailName1,
                                           Class<D1> detailClass1, String detailName2, Class<D2> detailClass2, String detailName3,
                                           Class<D3> detailClass3) throws IllegalAccessException, InvocationTargetException {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        JSONArray jsonArray1 = (JSONArray) jsonObject.get(detailName1);
        JSONArray jsonArray2 = (JSONArray) jsonObject.get(detailName2);
        JSONArray jsonArray3 = (JSONArray) jsonObject.get(detailName3);

        T mainEntity = JsonUtils.toBean(jsonObject, mainClass);
        List<D1> detailList1 = JsonUtils.toList(jsonArray1, detailClass1);
        List<D2> detailList2 = JsonUtils.toList(jsonArray2, detailClass2);
        List<D3> detailList3 = JsonUtils.toList(jsonArray3, detailClass3);

        BeanUtils.setProperty(mainEntity, detailName1, detailList1);
        BeanUtils.setProperty(mainEntity, detailName2, detailList2);
        BeanUtils.setProperty(mainEntity, detailName3, detailList3);
        return mainEntity;
    }

    /**
     * @param <T>         主实体类型
     * @param jsonString  JSON文本
     * @param mainClass   主实体类型
     * @param detailClass 存放了多个从实体在主实体中属性名称和类型
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @author: free.zhang
     * @Date: 2018年1月26日 下午3:57:16
     * @Description: 将JSON文本反序列化为主从关系的实体
     */
    @SuppressWarnings("rawtypes")
    public static <T> T toBean(String jsonString, Class<T> mainClass, HashMap<String, Class> detailClass)
            throws IllegalAccessException, InvocationTargetException {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        T mainEntity = JsonUtils.toBean(jsonObject, mainClass);
        for (Object key : detailClass.keySet()) {
            Class value = (Class) detailClass.get(key);
            BeanUtils.setProperty(mainEntity, key.toString(), value);
        }
        return mainEntity;
    }

}
