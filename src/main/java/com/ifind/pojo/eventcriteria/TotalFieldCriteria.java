package com.ifind.pojo.eventcriteria;

import java.util.Set;

/**
 * 订阅所有字段均发生变动的判断器
 * @author yury
 */
public class TotalFieldCriteria extends FieldCriteria {
    public TotalFieldCriteria(Set<String> subscribedFields) {
        assert subscribedFields != null;
        this.subscribedFields = subscribedFields;
    }

    /**
     * 订阅的字段都发生变动，则成立
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
