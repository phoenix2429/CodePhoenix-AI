package com.codephoenix.agents;

import com.codephoenix.ai.AIClient;
import com.codephoenix.ai.PromptManager;
import com.codephoenix.ai.ResponseParser;
import com.codephoenix.entity.ModernizationSuggestion;
import com.codephoenix.parser.JavaClassModel;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ModernizationAgent {

    public List<ModernizationSuggestion> analyze(JavaClassModel classModel, AIClient aiClient, 
                                                 PromptManager promptManager, ResponseParser responseParser) {
        log.info("ModernizationAgent analyzing class: {}", classModel.getName());
        List<ModernizationSuggestion> suggestions = new ArrayList<>();

        String prompt = promptManager.buildModernizationPrompt(classModel.getName(), classModel.getContent());
        String response = aiClient.generate(prompt);

        if (response != null) {
            JsonNode json = responseParser.parseJson(response);
            if (json != null) {
                ModernizationSuggestion suggestion = ModernizationSuggestion.builder()
                        .legacyTechnology(json.path("legacyTechnology").asText())
                        .recommendedTechnology(json.path("recommendedTechnology").asText())
                        .migrationNotes(json.path("migrationNotes").asText())
                        .build();
                suggestions.add(suggestion);
            }
        }

        if (suggestions.isEmpty()) {
            String code = classModel.getContent();

            if (code.contains("Vector")) {
                suggestions.add(ModernizationSuggestion.builder()
                        .legacyTechnology("Vector")
                        .recommendedTechnology("ArrayList / CopyOnWriteArrayList")
                        .migrationNotes("Vector is synchronized on every operation, causing thread blocks. Use ArrayList for thread-local usage, or CopyOnWriteArrayList for concurrent reads.")
                        .build());
            }
            if (code.contains("Hashtable")) {
                suggestions.add(ModernizationSuggestion.builder()
                        .legacyTechnology("Hashtable")
                        .recommendedTechnology("HashMap / ConcurrentHashMap")
                        .migrationNotes("Hashtable locks the entire map during access. Replace with ConcurrentHashMap for lock-free parallel execution.")
                        .build());
            }
            if (code.contains("SimpleDateFormat")) {
                suggestions.add(ModernizationSuggestion.builder()
                        .legacyTechnology("SimpleDateFormat")
                        .recommendedTechnology("java.time.format.DateTimeFormatter")
                        .migrationNotes("SimpleDateFormat is not thread-safe and can cause date formatting issues. Use DateTimeFormatter which is immutable and thread-safe.")
                        .build());
            }
            if (code.contains("java.util.Date") || code.contains("java.util.Calendar")) {
                suggestions.add(ModernizationSuggestion.builder()
                        .legacyTechnology("java.util.Date")
                        .recommendedTechnology("java.time.LocalDate / LocalDateTime")
                        .migrationNotes("Legacy Date/Calendar APIs are mutable and poorly designed. Use LocalDate/LocalDateTime for correct offset handling and immutability.")
                        .build());
            }
        }

        return suggestions;
    }
}
