package com.ifind;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkFlowProcess extends ProcessFunction<ObjectNode, ObjectNode> {
    private static final Logger LOG = LoggerFactory.getLogger(WorkFlowProcess.class);
    public static final WorkFlowProcess INSTANCE = new WorkFlowProcess();

    @Override
    public void processElement(ObjectNode value, Context ctx, Collector<ObjectNode> out) throws Exception {

    }
}
