package com.codephoenix.controller;

import com.codephoenix.dto.AgentResultDTO;
import com.codephoenix.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agents")
@RequiredArgsConstructor
public class AgentController {

    private final AgentService agentService;

    @GetMapping("/{analysisId}")
    public List<AgentResultDTO> getResults(
            @PathVariable Long analysisId) {

        return agentService.getResults(analysisId);
    }
}