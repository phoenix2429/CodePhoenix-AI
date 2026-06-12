package com.codephoenix.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "architecture_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArchitectureData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20000)
    private String nodesJson;

    @Column(length = 20000)
    private String linksJson;

    @OneToOne
    @JoinColumn(name = "analysis_id")
    private Analysis analysis;
}
