package com.codephoenix.service;

import com.codephoenix.entity.Report;

public interface ReportService {

    Report generateReport(Long analysisId);

}