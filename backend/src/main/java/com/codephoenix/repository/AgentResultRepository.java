package com.codephoenix.repository;

import com.codephoenix.entity.AgentResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgentResultRepository extends JpaRepository<AgentResult, Long> {
    List<AgentResult> findByAnalysisId(Long analysisId);
}