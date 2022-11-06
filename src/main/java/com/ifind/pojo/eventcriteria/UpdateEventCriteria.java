package com.ifind.pojo.event;

/**
 * 更新事件判断器
 * @author yury
 */
public class UpdateEventCriteria extends DDLEventCriteria {
    private FieldCriteria fieldCriteria;
    public UpdateEventCriteria(FieldCriteria fieldCriteria) {
        super(Event.UPDATE_EVENT);
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
        if (!fieldCriteria.meetField(e.getChangedFields())) {
            return false;
        }
        return true;
    }
}
