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
public class BugAgent {

    public AgentResult analyze(JavaClassModel classModel, AIClient aiClient, 
                               PromptManager promptManager, ResponseParser responseParser) {
        log.info("BugAgent analyzing class: {}", classModel.getName());
        
        String summary = null;
        String severity = "LOW";

        // Attempt LLM execution
        String prompt = promptManager.buildBugPrompt(classModel.getName(), classModel.getContent());
        String response = aiClient.generate(prompt);

        if (response != null) {
            JsonNode json = responseParser.parseJson(response);
            if (json != null) {
                summary = json.path("summary").asText();
                severity = json.path("severity").asText("LOW").toUpperCase();
            }
        }

        // Fallback to Rule-based scanner
        if (summary == null || summary.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Rule-Based Bug Detection Report for Class: ").append(classModel.getName()).append("\n");
            
            String code = classModel.getContent();
            boolean found = false;

            if (code.contains("== \"") || code.contains("== \"\"") || code.contains(" == null") && !code.contains("if (")) {
                sb.append("- Potential issue: String comparison using '==' instead of '.equals()'.\n");
                severity = "MEDIUM";
                found = true;
            }
            if (code.contains("catch (Exception e) {}") || code.contains("catch(Exception e){}")) {
                sb.append("- Critical issue: Empty catch block hides potential exceptions.\n");
                severity = "HIGH";
                found = true;
            }
            if (code.contains("System.out.println")) {
                sb.append("- Warning: Code contains System.out.println statement. Recommend using structured SLF4J logging.\n");
                found = true;
            }

            if (!found) {
                sb.append("- No immediate logical defects detected in source structure.\n");
            }
            summary = sb.toString();
        }

        return AgentResult.builder()
                .agentType("BUG")
                .summary(summary)
                .severity(severity)
                .build();
    }
}
