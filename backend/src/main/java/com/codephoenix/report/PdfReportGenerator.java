package com.codephoenix.report;

import com.codephoenix.entity.AgentResult;
import com.codephoenix.entity.Analysis;
import com.codephoenix.entity.ModernizationSuggestion;
import com.codephoenix.entity.TechnicalDebt;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;

@Component
@Slf4j
public class PdfReportGenerator {

    public String generate(Analysis analysis) {
        log.info("Generating PDF report for analysis ID: {}", analysis.getId());
        
        File reportDir = new File("reports");
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        String pdfPath = "reports/report-" + analysis.getId() + ".pdf";

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
            
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22, BaseColor.DARK_GRAY);
            Font sectionFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.BLUE);
            Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
            Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.BLACK);
            Font warningFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.RED);

            Paragraph title = new Paragraph("CodePhoenix AI - Executive Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            
            Paragraph tagline = new Paragraph("Reviving Legacy Systems Through Autonomous Engineering", FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 10, BaseColor.GRAY));
            tagline.setAlignment(Element.ALIGN_CENTER);
            document.add(tagline);
            
            document.add(Chunk.NEWLINE);

            PdfPTable infoTable = new PdfPTable(2);
            infoTable.setWidthPercentage(100);
            
            infoTable.addCell(new PdfPCell(new Phrase("Project Name:", boldFont)));
            infoTable.addCell(new PdfPCell(new Phrase(analysis.getProject().getProjectName(), normalFont)));
            
            infoTable.addCell(new PdfPCell(new Phrase("Analysis Date:", boldFont)));
            infoTable.addCell(new PdfPCell(new Phrase(analysis.getAnalysisDate().toString(), normalFont)));
            
            infoTable.addCell(new PdfPCell(new Phrase("Engineering Health Score:", boldFont)));
            infoTable.addCell(new PdfPCell(new Phrase(analysis.getHealthScore() + "/100", boldFont)));
            
            infoTable.addCell(new PdfPCell(new Phrase("Project Risk Level:", boldFont)));
            PdfPCell riskCell = new PdfPCell(new Phrase(analysis.getRiskLevel(), warningFont));
            infoTable.addCell(riskCell);

            document.add(infoTable);
            document.add(Chunk.NEWLINE);

            document.add(new Paragraph("1. Executive Summary", sectionFont));
            document.add(new Paragraph("CodePhoenix AI has performed an autonomous multi-agent analysis on this Java project. " +
                    "Based on structural parsing and security audits, the project's health index is calculated. A summary of agent diagnostics is outlined below.", normalFont));
            document.add(Chunk.NEWLINE);

            document.add(new Paragraph("2. Agent Diagnostics Summary", sectionFont));
            PdfPTable agentTable = new PdfPTable(3);
            agentTable.setWidthPercentage(100);
            agentTable.addCell(new PdfPCell(new Phrase("Agent Type", boldFont)));
            agentTable.addCell(new PdfPCell(new Phrase("Severity", boldFont)));
            agentTable.addCell(new PdfPCell(new Phrase("Findings Summary", boldFont)));

            for (AgentResult result : analysis.getAgentResults()) {
                agentTable.addCell(new PdfPCell(new Phrase(result.getAgentType(), normalFont)));
                agentTable.addCell(new PdfPCell(new Phrase(result.getSeverity(), result.getSeverity().equals("CRITICAL") || result.getSeverity().equals("HIGH") ? warningFont : normalFont)));
                
                String sum = result.getSummary();
                if (sum.length() > 100) sum = sum.substring(0, 97) + "...";
                agentTable.addCell(new PdfPCell(new Phrase(sum, normalFont)));
            }
            document.add(agentTable);
            document.add(Chunk.NEWLINE);

            document.add(new Paragraph("3. Technical Debt Breakdown", sectionFont));
            for (TechnicalDebt debt : analysis.getTechnicalDebts()) {
                Paragraph p = new Paragraph(debt.getDescription(), normalFont);
                document.add(p);
            }
            document.add(Chunk.NEWLINE);

            document.add(new Paragraph("4. Legacy Modernization Recommendations", sectionFont));
            if (analysis.getModernizationSuggestions().isEmpty()) {
                document.add(new Paragraph("No outdated legacy technologies were flagged in the source code.", normalFont));
            } else {
                PdfPTable modTable = new PdfPTable(3);
                modTable.setWidthPercentage(100);
                modTable.addCell(new PdfPCell(new Phrase("Legacy API", boldFont)));
                modTable.addCell(new PdfPCell(new Phrase("Modern Target", boldFont)));
                modTable.addCell(new PdfPCell(new Phrase("Upgrade Notes", boldFont)));

                for (ModernizationSuggestion suggestion : analysis.getModernizationSuggestions()) {
                    modTable.addCell(new PdfPCell(new Phrase(suggestion.getLegacyTechnology(), normalFont)));
                    modTable.addCell(new PdfPCell(new Phrase(suggestion.getRecommendedTechnology(), normalFont)));
                    modTable.addCell(new PdfPCell(new Phrase(suggestion.getMigrationNotes(), normalFont)));
                }
                document.add(modTable);
            }

            document.close();
            log.info("PDF report written successfully to {}", pdfPath);
        } catch (Exception e) {
            log.error("Failed to generate PDF report", e);
        }

        return pdfPath;
    }
}
