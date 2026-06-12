package com.codephoenix.service.impl;

import com.codephoenix.dto.AgentResultDTO;
import com.codephoenix.entity.AgentResult;
import com.codephoenix.repository.AgentResultRepository;
import com.codephoenix.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl
        implements AgentService {

    private final AgentResultRepository agentResultRepository;

    @Override
    public List<AgentResultDTO> getResults(
            Long analysisId) {

        List<AgentResult> results = agentResultRepository.findByAnalysisId(analysisId);
        
        return results.stream()
                .map(r -> AgentResultDTO.builder()
                        .agentType(r.getAgentType())
                        .severity(r.getSeverity())
                        .summary(r.getSummary())
                        .build())
                .collect(Collectors.toList());
    }
}