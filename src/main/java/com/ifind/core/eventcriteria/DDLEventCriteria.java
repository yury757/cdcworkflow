package com.ifind.core.eventcriteria;

import com.ifind.core.event.Event;

/**
 * 表和DDL类型判断器
 * @author yury
 */
public class DDLEventCriteria extends EventCriteria {
    private String tableName;
    private String eventType;
    public DDLEventCriteria(String tableName, String eventType) {
        assert eventType != null && tableName != null;
        this.tableName = tableName;
        this.eventType = eventType;
    }
    @Override
    public boolean meetEvent(Event event) {
        return this.tableName.equals(event.getTableName()) && this.eventType.equals(event.getEventType());
    }
}
