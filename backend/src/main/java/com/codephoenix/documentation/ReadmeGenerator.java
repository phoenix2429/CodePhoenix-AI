package com.codephoenix.documentation;

import com.codephoenix.parser.JavaClassModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReadmeGenerator {

    public String generate(String projectName, List<JavaClassModel> classes) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ").append(projectName).append("\n\n");
        sb.append("This is an automatically generated documentation README for the project, created by **CodePhoenix AI**.\n\n");
        
        sb.append("## Overview\n");
        sb.append("- Total Scanned Classes: ").append(classes.size()).append("\n\n");

        sb.append("## Project Components Structure\n");
        
        long controllers = classes.stream().filter(JavaClassModel::isController).count();
        long services = classes.stream().filter(JavaClassModel::isService).count();
        long repositories = classes.stream().filter(JavaClassModel::isRepository).count();

        sb.append("- **Controllers (API Handlers)**: ").append(controllers).append("\n");
        sb.append("- **Services (Business Rules)**: ").append(services).append("\n");
        sb.append("- **Repositories (Database Mappers)**: ").append(repositories).append("\n\n");

        sb.append("## Class Index\n");
        for (JavaClassModel clazz : classes) {
            sb.append("- `").append(clazz.getName()).append("` (").append(clazz.getPackageName()).append(")\n");
        }

        return sb.toString();
    }
}
