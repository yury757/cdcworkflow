package com.ifind.pojo.event;

import java.util.Set;

public interface FieldCriteria {
    /**
     * 变动的字段是否符合订阅的内容
     * @param changedFields 变动的字段集合
     * @return
     */
    public boolean meetField(Set<String> changedFields);
}
