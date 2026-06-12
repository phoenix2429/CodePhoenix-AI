package com.codephoenix.service;

import com.codephoenix.dto.AnalysisRequest;
import com.codephoenix.dto.AnalysisResponse;

public interface AnalysisService {

    AnalysisResponse startAnalysis(
            AnalysisRequest request
    );
}