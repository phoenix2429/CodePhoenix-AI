package com.codephoenix.service.impl;

import com.codephoenix.documentation.ApiDocGenerator;
import com.codephoenix.documentation.MethodDocGenerator;
import com.codephoenix.documentation.ReadmeGenerator;
import com.codephoenix.entity.Project;
import com.codephoenix.entity.ProjectDocumentation;
import com.codephoenix.repository.ProjectDocumentationRepository;
import com.codephoenix.repository.ProjectRepository;
import com.codephoenix.scanner.ProjectScanner;
import com.codephoenix.scanner.ScannedProjectModel;
import com.codephoenix.service.DocumentationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentationServiceImpl
        implements DocumentationService {

    private final ProjectRepository projectRepository;
    private final ProjectDocumentationRepository docRepository;
    private final ReadmeGenerator readmeGenerator;
    private final ApiDocGenerator apiDocGenerator;
    private final MethodDocGenerator methodDocGenerator;

    @Override
    @Transactional
    public String generateDocumentation(Long projectId) {
        log.info("Generating documentation for project ID: {}", projectId);

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        try {
            // Scan codebase
            File projectDir = new File(project.getExtractedPath());
            ScannedProjectModel scannedProject = ProjectScanner.scan(projectDir);

            // Generate contents
            String readme = readmeGenerator.generate(project.getProjectName(), scannedProject.getClasses());
            String apiDoc = apiDocGenerator.generate(scannedProject.getClasses());
            String methodDoc = methodDocGenerator.generate(scannedProject.getClasses());

            // Save or Update Documentation
            ProjectDocumentation doc = docRepository.findByProjectId(projectId)
                    .orElse(new ProjectDocumentation());
            
            doc.setProject(project);
            doc.setReadme(readme);
            doc.setApiDoc(apiDoc);
            doc.setMethodDoc(methodDoc);
            doc.setGeneratedAt(LocalDateTime.now());

            docRepository.save(doc);

            log.info("Project documentation generated and saved successfully");
            return readme;

        } catch (Exception e) {
            log.error("Failed to generate documentation for project ID: {}", projectId, e);
            throw new RuntimeException("Documentation generation failed: " + e.getMessage(), e);
        }
    }
}