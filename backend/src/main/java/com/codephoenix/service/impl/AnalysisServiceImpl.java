package com.codephoenix.service.impl;

import com.codephoenix.agents.AgentOrchestrator;
import com.codephoenix.analyzer.HealthScoreAnalyzer;
import com.codephoenix.architecture.DependencyGraphBuilder;
import com.codephoenix.dto.AnalysisRequest;
import com.codephoenix.dto.AnalysisResponse;
import com.codephoenix.entity.Analysis;
import com.codephoenix.entity.Project;
import com.codephoenix.impact.ChangeImpactAnalyzer;
import com.codephoenix.repository.AnalysisRepository;
import com.codephoenix.repository.ProjectRepository;
import com.codephoenix.scanner.ProjectScanner;
import com.codephoenix.scanner.ScannedProjectModel;
import com.codephoenix.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnalysisServiceImpl
        implements AnalysisService {

    private final ProjectRepository projectRepository;
    private final AnalysisRepository analysisRepository;
    private final AgentOrchestrator agentOrchestrator;
    private final ChangeImpactAnalyzer changeImpactAnalyzer;
    private final DependencyGraphBuilder dependencyGraphBuilder;
    private final HealthScoreAnalyzer healthScoreAnalyzer;

    @Override
    @Transactional
    public AnalysisResponse startAnalysis(
            AnalysisRequest request) {

        log.info("Starting analysis for project ID: {}", request.getProjectId());

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        project.setStatus("ANALYZING");
        projectRepository.save(project);

        try {
            // 1. Scan codebase
            File projectDir = new File(project.getExtractedPath());
            ScannedProjectModel scannedProject = ProjectScanner.scan(projectDir);

            // 2. Initialize Analysis Run
            Analysis analysis = new Analysis();
            analysis.setProject(project);
            analysis.setAnalysisDate(LocalDateTime.now());
            
            // Save initial record to get ID for child mappings
            analysis = analysisRepository.save(analysis);

            // 3. Orchestrate Multi-Agent analysis
            agentOrchestrator.orchestrate(analysis, scannedProject.getClasses());

            // 4. Run Change Impact predictions
            changeImpactAnalyzer.analyze(analysis, scannedProject.getClasses());

            // 5. Reverse engineer dependency call graph
            dependencyGraphBuilder.buildGraph(analysis, scannedProject.getClasses());

            // 6. Calculate Technical Debt & Health Scores
            healthScoreAnalyzer.calculate(analysis, scannedProject.getClasses());

            // Save final analysis cascade
            Analysis savedAnalysis = analysisRepository.save(analysis);

            project.setStatus("COMPLETED");
            projectRepository.save(project);

            log.info("Analysis completed successfully for project ID: {}", project.getId());

            return AnalysisResponse.builder()
                    .analysisId(savedAnalysis.getId())
                    .healthScore(savedAnalysis.getHealthScore())
                    .riskLevel(savedAnalysis.getRiskLevel())
                    .message("Codebase analysis completed successfully.")
                    .build();

        } catch (Exception e) {
            log.error("Failed to analyze project ID: {}", project.getId(), e);
            project.setStatus("FAILED");
            projectRepository.save(project);
            throw new RuntimeException("Analysis execution failed: " + e.getMessage(), e);
        }
    }
}