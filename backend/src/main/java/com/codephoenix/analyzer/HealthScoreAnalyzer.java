package com.codephoenix.analyzer;

import com.codephoenix.entity.Analysis;
import com.codephoenix.entity.TechnicalDebt;
import com.codephoenix.parser.JavaClassModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HealthScoreAnalyzer {

    private final ComplexityAnalyzer complexityAnalyzer;
    private final MaintainabilityAnalyzer maintainabilityAnalyzer;
    private final SecurityScoreAnalyzer securityScoreAnalyzer;
    private final TechnicalDebtAnalyzer technicalDebtAnalyzer;

    public int calculate(Analysis analysis, List<JavaClassModel> classes) {
        if (classes == null || classes.isEmpty()) {
            analysis.setHealthScore(100);
            analysis.setRiskLevel("LOW");
            return 100;
        }

        int totalMaintainability = 0;
        int totalSecurity = 0;
        
        for (JavaClassModel clazz : classes) {
            int complexity = complexityAnalyzer.analyze(clazz);
            int maintainability = maintainabilityAnalyzer.analyze(clazz, complexity);
            int security = securityScoreAnalyzer.analyze(clazz);
            
            totalMaintainability += maintainability;
            totalSecurity += security;

            // Generate Technical Debt entry
            TechnicalDebt debt = technicalDebtAnalyzer.analyze(clazz, complexity, maintainability, security);
            debt.setAnalysis(analysis);
            analysis.getTechnicalDebts().add(debt);
        }

        int avgMaintainability = totalMaintainability / classes.size();
        int avgSecurity = totalSecurity / classes.size();

        int healthScore = (avgMaintainability + avgSecurity) / 2;
        
        analysis.setHealthScore(healthScore);
        
        if (healthScore < 50) {
            analysis.setRiskLevel("CRITICAL");
        } else if (healthScore < 75) {
            analysis.setRiskLevel("HIGH");
        } else if (healthScore < 90) {
            analysis.setRiskLevel("MEDIUM");
        } else {
            analysis.setRiskLevel("LOW");
        }

        return healthScore;
    }
}
