package com.codephoenix.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "impact_analysis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImpactAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String component;

    private String impactLevel;

    @Column(length = 3000)
    private String affectedModules;

    @ManyToOne
    @JoinColumn(name = "analysis_id")
    private Analysis analysis;
}