package com.codephoenix.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AgentResultDTO {

    private String agentType;
    private String severity;
    private String summary;

}