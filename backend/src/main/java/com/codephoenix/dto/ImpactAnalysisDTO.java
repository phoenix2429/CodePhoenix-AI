package com.codephoenix.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImpactAnalysisDTO {

    private String component;
    private String impactLevel;
    private String affectedModules;

}