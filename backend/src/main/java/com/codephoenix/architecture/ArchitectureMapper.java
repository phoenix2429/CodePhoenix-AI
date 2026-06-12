package com.codephoenix.architecture;

import com.codephoenix.entity.ArchitectureData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ArchitectureMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Map<String, Object> toMap(ArchitectureData data) {
        Map<String, Object> result = new HashMap<>();
        if (data == null) {
            return result;
        }

        try {
            result.put("nodes", objectMapper.readValue(data.getNodesJson(), Object.class));
            result.put("links", objectMapper.readValue(data.getLinksJson(), Object.class));
        } catch (Exception e) {
            result.put("nodes", data.getNodesJson());
            result.put("links", data.getLinksJson());
        }
        return result;
    }
}
