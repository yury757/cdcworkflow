package com.ifind.pojo.eventcriteria;

import com.ifind.pojo.event.Event;
import com.ifind.pojo.event.UpdateEvent;

/**
 * 更新事件判断器
 * @author yury
 */
public class UpdateEventCriteria extends DDLEventCriteria {
    private FieldCriteria fieldCriteria;
    public UpdateEventCriteria(String tableName, FieldCriteria fieldCriteria) {
        super(tableName, Event.UPDATE_EVENT);
        this.fieldCriteria = fieldCriteria;
    }

    @Override
    public boolean meetEvent(Event event) {
        if (!(event instanceof UpdateEvent)) {
            return false;
        }
        UpdateEvent e = (UpdateEvent)event;
        if (!Event.UPDATE_EVENT.equals(event.getEventType())) {
            return false;
        }
        return fieldCriteria.meetField(e.getChangedFields());
    }
}
