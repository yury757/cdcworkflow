package com.ifind.simplehttp;

import com.ifind.core.action.Action;
import com.ifind.core.action.YB003InsertAction;
import com.ifind.core.dynamic.CompileUtil;
import com.ifind.core.dynamic.DynamicObjectContainer;
import com.ifind.core.event.Event;
import com.ifind.core.eventcriteria.EventCriteria;
import com.ifind.core.eventcriteria.EventCriteriaFactory;
import com.ifind.core.job.Job;
import com.ifind.simplehttp.annotation.SHController;
import com.ifind.simplehttp.annotation.SHMapping;
import com.ifind.sink.MySink;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class Controller implements SHController {
    
    @SHMapping(path = "/test")
    public String test(String content) {
        return "success";
    }
    
    @SHMapping(path = "/jobs")
    public Map<String, String> getAllJobs(String content) {
        return MySink.print();
    }
    
    @SHMapping(path = "/register", method = "POST")
    public String register(String content) {
        Map<String, String> params = getParams(content);
        String subscribeTableName = params.getOrDefault("subscribeTableName", null);
        String subscribeTableField = params.getOrDefault("subscribeTableField", null);
        String srcName = params.getOrDefault("srcName", null);
        String srcContent = params.getOrDefault("srcContent", null);
        if (!checkNull(subscribeTableName, srcName, srcContent)) {
            throw new RuntimeException("params error");
        }
        EventCriteria eventCriteria = EventCriteriaFactory.getEventCriteria(subscribeTableName, Event.READ_EVENT, null);
        CompileUtil.compile(srcName, srcContent);
        Action action = (Action)DynamicObjectContainer.INSTANCE.get(srcName);
        Job job = new Job(eventCriteria, action);
        MySink.register(srcName, job);
        return "success";
    }

    public Map<String, String> getParams(String content) {
        Map<String, String> params = new HashMap<>();
        String[] split = content.split("&");
        for (String s : split) {
            String[] split2 = s.split("=");
            if (split2.length >= 2) {
                params.put(split2[0], split2[1]);
            }
        }
        return params;
    }

    public boolean checkNull(String... o) {
        for (String s : o) {
            if (StringUtils.isEmpty(s)) {
                return false;
            }
        }
        return true;
    }

}
