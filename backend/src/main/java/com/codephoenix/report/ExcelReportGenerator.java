package com.codephoenix.report;

import com.codephoenix.entity.Analysis;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;

@Component
public class ExcelReportGenerator {

    public String generate(Analysis analysis) {
        String csvPath = "reports/report-" + analysis.getId() + ".csv";
        try {
            File reportDir = new File("reports");
            if (!reportDir.exists()) {
                reportDir.mkdirs();
            }

            try (FileWriter csvWriter = new FileWriter(csvPath)) {
                csvWriter.append("CodePhoenix AI - Technical Report\n");
                csvWriter.append("Analysis ID,").append(String.valueOf(analysis.getId())).append("\n");
                csvWriter.append("Health Score,").append(String.valueOf(analysis.getHealthScore())).append("\n");
                csvWriter.append("Risk Level,").append(analysis.getRiskLevel()).append("\n\n");

                csvWriter.append("Agent Type,Severity,Summary\n");
                analysis.getAgentResults().forEach(res -> {
                    try {
                        csvWriter.append(res.getAgentType()).append(",")
                                .append(res.getSeverity()).append(",")
                                .append("\"").append(res.getSummary().replace("\"", "\"\"")).append("\"\n");
                    } catch (Exception e) {}
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return csvPath;
    }
}
