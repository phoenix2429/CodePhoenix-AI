package com.codephoenix.scanner;

import com.codephoenix.parser.JavaClassModel;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;

@Slf4j
public class ProjectScanner {

    public static ScannedProjectModel scan(File projectDir) {
        ScannedProjectModel projectModel = new ScannedProjectModel();

        if (projectDir == null || !projectDir.exists()) {
            log.warn("Project directory is null or does not exist");
            return projectModel;
        }

        log.info("Scanning project directory: {}", projectDir.getAbsolutePath());

        // Scan all Java files recursively
        List<File> javaFiles = DirectoryScanner.scan(projectDir);
        for (File javaFile : javaFiles) {
            try {
                JavaClassModel classModel = FileScanner.scanJavaFile(javaFile);
                projectModel.getClasses().add(classModel);
            } catch (Exception e) {
                log.error("Failed to parse java file: {}", javaFile.getName(), e);
            }
        }

        // Scan pom.xml for dependencies
        File pomFile = new File(projectDir, "pom.xml");
        if (pomFile.exists()) {
            List<String> deps = FileScanner.scanPomDependencies(pomFile);
            projectModel.setDependencies(deps);
        } else {
            // Check in child subdirectories if multi-module project
            File[] files = projectDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        File subPom = new File(file, "pom.xml");
                        if (subPom.exists()) {
                            projectModel.getDependencies().addAll(FileScanner.scanPomDependencies(subPom));
                        }
                    }
                }
            }
        }

        log.info("Scan completed. Found {} classes and {} dependencies", 
                projectModel.getClasses().size(), projectModel.getDependencies().size());

        return projectModel;
    }
}
