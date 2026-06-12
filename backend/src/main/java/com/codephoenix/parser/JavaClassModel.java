package com.codephoenix.parser;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class JavaClassModel {
    private String name;
    private String packageName;
    private String filePath;
    private String content; // Source code content
    private boolean isInterface;
    
    // Stereotypes
    private boolean isController;
    private boolean isService;
    private boolean isRepository;
    
    private List<String> annotations = new ArrayList<>();
    private List<String> fields = new ArrayList<>();
    private List<JavaMethodModel> methods = new ArrayList<>();
    private List<String> dependencies = new ArrayList<>(); // Referenced class names
}
