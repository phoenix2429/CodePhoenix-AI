package com.codephoenix.service.impl;

import com.codephoenix.entity.Project;
import com.codephoenix.entity.User;
import com.codephoenix.repository.ProjectRepository;
import com.codephoenix.repository.UserRepository;
import com.codephoenix.scanner.ZipExtractor;
import com.codephoenix.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectServiceImpl
        implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Value("${file.upload-dir:uploads/projects}")
    private String uploadDir;

    @Override
    public Project createProject(
            MultipartFile file, String projectName, String description) {

        if (file.isEmpty()) {
            throw new IllegalArgumentException("Uploaded file is empty");
        }

        try {
            // Ensure directory exists
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }

            // Save the ZIP file
            String uniqueId = UUID.randomUUID().toString();
            String zipFileName = uniqueId + "_" + file.getOriginalFilename();
            Path zipFilePath = Paths.get(uploadDir, zipFileName);
            Files.copy(file.getInputStream(), zipFilePath);

            // Extract the ZIP file
            String extractedFolderName = uniqueId + "_extracted";
            File extractedFolder = new File(uploadFolder, extractedFolderName);
            extractedFolder.mkdirs();

            ZipExtractor.extract(zipFilePath.toFile(), extractedFolder);

            // Create Project Entity
            Project project = new Project();
            project.setProjectName(projectName);
            project.setDescription(description);
            project.setZipPath(zipFilePath.toAbsolutePath().toString());
            project.setExtractedPath(extractedFolder.getAbsolutePath());
            project.setStatus("UPLOADED");
            project.setUploadedAt(LocalDateTime.now());

            // Associate current authenticated user
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userRepository.findByEmail(email).orElse(null);
            project.setUser(user);

            return projectRepository.save(project);

        } catch (IOException e) {
            log.error("Failed to upload/extract project ZIP", e);
            throw new RuntimeException("Failed to upload project: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}