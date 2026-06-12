package com.codephoenix.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModernizationDTO {

    private String legacyTechnology;
    private String recommendedTechnology;
    private String migrationNotes;

}