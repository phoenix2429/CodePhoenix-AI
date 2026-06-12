package com.codephoenix.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectName;

    @Column(length = 3000)
    private String description;

    private String zipPath;

    private String extractedPath;

    private String status;

    private LocalDateTime uploadedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL)
    private ProjectDocumentation documentation;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Analysis> analyses = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        uploadedAt = LocalDateTime.now();
    }
}