package com.codephoenix.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pdfPath;

    private LocalDateTime generatedAt;

    @OneToOne
    @JoinColumn(name = "analysis_id")
    private Analysis analysis;

    @PrePersist
    public void prePersist() {
        generatedAt = LocalDateTime.now();
    }
}