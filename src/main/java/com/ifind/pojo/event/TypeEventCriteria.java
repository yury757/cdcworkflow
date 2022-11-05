package com.ifind.pojo.event;

public class TypeEventCriteria implements EventCriteria {
    private String eventType;
    public TypeEventCriteria(String eventType) {
        this.eventType = eventType;
    }
    public boolean meetEvent(Event event) {
        if (this.eventType == null) return false;
        return this.eventType.equals(event.getEventType());
    }
}
