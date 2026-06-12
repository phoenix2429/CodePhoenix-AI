package com.codephoenix.service;

import com.codephoenix.entity.Project;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProjectService {

    Project createProject(MultipartFile file, String projectName, String description);

    List<Project> getAllProjects();

}