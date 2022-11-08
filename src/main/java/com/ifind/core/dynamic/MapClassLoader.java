package com.ifind.core.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 专用于动态编译的类加载器
 * 使用方式：{@link MapClassLoader#loadClass(String)}
 * @author yury
 */
public class MapClassLoader extends ClassLoader {
    private static final Logger LOG = LoggerFactory.getLogger(MapClassLoader.class);
    public static final MapClassLoader INSTANCE = new MapClassLoader();

    /**
     * key: 全限定类名
     * value: java字节码
     */
    private Map<String, byte[]> classes = new ConcurrentHashMap<>();
    private MapClassLoader() {
    }

    /**
     * {@link ClassLoader#loadClass(String)} 会调用该方法，重写该方法后，就可以使用自定义的查询类的方式
     * @param fullName 全限定类名
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String fullName) throws ClassNotFoundException {
        byte[] bytes = this.classes.get(fullName);
        if (bytes == null)
            throw new ClassNotFoundException(fullName);
        Class<?> cl = defineClass(fullName, bytes, 0, bytes.length);
        if (cl == null)
            throw new ClassNotFoundException(fullName);
        return cl;
    }

    /**
     * 添加一个字节码文件对象
     * @param fullName 全限定类名
     * @param byteArrayClassFile
     */
    public void put(String fullName, byte[] byteArrayClassFile) {
        this.classes.put(fullName, byteArrayClassFile);
    }
}
