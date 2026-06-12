package com.codephoenix.analyzer;

import com.codephoenix.entity.TechnicalDebt;
import com.codephoenix.parser.JavaClassModel;
import org.springframework.stereotype.Component;

@Component
public class TechnicalDebtAnalyzer {

    public TechnicalDebt analyze(JavaClassModel classModel, int complexity, int maintainability, int securityScore) {
        int debtScore = 0;
        StringBuilder desc = new StringBuilder();
        desc.append("Technical Debt Analysis for class: ").append(classModel.getName()).append(".\n");

        if (complexity > 10) {
            debtScore += (complexity - 10) * 5;
            desc.append("- Branch complexity is high (").append(complexity).append("). Recommend simplifying control flows.\n");
        }
        
        if (maintainability < 80) {
            debtScore += (80 - maintainability) * 3;
            desc.append("- Maintainability index is low (").append(maintainability).append("/100). Code is too dense or lacks comments.\n");
        }
        
        if (securityScore < 90) {
            debtScore += (90 - securityScore) * 6;
            desc.append("- Security health is compromised (").append(securityScore).append("/100). Vulnerabilities need remediation.\n");
        }

        if (debtScore == 0) {
            desc.append("- Code is clean and matches modern standards. Negligible debt.\n");
        }

        debtScore = Math.min(100, debtScore);

        return TechnicalDebt.builder()
                .debtScore(debtScore)
                .description(desc.toString())
                .build();
    }
}
