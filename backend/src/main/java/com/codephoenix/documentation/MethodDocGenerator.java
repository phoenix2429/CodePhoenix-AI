package com.codephoenix.documentation;

import com.codephoenix.parser.JavaClassModel;
import com.codephoenix.parser.JavaMethodModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MethodDocGenerator {

    public String generate(List<JavaClassModel> classes) {
        StringBuilder sb = new StringBuilder();
        sb.append("# Detailed Class Method Specifications\n\n");
        sb.append("This document catalogs all declared methods, parameters, and return types.\n\n");

        for (JavaClassModel clazz : classes) {
            if (clazz.getMethods().isEmpty()) {
                continue;
            }

            sb.append("## Class: `").append(clazz.getName()).append("`\n");
            sb.append("- **Package**: `").append(clazz.getPackageName()).append("`\n");
            sb.append("- **Total Methods**: ").append(clazz.getMethods().size()).append("\n\n");

            for (JavaMethodModel method : clazz.getMethods()) {
                sb.append("### Method `").append(method.getName()).append("()`\n");
                sb.append("- **Access/Return**: `").append(method.getReturnType()).append("`\n");
                
                if (!method.getParameters().isEmpty()) {
                    sb.append("- **Parameters**:\n");
                    for (String param : method.getParameters()) {
                        sb.append("  - `").append(param).append("`\n");
                    }
                }
                
                if (!method.getAnnotations().isEmpty()) {
                    sb.append("- **Annotations**: `").append(String.join("`, `", method.getAnnotations())).append("`\n");
                }
                
                sb.append("\n");
            }
            sb.append("---\n\n");
        }

        return sb.toString();
    }
}
