package com.codephoenix.report;

import com.codephoenix.entity.Analysis;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DashboardReportGenerator {

    public Map<String, Object> generateDashboardData(Analysis analysis) {
        Map<String, Object> data = new HashMap<>();
        data.put("id", analysis.getId());
        data.put("healthScore", analysis.getHealthScore());
        data.put("riskLevel", analysis.getRiskLevel());
        data.put("analysisDate", analysis.getAnalysisDate());
        
        long criticalCount = analysis.getAgentResults().stream().filter(r -> r.getSeverity().equals("CRITICAL")).count();
        long highCount = analysis.getAgentResults().stream().filter(r -> r.getSeverity().equals("HIGH")).count();
        long mediumCount = analysis.getAgentResults().stream().filter(r -> r.getSeverity().equals("MEDIUM")).count();
        long lowCount = analysis.getAgentResults().stream().filter(r -> r.getSeverity().equals("LOW")).count();

        Map<String, Object> severityMetrics = new HashMap<>();
        severityMetrics.put("critical", criticalCount);
        severityMetrics.put("high", highCount);
        severityMetrics.put("medium", mediumCount);
        severityMetrics.put("low", lowCount);
        data.put("severityMetrics", severityMetrics);

        return data;
    }
}
