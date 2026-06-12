package com.codephoenix.agents;

import com.codephoenix.ai.AIClient;
import com.codephoenix.ai.PromptManager;
import com.codephoenix.ai.ResponseParser;
import com.codephoenix.entity.AgentResult;
import com.codephoenix.parser.JavaClassModel;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PerformanceAgent {

    public AgentResult analyze(JavaClassModel classModel, AIClient aiClient, 
                               PromptManager promptManager, ResponseParser responseParser) {
        log.info("PerformanceAgent analyzing class: {}", classModel.getName());

        String summary = null;
        String severity = "LOW";

        String prompt = promptManager.buildPerformancePrompt(classModel.getName(), classModel.getContent());
        String response = aiClient.generate(prompt);

        if (response != null) {
            JsonNode json = responseParser.parseJson(response);
            if (json != null) {
                summary = json.path("summary").asText();
                severity = json.path("severity").asText("LOW").toUpperCase();
            }
        }

        if (summary == null || summary.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Rule-Based Performance Scan Report for Class: ").append(classModel.getName()).append("\n");

            String code = classModel.getContent();
            boolean found = false;

            if (code.contains("for") || code.contains("while")) {
                if (code.matches("(?s).*?(for|while)\\s*\\(.*?\\)\\s*\\{.*?\\+\\s*\\\".*?\\}.*?")) {
                    sb.append("- Medium issue: Inefficient String concatenation inside a loop. Use StringBuilder to avoid massive object allocations.\n");
                    severity = "MEDIUM";
                    found = true;
                }
            }

            if (code.contains("Vector") || code.contains("Hashtable")) {
                sb.append("- Low issue: Synchronized legacy collection type (Vector or Hashtable) in use. Upgrade to ArrayList, HashMap, or ConcurrentHashMap for thread safety without execution locks.\n");
                severity = "LOW";
                found = true;
            }

            if (code.contains("Thread.sleep")) {
                sb.append("- Medium issue: Thread.sleep() block detected, which halts thread execution. Recommend using proper asynchronous scheduler or thread pools.\n");
                severity = "MEDIUM";
                found = true;
            }

            if (!found) {
                sb.append("- No immediate computational performance issues detected.\n");
            }
            summary = sb.toString();
        }

        return AgentResult.builder()
                .agentType("PERFORMANCE")
                .summary(summary)
                .severity(severity)
                .build();
    }
}
