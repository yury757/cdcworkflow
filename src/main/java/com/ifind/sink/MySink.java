package com.ifind.sink;

import com.ifind.core.event.Event;
import com.ifind.core.job.Job;
import com.ifind.simplehttp.SHServer;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MySink implements SinkFunction<Event> {
    private static final Map<String, Job> jobMap = new ConcurrentHashMap<>();
    public MySink(SHServer server) {
        try {
            server.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
