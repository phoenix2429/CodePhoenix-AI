package com.codephoenix.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "analysis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Analysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer healthScore;

    private String riskLevel;

    private LocalDateTime analysisDate;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "analysis", cascade = CascadeType.ALL)
    private List<AgentResult> agentResults = new ArrayList<>();

    @OneToMany(mappedBy = "analysis", cascade = CascadeType.ALL)
    private List<TechnicalDebt> technicalDebts = new ArrayList<>();

    @OneToMany(mappedBy = "analysis", cascade = CascadeType.ALL)
    private List<ImpactAnalysis> impactAnalyses = new ArrayList<>();

    @OneToMany(mappedBy = "analysis", cascade = CascadeType.ALL)
    private List<ModernizationSuggestion> modernizationSuggestions = new ArrayList<>();

    @OneToOne(mappedBy = "analysis", cascade = CascadeType.ALL)
    private ArchitectureData architectureData;

    @OneToOne(mappedBy = "analysis", cascade = CascadeType.ALL)
    private Report report;

    @PrePersist
    public void prePersist() {
        analysisDate = LocalDateTime.now();
    }
}