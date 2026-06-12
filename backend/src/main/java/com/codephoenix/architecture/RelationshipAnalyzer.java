package com.codephoenix.architecture;

import com.codephoenix.parser.JavaClassModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RelationshipAnalyzer {

    public List<String> findRelationships(JavaClassModel source, List<JavaClassModel> allClassModels) {
        List<String> targetClasses = new ArrayList<>();
        
        for (JavaClassModel target : allClassModels) {
            if (source.getName() == null || target.getName() == null || source.getName().equals(target.getName())) {
                continue;
            }
            
            if (source.getDependencies().contains(target.getName())) {
                targetClasses.add(target.getName());
            } else if (source.getContent().contains(target.getName())) {
                targetClasses.add(target.getName());
            }
        }
        
        return targetClasses;
    }
}
