package com.tenacity.free.common.excel;

import com.google.gson.FieldNamingStrategy;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @project_name: tenacity-free-common
 * @package_name: com.tenacity.free.common.excel
 * @file_name: GsonKeyRename.java
 * @author: free.zhang
 * @datetime: 2018年1月13日 下午8:12:06
 * @desc: Gson字段重命名
 */
public class GsonKeyRename implements FieldNamingStrategy {

    public Map<String, String> map;

    public GsonKeyRename(Map<String, String> map) {
        super();
        this.map = map;
    }

    @Override
    public String translateName(Field f) {

        Iterator<Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, String> entry = it.next();
            if (entry.getKey().equalsIgnoreCase(f.getName())) {
                return entry.getValue();
            }
        }

        return f.getName();
    }

}
