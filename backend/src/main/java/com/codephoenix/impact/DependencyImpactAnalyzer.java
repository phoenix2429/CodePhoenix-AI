package com.codephoenix.impact;

import com.codephoenix.parser.JavaClassModel;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DependencyImpactAnalyzer {

    public Set<String> analyzeTransitiveImpact(String targetClass, List<JavaClassModel> allClassModels) {
        Set<String> affected = new HashSet<>();
        findDirectDependents(targetClass, allClassModels, affected);
        
        Set<String> transitive = new HashSet<>(affected);
        for (String dep : affected) {
            findDirectDependents(dep, allClassModels, transitive);
        }
        
        return transitive;
    }

    private void findDirectDependents(String target, List<JavaClassModel> classes, Set<String> accumulator) {
        for (JavaClassModel clazz : classes) {
            if (clazz.getName() == null || clazz.getName().equals(target)) {
                continue;
            }
            if (clazz.getDependencies().contains(target) || clazz.getContent().contains(target)) {
                if (!accumulator.contains(clazz.getName())) {
                    accumulator.add(clazz.getName());
                }
            }
        }
    }
}
