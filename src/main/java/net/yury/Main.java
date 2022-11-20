package net.yury;

import net.yury.core.event.Event;
import net.yury.modify.ModifyPostgreSQLSource;
import net.yury.simplehttp.Controller;
import net.yury.simplehttp.SHServer;
import net.yury.sink.MySink;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

public class Main {
    public static void main(String[] args) throws Exception {

        SHServer server = new SHServer(7000, new Controller());

        SourceFunction<Event> sourceFunction = ModifyPostgreSQLSource.<Event>builder()
                .hostname("myubuntu1")
                .port(5432)
                .database("postgres")
                .schemaList("public")
                .tableList("public.t1")
                .username("postgres")
                .password("postgres")
                .slotName("flink")
                .decodingPluginName("pgoutput")
                .deserializer(DBDeserialization.INSTANCE)
                .build();

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.enableCheckpointing(2000);
        env.getCheckpointConfig().setCheckpointStorage("file:///D:/tmp/");
        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
        env.getCheckpointConfig().setMinPauseBetweenCheckpoints(10 * 1000);
        env.getCheckpointConfig().setMaxConcurrentCheckpoints(3);
        env.getCheckpointConfig().setCheckpointTimeout(5 * 1000);
        env.getCheckpointConfig().setTolerableCheckpointFailureNumber(3);
        env.getCheckpointConfig().setExternalizedCheckpointCleanup(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);

        env
                .addSource(sourceFunction)
                .addSink(new MySink(server)).setParallelism(3);

        env.execute();
    }
}
