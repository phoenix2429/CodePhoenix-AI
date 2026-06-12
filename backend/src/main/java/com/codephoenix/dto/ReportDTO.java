package com.codephoenix.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportDTO {

    private Long reportId;
    private String pdfPath;
    private String generatedAt;

}