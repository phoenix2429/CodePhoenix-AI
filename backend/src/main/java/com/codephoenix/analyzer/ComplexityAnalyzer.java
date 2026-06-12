package com.codephoenix.analyzer;

import com.codephoenix.parser.JavaClassModel;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ComplexityAnalyzer {

    public int analyze(JavaClassModel classModel) {
        String code = classModel.getContent();
        if (code == null || code.isEmpty()) {
            return 1;
        }

        // Standard Cyclomatic/Cognitive complexity estimate
        int complexity = 1;
        
        // Match conditional/loop structures
        Pattern pattern = Pattern.compile("\\b(if|for|while|catch|case|&&|\\|\\|)\\b");
        Matcher matcher = pattern.matcher(code);
        while (matcher.find()) {
            complexity++;
        }

        return complexity;
    }
}
