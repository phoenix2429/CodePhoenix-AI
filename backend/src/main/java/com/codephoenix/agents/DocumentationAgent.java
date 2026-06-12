package com.codephoenix.agents;

import com.codephoenix.ai.AIClient;
import com.codephoenix.ai.PromptManager;
import com.codephoenix.parser.JavaClassModel;
import com.codephoenix.parser.JavaMethodModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DocumentationAgent {

    public String analyze(JavaClassModel classModel, AIClient aiClient, PromptManager promptManager) {
        log.info("DocumentationAgent generating doc for class: {}", classModel.getName());

        String prompt = promptManager.buildDocPrompt(classModel.getName(), classModel.getContent());
        String response = aiClient.generate(prompt);

        if (response != null && !response.isEmpty()) {
            return response;
        }

        // Fallback: structured markdown
        StringBuilder sb = new StringBuilder();
        sb.append("# ").append(classModel.getName()).append("\n\n");
        sb.append("## Description\n");
        if (classModel.isInterface()) {
            sb.append("This is a Java interface: `").append(classModel.getName()).append("`.\n\n");
        } else {
            sb.append("This is a Java class: `").append(classModel.getName()).append("`.\n\n");
        }

        sb.append("## Metadata\n");
        sb.append("- **Package**: `").append(classModel.getPackageName()).append("`\n");
        sb.append("- **Component Type**: ");
        if (classModel.isController()) sb.append("Controller (REST Controller)\n");
        else if (classModel.isService()) sb.append("Service (Business Logic)\n");
        else if (classModel.isRepository()) sb.append("Repository (Data Access)\n");
        else sb.append("Generic Component / Entity\n");

        if (!classModel.getAnnotations().isEmpty()) {
            sb.append("- **Annotations**: ");
            sb.append(String.join(", ", classModel.getAnnotations())).append("\n");
        }
        sb.append("\n");

        sb.append("## Fields\n");
        if (classModel.getFields().isEmpty()) {
            sb.append("No member fields declared.\n\n");
        } else {
            for (String field : classModel.getFields()) {
                sb.append("- `").append(field).append("`\n");
            }
            sb.append("\n");
        }

        sb.append("## Declared Methods\n");
        if (classModel.getMethods().isEmpty()) {
            sb.append("No methods declared.\n\n");
        } else {
            for (JavaMethodModel method : classModel.getMethods()) {
                sb.append("### `").append(method.getName()).append("`\n");
                sb.append("- **Return Type**: `").append(method.getReturnType()).append("`\n");
                if (!method.getParameters().isEmpty()) {
                    sb.append("- **Parameters**:\n");
                    for (String param : method.getParameters()) {
                        sb.append("  - `").append(param).append("`\n");
                    }
                }
                sb.append("\n");
            }
        }

        return sb.toString();
    }
}
