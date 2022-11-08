package com.ifind.core.event;

/**
 * 数据库数据变动事件
 * @author yury
 */
public class Event {
    public static final String UPDATE_EVENT = "u";
    public static final String INSERT_EVENT = "c";
    public static final String DELETE_EVENT = "d";
    public static final String READ_EVENT = "r";

    protected String eventType;
    protected Long modifyTime;
    protected String database;
    protected String schemaName;
    protected String tableName;

    public Event(String eventType) {
        this.eventType = eventType;
    }
    public String getEventType() {
        return this.eventType;
    }
    public Long getModifyTime() {
        return modifyTime;
    }
    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }
    public String getDatabase() {
        return database;
    }
    public void setDatabase(String database) {
        this.database = database;
    }
    public String getSchemaName() {
        return schemaName;
    }
    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
