package com.ifind.pojo.event;

import java.util.Set;

public class UpdateEvent extends Event {
    private Set<String> changedFields;

    public UpdateEvent(String eventType) {
        super(eventType);
    }
    public UpdateEvent(String eventType, Set<String> changedFields) {
        super(eventType);
        this.changedFields = changedFields;
    }

    public Set<String> getChangedFields() {
        return changedFields;
    }

    public void setChangedFields(Set<String> changedFields) {
        this.changedFields = changedFields;
    }
}
