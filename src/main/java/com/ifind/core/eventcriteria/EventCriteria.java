package com.ifind.core.eventcriteria;

import com.ifind.core.event.Event;

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
