package com.ifind.core.eventcriteria;

import com.ifind.core.event.Event;

import java.util.HashSet;
import java.util.Set;

public class EventCriteriaFactory {
    public static EventCriteria getEventCriteria(String tableName, String eventType, Set<String> fields) {
        EventCriteria eventCriteria;
        if (Event.UPDATE_EVENT.equals(eventType)) {
            if (fields == null) {
                fields = new HashSet<>();
            }
            FieldCriteria fieldCriteria = new AnyFieldCriteria(fields);
            eventCriteria = new UpdateEventCriteria(tableName, fieldCriteria);
        }else {
            eventCriteria = new DDLEventCriteria(tableName, eventType);
        }
        return eventCriteria;
    }
}
