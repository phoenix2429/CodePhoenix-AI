package com.codephoenix.architecture;

import com.codephoenix.parser.JavaClassModel;
import org.springframework.stereotype.Component;

@Component
public class LayerDetector {

    public String detectLayer(JavaClassModel classModel) {
        if (classModel.isController()) {
            return "Controller";
        } else if (classModel.isService()) {
            return "Service";
        } else if (classModel.isRepository()) {
            return "Repository";
        } else if (classModel.isInterface()) {
            return "Interface";
        }
        
        String name = classModel.getName();
        if (name.endsWith("Controller") || name.endsWith("Resource")) {
            return "Controller";
        } else if (name.endsWith("Service") || name.endsWith("ServiceImpl")) {
            return "Service";
        } else if (name.endsWith("Repository") || name.endsWith("DAO")) {
            return "Repository";
        } else if (name.endsWith("Entity") || name.endsWith("DTO")) {
            return "DataModel";
        }
        
        return "Component";
    }
}
