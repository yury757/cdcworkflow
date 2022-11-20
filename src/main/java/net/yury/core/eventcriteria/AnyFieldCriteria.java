package net.yury.core.eventcriteria;

import java.util.Set;

/**
 * 任意订阅字段变动判断器
 * @author yury
 */
public class AnyFieldCriteria extends FieldCriteria {
    public AnyFieldCriteria(Set<String> subscribedFields) {
        assert subscribedFields != null;
        this.subscribedFields = subscribedFields;
    }

    /**
     * 只要有任意一个订阅的字段变动了，则成立
     * @param changedFields 变动的字段集合
     * @return
     */
    @Override
    public boolean meetField(Set<String> changedFields) {
        for (String subscribedField : subscribedFields) {
            if (changedFields.contains(subscribedField)) {
                return true;
            }
        }
        return false;
    }
}
