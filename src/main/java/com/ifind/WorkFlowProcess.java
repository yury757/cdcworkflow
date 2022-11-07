package com.ifind;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ifind.pojo.event.Event;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkFlowProcess extends ProcessFunction<Event, Event> {
    private static final Logger LOG = LoggerFactory.getLogger(WorkFlowProcess.class);
    public static final WorkFlowProcess INSTANCE = new WorkFlowProcess();

    @Override
    public void processElement(Event value, Context ctx, Collector<Event> out) throws Exception {

    }
}
