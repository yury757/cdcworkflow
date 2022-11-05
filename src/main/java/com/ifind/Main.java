package com.ifind;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import com.ververica.cdc.debezium.StringDebeziumDeserializationSchema;
import com.ververica.cdc.connectors.postgres.PostgreSQLSource;
import org.apache.flink.util.Collector;

public class Main {
    public static void main(String[] args) throws Exception {
        SourceFunction<ObjectNode> sourceFunction = PostgreSQLSource.<ObjectNode>builder()
                .hostname("localhost")
                .port(5432)
                .database("postgres")
                .schemaList("inventory")
                .tableList("inventory.products")
                .username("flinkuser")
                .password("flinkpw")
                .deserializer(new DBDeserialization())
                .build();

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env
                .addSource(sourceFunction)
                .process(WorkFlowProcess.INSTANCE) // WorkFlowProcess.INSTANCE
                .print().setParallelism(1); // use parallelism 1 for sink to keep message ordering

        env.execute();
    }
}