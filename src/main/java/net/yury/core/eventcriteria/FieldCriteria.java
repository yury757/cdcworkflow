package net.yury.core.eventcriteria;

import java.util.Set;

/**
 * 变更字段判断器
 * @author yury
 */
public abstract class FieldCriteria {
    /**
     * 订阅的字段集合
     */
    protected Set<String> subscribedFields;

    /**
     * 变动的字段是否符合订阅的内容
     * @param changedFields 变动的字段集合
     * @return
     */
    public abstract boolean meetField(Set<String> changedFields);
}
