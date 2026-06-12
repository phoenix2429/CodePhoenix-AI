package com.codephoenix.service.impl;

import com.codephoenix.entity.Analysis;
import com.codephoenix.entity.Report;
import com.codephoenix.repository.AnalysisRepository;
import com.codephoenix.repository.ReportRepository;
import com.codephoenix.report.PdfReportGenerator;
import com.codephoenix.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final AnalysisRepository analysisRepository;
    private final PdfReportGenerator pdfReportGenerator;

    @Override
    @Transactional
    public Report generateReport(Long analysisId) {
        log.info("Generating report for analysis ID: {}", analysisId);

        Analysis analysis = analysisRepository.findById(analysisId)
                .orElseThrow(() -> new IllegalArgumentException("Analysis run not found"));

        // Generate PDF
        String pdfPath = pdfReportGenerator.generate(analysis);

        // Save or update Report record
        Report report = reportRepository.findByAnalysisId(analysisId)
                .orElse(new Report());

        report.setAnalysis(analysis);
        report.setPdfPath(pdfPath);
        report.setGeneratedAt(LocalDateTime.now());

        Report savedReport = reportRepository.save(report);
        
        // Also update analysis reference
        analysis.setReport(savedReport);
        analysisRepository.save(analysis);

        return savedReport;
    }
}