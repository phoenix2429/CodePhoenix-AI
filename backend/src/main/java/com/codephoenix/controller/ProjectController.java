package com.codephoenix.controller;

import com.codephoenix.entity.Project;
import com.codephoenix.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping(consumes = "multipart/form-data")
    public Project uploadProject(
            @RequestParam("file") MultipartFile file,
            @RequestParam("projectName") String projectName,
            @RequestParam(value = "description", required = false) String description) {

        return projectService.createProject(file, projectName, description);
    }

    @GetMapping
    public List<Project> getAllProjects() {

        return projectService.getAllProjects();
    }
}