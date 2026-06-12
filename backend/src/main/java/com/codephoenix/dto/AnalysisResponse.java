package com.codephoenix.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnalysisResponse {

    private Long analysisId;
    private Integer healthScore;
    private String riskLevel;
    private String message;

}