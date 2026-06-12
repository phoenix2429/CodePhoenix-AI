package com.codephoenix.ai;

import org.springframework.stereotype.Component;

@Component
public class PromptManager {

    public String buildBugPrompt(String className, String sourceCode) {
        return "Analyze the following Java class '" + className + "' for bugs, null pointer risks, logic flaws, and potential defects. " +
                "Provide a brief summary and severity level (CRITICAL, HIGH, MEDIUM, LOW) in JSON format: " +
                "{\"summary\": \"Analysis summary listing found issues\", \"severity\": \"HIGH\"}\n\n" +
                "Source code:\n" + sourceCode;
    }

    public String buildSecurityPrompt(String className, String sourceCode) {
        return "Analyze the following Java class '" + className + "' for security vulnerabilities such as SQL injection, XSS, hardcoded secrets, and unsafe APIs. " +
                "Provide a brief summary and severity level (CRITICAL, HIGH, MEDIUM, LOW) in JSON format: " +
                "{\"summary\": \"Security audit findings\", \"severity\": \"CRITICAL\"}\n\n" +
                "Source code:\n" + sourceCode;
    }

    public String buildPerformancePrompt(String className, String sourceCode) {
        return "Analyze the following Java class '" + className + "' for performance bottlenecks, inefficient loops, memory concerns, and expensive operations. " +
                "Provide a brief summary and severity level (CRITICAL, HIGH, MEDIUM, LOW) in JSON format: " +
                "{\"summary\": \"Performance bottlenecks summary\", \"severity\": \"MEDIUM\"}\n\n" +
                "Source code:\n" + sourceCode;
    }

    public String buildTestPrompt(String className, String sourceCode) {
        return "Analyze the following Java class '" + className + "' and generate JUnit 5 test recommendations and coverage suggestions. " +
                "Provide a brief summary and severity level (CRITICAL, HIGH, MEDIUM, LOW) in JSON format: " +
                "{\"summary\": \"JUnit test recommendations and templates\", \"severity\": \"LOW\"}\n\n" +
                "Source code:\n" + sourceCode;
    }

    public String buildModernizationPrompt(String className, String sourceCode) {
        return "Analyze the following Java class '" + className + "' for legacy API usage (e.g. Vector, Hashtable, SimpleDateFormat), outdated libraries, or legacy Java version patterns. " +
                "Provide the legacy technology, recommended technology, and migration notes in JSON format: " +
                "{\"legacyTechnology\": \"Vector\", \"recommendedTechnology\": \"ArrayList\", \"migrationNotes\": \"Replace Vector with ArrayList for thread-unsafe operations to boost speed\"}\n\n" +
                "Source code:\n" + sourceCode;
    }

    public String buildDocPrompt(String className, String sourceCode) {
        return "Analyze the following Java class '" + className + "' and generate a README style markdown documentation summarizing its purpose, public methods, and APIs.\n\n" +
                "Source code:\n" + sourceCode;
    }
}
