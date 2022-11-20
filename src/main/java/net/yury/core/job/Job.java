package net.yury.core.job;

import net.yury.core.action.Action;
import net.yury.core.event.Event;
import net.yury.core.eventcriteria.EventCriteria;

import java.util.ArrayList;
import java.util.List;

/**
 * job 给定一个事件判定标准，以及对应的行为动作。当某个事件到来时，若符合事件判定器的标准，则执行该动作，否则不执行
 * @author yury
 */
public class Job {
    private EventCriteria eventCriteria;
    private Action action;

    public Job(EventCriteria eventCriteria, Action action) {
        this.eventCriteria = eventCriteria;
        this.action = action;
    }

    public void process(Event event) {
        if (eventCriteria.meetEvent(event)) {
            this.action.process(event);
        }
    }

    public void batchProcess(List<Event> eventList) {
        List<Event> list = new ArrayList<>(eventList.size());
        for (Event event : eventList) {
            if (eventCriteria.meetEvent(event)) {
                list.add(event);
            }
        }
        this.action.batchProcess(list);
    }
}
