package com.codephoenix.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "technical_debt")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechnicalDebt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer debtScore;

    @Column(length = 3000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "analysis_id")
    private Analysis analysis;
}