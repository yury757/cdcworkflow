package com.ifind.pojo.event;

public abstract class Event {
    public static final String UPDATE_EVENT = "u";
    public static final String INSERT_EVENT = "c";
    public static final String DELETE_EVENT = "d";

    private String eventType;
    private String data;

    public Event(String eventType) {
        this.eventType = eventType;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getEventType() {
        return this.eventType;
    }
    public String getData() {
        return this.data;
    }
}
