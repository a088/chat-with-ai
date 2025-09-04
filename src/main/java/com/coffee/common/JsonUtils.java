package com.coffee.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String parseDeltaContent(String json) {
        try {
            JsonNode root = mapper.readTree(json);
            JsonNode choices = root.path("choices");
            if (choices.isArray() && choices.size() > 0) {
                JsonNode delta = choices.get(0).path("delta");
                if (delta.has("content")) {
                    return delta.get("content").asText();
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
