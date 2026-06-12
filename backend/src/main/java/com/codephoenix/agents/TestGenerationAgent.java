package com.codephoenix.agents;

import com.codephoenix.ai.AIClient;
import com.codephoenix.ai.PromptManager;
import com.codephoenix.ai.ResponseParser;
import com.codephoenix.entity.AgentResult;
import com.codephoenix.parser.JavaClassModel;
import com.codephoenix.parser.JavaMethodModel;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TestGenerationAgent {

    public AgentResult analyze(JavaClassModel classModel, AIClient aiClient, 
                               PromptManager promptManager, ResponseParser responseParser) {
        log.info("TestGenerationAgent analyzing class: {}", classModel.getName());

        String summary = null;
        String severity = "LOW";

        String prompt = promptManager.buildTestPrompt(classModel.getName(), classModel.getContent());
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
            sb.append("JUnit 5 Test Recommendations for Class: ").append(classModel.getName()).append("\n\n");
            
            sb.append("package ").append(classModel.getPackageName()).append(";\n\n");
            sb.append("import org.junit.jupiter.api.Test;\n");
            sb.append("import static org.junit.jupiter.api.Assertions.*;\n\n");
            sb.append("class ").append(classModel.getName()).append("Test {\n\n");

            if (classModel.getMethods().isEmpty()) {
                sb.append("    @Test\n");
                sb.append("    void testInstantiation() {\n");
                sb.append("        ").append(classModel.getName()).append(" instance = new ").append(classModel.getName()).append("();\n");
                sb.append("        assertNotNull(instance);\n");
                sb.append("    }\n");
            } else {
                for (JavaMethodModel method : classModel.getMethods()) {
                    String methodName = method.getName();
                    // Capitalize first letter of method
                    String capName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
                    
                    sb.append("    @Test\n");
                    sb.append("    void test").append(capName).append("() {\n");
                    sb.append("        // TODO: Initialize required parameters and verify return value\n");
                    sb.append("        // ").append(classModel.getName()).append(" target = new ").append(classModel.getName()).append("();\n");
                    sb.append("        // var result = target.").append(methodName).append("(");
                    for (int i = 0; i < method.getParameters().size(); i++) {
                        sb.append("null");
                        if (i < method.getParameters().size() - 1) sb.append(", ");
                    }
                    sb.append(");\n");
                    if (!method.getReturnType().equals("void")) {
                        sb.append("        // assertNotNull(result);\n");
                    }
                    sb.append("        assertTrue(true);\n");
                    sb.append("    }\n\n");
                }
            }

            sb.append("}\n");
            summary = sb.toString();
        }

        return AgentResult.builder()
                .agentType("TEST")
                .summary(summary)
                .severity(severity)
                .build();
    }
}
