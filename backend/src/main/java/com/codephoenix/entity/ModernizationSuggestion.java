package com.codephoenix.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "modernization_suggestions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModernizationSuggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String legacyTechnology;

    private String recommendedTechnology;

    @Column(length = 5000)
    private String migrationNotes;

    @ManyToOne
    @JoinColumn(name = "analysis_id")
    private Analysis analysis;
}