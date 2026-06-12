package com.codephoenix.agents;

import com.codephoenix.ai.AIClient;
import com.codephoenix.ai.PromptManager;
import com.codephoenix.ai.ResponseParser;
import com.codephoenix.entity.AgentResult;
import com.codephoenix.entity.Analysis;
import com.codephoenix.entity.ModernizationSuggestion;
import com.codephoenix.parser.JavaClassModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
@Slf4j
public class AgentOrchestrator {

    private final BugAgent bugAgent;
    private final SecurityAgent securityAgent;
    private final PerformanceAgent performanceAgent;
    private final TestGenerationAgent testGenerationAgent;
    private final ModernizationAgent modernizationAgent;
    
    private final AIClient aiClient;
    private final PromptManager promptManager;
    private final ResponseParser responseParser;

    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    public void orchestrate(Analysis analysis, List<JavaClassModel> classes) {
        log.info("Starting agent orchestration for analysis ID: {} with {} classes", analysis.getId(), classes.size());

        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (JavaClassModel clazz : classes) {
            CompletableFuture<Void> classFuture = CompletableFuture.runAsync(() -> {
                try {
                    // 1. Run Bug Agent
                    AgentResult bugRes = bugAgent.analyze(clazz, aiClient, promptManager, responseParser);
                    bugRes.setAnalysis(analysis);
                    synchronized (analysis) {
                        analysis.getAgentResults().add(bugRes);
                    }

                    // 2. Run Security Agent
                    AgentResult secRes = securityAgent.analyze(clazz, aiClient, promptManager, responseParser);
                    secRes.setAnalysis(analysis);
                    synchronized (analysis) {
                        analysis.getAgentResults().add(secRes);
                    }

                    // 3. Run Performance Agent
                    AgentResult perfRes = performanceAgent.analyze(clazz, aiClient, promptManager, responseParser);
                    perfRes.setAnalysis(analysis);
                    synchronized (analysis) {
                        analysis.getAgentResults().add(perfRes);
                    }

                    // 4. Run Test Agent
                    AgentResult testRes = testGenerationAgent.analyze(clazz, aiClient, promptManager, responseParser);
                    testRes.setAnalysis(analysis);
                    synchronized (analysis) {
                        analysis.getAgentResults().add(testRes);
                    }

                    // 5. Run Modernization Agent
                    List<ModernizationSuggestion> modSuggestions = modernizationAgent.analyze(clazz, aiClient, promptManager, responseParser);
                    for (ModernizationSuggestion mod : modSuggestions) {
                        mod.setAnalysis(analysis);
                        synchronized (analysis) {
                            analysis.getModernizationSuggestions().add(mod);
                        }
                    }
                } catch (Exception e) {
                    log.error("Error running agents for class {}", clazz.getName(), e);
                }
            }, executor);

            futures.add(classFuture);
        }

        // Wait for all class analyses to finish
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        log.info("Agent orchestration completed for analysis ID: {}", analysis.getId());
    }
}
