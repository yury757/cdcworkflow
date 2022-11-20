package net.yury.sink;

import net.yury.core.event.Event;
import net.yury.core.job.Job;
import net.yury.simplehttp.SHServer;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MySink implements SinkFunction<Event> {
    private static final Map<String, Job> jobMap = new ConcurrentHashMap<>();
    public MySink(SHServer server) {
        server.start();
    }
    public static void register(String jobName, Job job) {
        jobMap.put(jobName, job);
    }
    
    public static Map<String, String> print() {
        Map<String, String> res = new HashMap<>();
        for (Map.Entry<String, Job> entry: jobMap.entrySet()) {
            res.put(entry.getKey(), entry.getValue().toString());
        }
        return res;
    }
    
    @Override
    public void invoke(Event value, Context context) throws Exception {
        for (Job job : jobMap.values()) {
            job.process(value);
        }
    }
}
