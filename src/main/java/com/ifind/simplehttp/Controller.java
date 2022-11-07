package com.ifind.simplehttp;

import com.ifind.simplehttp.annotation.SHController;
import com.ifind.simplehttp.annotation.SHMapping;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class Controller implements SHController {
    @SHMapping(path = "/register", method = "POST")
    public String register(String content) {
        Map<String, String> params = getParams(content);
        String subscribeTableName = params.getOrDefault("subscribeTableName", null);
        String subscribeTableField = params.getOrDefault("subscribeTableField", null);
        String srcType = params.getOrDefault("srcType", null);
        String srcContent = params.getOrDefault("srcContent", null);
        if (!checkNull(subscribeTableName, subscribeTableField, srcType, srcContent)) {
            throw new RuntimeException("params error");
        }
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
