package com.codephoenix.controller;

import com.codephoenix.dto.AnalysisRequest;
import com.codephoenix.dto.AnalysisResponse;
import com.codephoenix.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analysis")
@RequiredArgsConstructor
public class AnalysisController {

    private final AnalysisService analysisService;

    @PostMapping("/start")
    public AnalysisResponse analyze(
            @RequestBody AnalysisRequest request) {

        return analysisService.startAnalysis(request);
    }
}