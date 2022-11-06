package com.ifind.pojo.event;

/**
 * 事件判断器
 * @author yury
 */
public abstract class EventCriteria {

    /**
     * 判断一个事件是否满足要求
     * @param event
     * @return
     */
    public abstract boolean meetEvent(Event event);
}
