package com.codephoenix.impact;

import com.codephoenix.entity.Analysis;
import com.codephoenix.entity.ImpactAnalysis;
import com.codephoenix.parser.JavaClassModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class ChangeImpactAnalyzer {

    private final DependencyImpactAnalyzer dependencyImpactAnalyzer;
    private final RiskPredictor riskPredictor;

    public void analyze(Analysis analysis, List<JavaClassModel> classes) {
        log.info("Running change impact analysis for analysis ID: {}", analysis.getId());

        for (JavaClassModel clazz : classes) {
            Set<String> affected = dependencyImpactAnalyzer.analyzeTransitiveImpact(clazz.getName(), classes);
            String risk = riskPredictor.predictRisk(affected.size());

            String affectedModules = String.join(", ", affected);
            if (affectedModules.isEmpty()) {
                affectedModules = "None";
            }

            ImpactAnalysis impact = ImpactAnalysis.builder()
                    .component(clazz.getName())
                    .impactLevel(risk)
                    .affectedModules(affectedModules)
                    .analysis(analysis)
                    .build();

            analysis.getImpactAnalyses().add(impact);
        }
    }
}
