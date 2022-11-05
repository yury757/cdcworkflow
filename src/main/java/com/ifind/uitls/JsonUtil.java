package com.ifind.uitls;

import com.fasterxml.jackson.core.TreeCodec;
import com.fasterxml.jackson.databind.ObjectMapper;

public enum JsonUtil {
    INSTANCE;
    private ObjectMapper mapper;
    private JsonUtil() {
        this.mapper = new ObjectMapper();
    }

    public static ObjectMapper getMapper() {
        return INSTANCE.mapper;
    }
}
