package com.ifind.pojo;

import com.ifind.pojo.event.Event;

public abstract class Job {
    private Event subscribedEvent;
    private Action action;

    public Job(Event subscribedEvent, Action action) {
        this.subscribedEvent = subscribedEvent;
        this.action = action;
    }

    public void run() {
        this.action.execute(this.subscribedEvent);
    }
}
