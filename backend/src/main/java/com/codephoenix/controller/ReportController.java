package com.codephoenix.controller;

import com.codephoenix.entity.Report;
import com.codephoenix.service.ReportService;
import com.codephoenix.report.ExcelReportGenerator;
import com.codephoenix.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;
    private final ReportRepository reportRepository;
    private final ExcelReportGenerator excelReportGenerator;

    @GetMapping("/{analysisId}")
    public Report getReport(
            @PathVariable Long analysisId) {

        return reportService.generateReport(
                analysisId
        );
    }

    @GetMapping("/{analysisId}/download")
    public ResponseEntity<Resource> downloadPdf(@PathVariable Long analysisId) {
        Report report = reportRepository.findByAnalysisId(analysisId)
                .orElseThrow(() -> new IllegalArgumentException("Report not found"));

        File file = new File(report.getPdfPath());
        if (!file.exists()) {
            // Generate it if missing
            reportService.generateReport(analysisId);
            file = new File(report.getPdfPath());
        }

        Resource resource = new FileSystemResource(file);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(resource);
    }

    @GetMapping("/{analysisId}/excel")
    public ResponseEntity<Resource> downloadExcel(@PathVariable Long analysisId) {
        Report report = reportRepository.findByAnalysisId(analysisId)
                .orElseThrow(() -> new IllegalArgumentException("Report not found"));

        String csvPath = excelReportGenerator.generate(report.getAnalysis());
        File file = new File(csvPath);

        Resource resource = new FileSystemResource(file);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("text/csv"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(resource);
    }
}