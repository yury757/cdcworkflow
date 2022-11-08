package com.ifind.core.event;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 删除事件
 * @author yury
 */
public class DeleteEvent extends Event {
    private ObjectNode oldData;

    public DeleteEvent(ObjectNode oldData) {
        super(Event.DELETE_EVENT);
        this.oldData = oldData;
    }

    public ObjectNode getOldData() {
        return oldData;
    }
    public void setOldData(ObjectNode oldData) {
        this.oldData = oldData;
    }
}
