package com.ifind;

import com.ifind.pojo.event.Event;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import com.ververica.cdc.connectors.postgres.PostgreSQLSource;

public class Main {
    public static void main2(String[] args) throws Exception {
        SourceFunction<Event> sourceFunction = PostgreSQLSource.<Event>builder()
                .hostname("localhost")
                .port(5432)
                .database("postgres")
                .schemaList("inventory")
                .tableList("inventory.products")
                .username("flinkuser")
                .password("flinkpw")
                .deserializer(DBDeserialization.INSTANCE)
                .build();

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env
                .addSource(sourceFunction)
                .process(WorkFlowProcess.INSTANCE) // WorkFlowProcess.INSTANCE
                .print().setParallelism(1); // use parallelism 1 for sink to keep message ordering

        env.execute();
    }

    public static void main(String[] args) {
    }
}
