package net.yury.core.event;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class ReadEvent extends Event {
    private ObjectNode newData;

    public ReadEvent(ObjectNode newData) {
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
