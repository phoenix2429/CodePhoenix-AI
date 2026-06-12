package com.codephoenix.modernization;

import com.codephoenix.parser.JavaClassModel;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class RefactoringAdvisor {

    public List<String> getRefactoringSuggestions(JavaClassModel classModel) {
        List<String> suggestions = new ArrayList<>();
        
        if (classModel.getMethods().size() > 15) {
            suggestions.add("Split " + classModel.getName() + " class into smaller, single-responsibility service classes (Violates Single Responsibility Principle).");
        }
        
        classModel.getMethods().forEach(method -> {
            if (method.getBody() != null && method.getBody().split("\n").length > 40) {
                suggestions.add("Extract helper methods from massive method '" + method.getName() + "' (Method length > 40 lines).");
            }
        });

        if (classModel.getFields().size() > 10) {
            suggestions.add("High variable count in class. Extract parameter blocks or settings into dedicated configuration/data records.");
        }

        return suggestions;
    }
}
