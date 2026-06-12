package com.codephoenix.service;

import com.codephoenix.dto.AgentResultDTO;

import java.util.List;

public interface AgentService {

    List<AgentResultDTO> getResults(
            Long analysisId
    );
}