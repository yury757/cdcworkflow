package com.ifind.pojo.event;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Set;

/**
 * 更新事件
 * @author yury
 */
public class UpdateEvent extends Event {
    private ObjectNode oldData;
    private ObjectNode newData;
    private Set<String> changedFields;

    public UpdateEvent(ObjectNode oldData, ObjectNode newData, Set<String> changedFields) {
        super(Event.UPDATE_EVENT);
        this.changedFields = changedFields;
        this.oldData = oldData;
        this.newData = newData;
    }

    public Set<String> getChangedFields() {
        return changedFields;
    }
    public void setChangedFields(Set<String> changedFields) {
        this.changedFields = changedFields;
    }
    public ObjectNode getOldData() {
        return oldData;
    }
    public void setOldData(ObjectNode oldData) {
        this.oldData = oldData;
    }
    public ObjectNode getNewData() {
        return newData;
    }
    public void setNewData(ObjectNode newData) {
        this.newData = newData;
    }
}
