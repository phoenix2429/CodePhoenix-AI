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
public class SecurityAgent {

    public AgentResult analyze(JavaClassModel classModel, AIClient aiClient, 
                               PromptManager promptManager, ResponseParser responseParser) {
        log.info("SecurityAgent analyzing class: {}", classModel.getName());

        String summary = null;
        String severity = "LOW";

        String prompt = promptManager.buildSecurityPrompt(classModel.getName(), classModel.getContent());
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
            sb.append("Rule-Based Security Scan Report for Class: ").append(classModel.getName()).append("\n");

            String code = classModel.getContent();
            boolean found = false;

            if (code.contains("select ") || code.contains("SELECT ") || code.contains("insert ") || code.contains("INSERT ")) {
                if (code.contains(" + ") && (code.contains("where") || code.contains("WHERE") || code.contains("values") || code.contains("VALUES"))) {
                    sb.append("- Critical issue: Potential SQL Injection vulnerability detected via query string concatenation. Recommend using PreparedStatement or JPA parameters.\n");
                    severity = "CRITICAL";
                    found = true;
                }
            }

            if (code.contains("apiKey") || code.contains("api_key") || code.contains("secret") || code.contains("password") || code.contains("token")) {
                if (code.matches("(?s).*?(private|public|protected|)\\s+String\\s+\\w*(password|secret|key|token)\\w*\\s*=\\s*\"[^\"]+\".*?")) {
                    sb.append("- High issue: Hardcoded credentials or API keys found in member variables.\n");
                    severity = "HIGH";
                    found = true;
                }
            }

            if (code.contains("getInstance(\"MD5\")") || code.contains("getInstance(\"SHA-1\")")) {
                sb.append("- Critical issue: Obsolete/insecure cryptographic hashing algorithm (MD5/SHA-1) in use. Recommend upgrading to SHA-256 or bcrypt.\n");
                severity = "CRITICAL";
                found = true;
            }

            if (!found) {
                sb.append("- No obvious OWASP security flaws found via static pattern signatures.\n");
            }
            summary = sb.toString();
        }

        return AgentResult.builder()
                .agentType("SECURITY")
                .summary(summary)
                .severity(severity)
                .build();
    }
}
