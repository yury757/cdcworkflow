package com.ifind.core.dynamic;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用于管理动态编译生成的对象
 * @author yury
 */
public class DynamicObjectContainer {
    public static final DynamicObjectContainer INSTANCE = new DynamicObjectContainer();
    private Map<String, Object> objectMap = new ConcurrentHashMap<>();
    private DynamicObjectContainer() {

    }
    /**
     * 注册对象
     * @param name 对象名
     * @param obj 对象
     */
    public void register(String name, Object obj) {
        objectMap.put(name, obj);
    }

    /**
     * 通过对象名称获取对象
     * @param name 对象名
     * @return
     */
    public Object get(String name) {
        return objectMap.getOrDefault(name, null);
    }
}
