package com.ifind.core.event;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 写入事件
 * @author yury
 */
public class InsertEvent extends Event {
    private ObjectNode newData;

    public InsertEvent(ObjectNode newData) {
        super(Event.INSERT_EVENT);
        this.newData = newData;
    }

    public ObjectNode getNewData() {
        return newData;
    }
    public void setNewData(ObjectNode newData) {
        this.newData = newData;
    }
}
