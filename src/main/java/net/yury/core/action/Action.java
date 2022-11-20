package net.yury.core.action;

import net.yury.core.event.Event;

import java.util.List;

public abstract class Action {
    public abstract void process(Event event) throws RuntimeException;
    public abstract void batchProcess(List<Event> eventList) throws RuntimeException;
}
