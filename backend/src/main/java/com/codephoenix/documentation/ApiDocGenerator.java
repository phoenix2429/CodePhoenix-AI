package com.codephoenix.documentation;

import com.codephoenix.parser.JavaClassModel;
import com.codephoenix.parser.JavaMethodModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApiDocGenerator {

    public String generate(List<JavaClassModel> classes) {
        StringBuilder sb = new StringBuilder();
        sb.append("# REST API Endpoints Reference\n\n");
        sb.append("This document outlines all detected Controller mappings and HTTP operations.\n\n");

        boolean hasControllers = false;
        for (JavaClassModel clazz : classes) {
            if (!clazz.isController()) {
                continue;
            }
            hasControllers = true;

            sb.append("## ").append(clazz.getName()).append("\n");
            sb.append("- **Class Path**: `").append(clazz.getPackageName()).append(".").append(clazz.getName()).append("`\n\n");

            sb.append("| Method | Mapping | Return Type | Parameters |\n");
            sb.append("| --- | --- | --- | --- |\n");

            for (JavaMethodModel method : clazz.getMethods()) {
                String mapping = "N/A";
                for (String anno : method.getAnnotations()) {
                    if (anno.contains("Mapping")) {
                        mapping = anno;
                        break;
                    }
                }
                
                String params = String.join(", ", method.getParameters());
                if (params.isEmpty()) params = "None";

                sb.append("| `").append(method.getName()).append("` | `")
                        .append(mapping).append("` | `")
                        .append(method.getReturnType()).append("` | `")
                        .append(params).append("` |\n");
            }
            sb.append("\n");
        }

        if (!hasControllers) {
            sb.append("*No Controller classes or HTTP endpoints detected in the codebase.*\n");
        }

        return sb.toString();
    }
}
