package com.ifind.pojo.event;

import java.util.Set;

public class NormalFieldCriteria implements FieldCriteria {
    /**
     * 订阅的字段集合
     */
    private Set<String> subscribedFields;
    public NormalFieldCriteria(Set<String> subscribedFields) {
        assert subscribedFields != null;
        this.subscribedFields = subscribedFields;
    }

    /**
     * 只要变动的字段可以覆盖订阅的字段，则成立
     * @param changedFields 变动的字段集合
     * @return
     */
    public boolean meetField(Set<String> changedFields) {
        for (String subscribedField : this.subscribedFields) {
            if (!changedFields.contains(subscribedField)) {
                return false;
            }
        }
        return true;
    }
}
