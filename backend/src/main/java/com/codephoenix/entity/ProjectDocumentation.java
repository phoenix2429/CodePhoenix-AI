package com.codephoenix.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "project_documentation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDocumentation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50000)
    private String readme;

    @Column(length = 50000)
    private String apiDoc;

    @Column(length = 50000)
    private String methodDoc;

    private LocalDateTime generatedAt;

    @OneToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @PrePersist
    public void prePersist() {
        generatedAt = LocalDateTime.now();
    }
}
