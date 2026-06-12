package com.codephoenix.architecture;

import com.codephoenix.entity.Analysis;
import com.codephoenix.entity.ArchitectureData;
import com.codephoenix.parser.JavaClassModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class DependencyGraphBuilder {

    private final LayerDetector layerDetector;
    private final RelationshipAnalyzer relationshipAnalyzer;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ArchitectureData buildGraph(Analysis analysis, List<JavaClassModel> classes) {
        log.info("Building dependency graph for analysis ID: {}", analysis.getId());
        
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> links = new ArrayList<>();

        for (JavaClassModel clazz : classes) {
            Map<String, Object> node = new HashMap<>();
            node.put("id", clazz.getName());
            node.put("type", layerDetector.detectLayer(clazz));
            node.put("package", clazz.getPackageName());
            nodes.add(node);
        }

        for (JavaClassModel clazz : classes) {
            List<String> targets = relationshipAnalyzer.findRelationships(clazz, classes);
            for (String target : targets) {
                Map<String, Object> link = new HashMap<>();
                link.put("source", clazz.getName());
                link.put("target", target);
                link.put("type", "calls");
                links.add(link);
            }
        }

        try {
            String nodesJson = objectMapper.writeValueAsString(nodes);
            String linksJson = objectMapper.writeValueAsString(links);

            ArchitectureData archData = ArchitectureData.builder()
                    .nodesJson(nodesJson)
                    .linksJson(linksJson)
                    .analysis(analysis)
                    .build();

            analysis.setArchitectureData(archData);
            return archData;

        } catch (Exception e) {
            log.error("Failed to build dependency graph JSON", e);
            throw new RuntimeException("Graph build failure", e);
        }
    }
}
