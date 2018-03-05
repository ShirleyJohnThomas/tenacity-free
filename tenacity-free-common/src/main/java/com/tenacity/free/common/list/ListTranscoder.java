package com.tenacity.free.common.list;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @project_name: tenacity-free-common
 * @package_name: com.tenacity.free.common.list
 * @file_name: ListTranscoder.java
 * @author: free.zhang
 * @datetime: 2018年1月13日 下午7:32:02
 * @desc: 集合序列化，反序列化工具
 */
public class ListTranscoder<T extends Serializable> {

    /**
     * @param value
     * @return
     * @throws IOException
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:33:57
     * @desc: list集合序列化
     */
    @SuppressWarnings({"unchecked", "unused"})
    public byte[] serizlize(Object value) throws IOException {

        if (value == null) {
            throw new NullPointerException("Can't serialize null");
        }
        List<T> values = (List<T>) value;
        byte[] results = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        bos = new ByteArrayOutputStream();
        os = new ObjectOutputStream(bos);
        for (T t : values) {
            os.writeObject(t);
        }
        os.writeObject(null);
        os.close();
        bos.close();
        results = bos.toByteArray();
        close(os);
        close(bos);

        return null;
    }

    /**
     * @param input
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:33:17
     * @desc: list集合反序列化
     */
    @SuppressWarnings("unchecked")
    public Object deserizlize(byte[] input) throws IOException, ClassNotFoundException {

        List<T> list = new ArrayList<>();

        ByteArrayInputStream bis = null;
        ObjectInputStream is = null;
        if (input != null) {
            bis = new ByteArrayInputStream(input);
            is = new ObjectInputStream(bis);
            while (true) {
                T t = (T) is.readObject();
                if (t == null) {
                    break;
                }
                list.add(t);
            }
        }
        close(is);
        close(bis);

        return list;
    }

    /**
     * @param closeable
     * @throws IOException
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:32:37
     * @desc: 释放资源
     */
    private static void close(Closeable closeable) throws IOException {
        if (closeable != null) {
            closeable.close();
        }
    }

}
