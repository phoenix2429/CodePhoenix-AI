package com.codephoenix.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "agent_results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String agentType;

    @Column(length = 5000)
    private String summary;

    private String severity;

    @ManyToOne
    @JoinColumn(name = "analysis_id")
    private Analysis analysis;
}