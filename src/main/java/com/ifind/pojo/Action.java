package com.ifind.pojo;

import com.ifind.pojo.event.Event;

public abstract class Action {
    public abstract void execute(Event event) throws RuntimeException;
}
