package com.tenacity.free.common.excel;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * @project_name: tenacity-free-common
 * @package_name: com.tenacity.free.common.excel
 * @file_name: GsonExclusion.java
 * @author: free.zhang
 * @datetime: 2018年1月13日 下午8:12:54
 * @desc: Gson 字段排除
 */
public class GsonExclusion implements ExclusionStrategy {

    public String fields[];

    public GsonExclusion(String[] fields) {
        this.fields = fields;
    }

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        for (String s : fields) {
            if (s.equalsIgnoreCase(f.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }

}
