package com.codephoenix.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TechnicalDebtDTO {

    private Integer debtScore;
    private String description;

}