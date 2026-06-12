package com.codephoenix.dto;

import lombok.Data;

@Data
public class UploadProjectRequest {

    private String projectName;
    private String description;

}