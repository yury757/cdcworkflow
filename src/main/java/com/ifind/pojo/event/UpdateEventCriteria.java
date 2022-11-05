package com.ifind.pojo.event;

import java.util.Set;

public class UpdateEventCriteria implements EventCriteria {
    private Set<String> fields;
    private FieldCriteria fieldCriteria;
    public UpdateEventCriteria(Set<String> fields, FieldCriteria fieldCriteria) {
        this.fields = fields;
        this.fieldCriteria = fieldCriteria;
    }

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
