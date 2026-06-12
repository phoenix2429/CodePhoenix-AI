package com.codephoenix.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AIResponseParser {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static JsonNode parseJson(String aiResponse) {
        if (aiResponse == null || aiResponse.isEmpty()) {
            return null;
        }

        try {
            String cleanJson = aiResponse.trim();
            if (cleanJson.startsWith("```")) {
                int firstNewLine = cleanJson.indexOf('\n');
                int lastFence = cleanJson.lastIndexOf("```");
                if (firstNewLine != -1 && lastFence != -1 && lastFence > firstNewLine) {
                    cleanJson = cleanJson.substring(firstNewLine, lastFence).trim();
                }
            }
            if (cleanJson.startsWith("json")) {
                cleanJson = cleanJson.substring(4).trim();
            }

            return objectMapper.readTree(cleanJson);
        } catch (Exception e) {
            log.error("Failed to parse JSON response in AIResponseParser: {}", aiResponse, e);
        }
        return null;
    }
}
