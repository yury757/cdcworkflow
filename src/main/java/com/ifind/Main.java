package com.ifind;

import com.ifind.core.event.Event;
import com.ifind.simplehttp.Controller;
import com.ifind.simplehttp.SHServer;
import com.ifind.sink.MySink;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import com.ververica.cdc.connectors.postgres.PostgreSQLSource;

public class Main {
    public static void main2(String[] args) throws Exception {

        SHServer server = new SHServer(7000, new Controller());

        SourceFunction<Event> sourceFunction = PostgreSQLSource.<Event>builder()
                .hostname("10.0.16.234")
                .port(5444)
                .database("postgres")
                .schemaList("db40")
                .tableList("db40.yb003")
                .username("db40")
                .password("db40_aa")
                .deserializer(DBDeserialization.INSTANCE)
                .build();

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env
                .addSource(sourceFunction)
                .addSink(new MySink(server)).setParallelism(3);

        env.execute();
    }

    public static void main(String[] args) {
    }
}
