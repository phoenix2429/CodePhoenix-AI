package com.codephoenix.analyzer;

import com.codephoenix.parser.JavaClassModel;
import org.springframework.stereotype.Component;

@Component
public class SecurityScoreAnalyzer {

    public int analyze(JavaClassModel classModel) {
        String code = classModel.getContent();
        if (code == null || code.isEmpty()) {
            return 100;
        }

        int score = 100;

        // SQL injection check
        if ((code.contains("select ") || code.contains("SELECT ") || code.contains("insert ") || code.contains("INSERT ")) 
                && code.contains(" + ") && (code.contains("where") || code.contains("WHERE") || code.contains("values") || code.contains("VALUES"))) {
            score -= 35;
        }

        // Hardcoded credentials check
        if (code.contains("apiKey") || code.contains("api_key") || code.contains("secret") || code.contains("password")) {
            if (code.matches("(?s).*?(private|public|protected|)\\s+String\\s+\\w*(password|secret|key|token)\\w*\\s*=\\s*\"[^\"]+\".*?")) {
                score -= 30;
            }
        }

        // Weak hashing
        if (code.contains("getInstance(\"MD5\")") || code.contains("getInstance(\"SHA-1\")")) {
            score -= 25;
        }

        return Math.max(0, score);
    }
}
