package com.codephoenix.analyzer;

import com.codephoenix.parser.JavaClassModel;
import org.springframework.stereotype.Component;

@Component
public class MaintainabilityAnalyzer {

    public int analyze(JavaClassModel classModel, int complexity) {
        String code = classModel.getContent();
        if (code == null || code.isEmpty()) {
            return 100;
        }

        // Count lines of code (LOC)
        int loc = code.split("\r\n|\r|\n").length;
        
        // Count comments roughly
        int commentCount = 0;
        for (String line : code.split("\n")) {
            if (line.trim().startsWith("//") || line.trim().startsWith("*") || line.trim().startsWith("/*")) {
                commentCount++;
            }
        }

        // Formula: 100 - (loc / 8) - (complexity * 1.5) + (commentCount / 3)
        double score = 100.0 - (loc / 8.0) - (complexity * 1.5) + (commentCount / 3.0);

        // Clamp between 0 and 100
        int finalScore = (int) Math.round(score);
        return Math.max(0, Math.min(100, finalScore));
    }
}
