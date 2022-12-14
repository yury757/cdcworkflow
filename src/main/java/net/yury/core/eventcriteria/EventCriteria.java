package net.yury.core.eventcriteria;

import net.yury.core.event.Event;

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
