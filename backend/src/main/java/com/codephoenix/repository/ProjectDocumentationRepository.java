package com.codephoenix.repository;

import com.codephoenix.entity.ProjectDocumentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectDocumentationRepository extends JpaRepository<ProjectDocumentation, Long> {
    Optional<ProjectDocumentation> findByProjectId(Long projectId);
}
