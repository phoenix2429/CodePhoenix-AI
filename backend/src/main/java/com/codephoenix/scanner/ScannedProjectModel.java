package com.codephoenix.scanner;

import com.codephoenix.parser.JavaClassModel;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class ScannedProjectModel {
    private List<JavaClassModel> classes = new ArrayList<>();
    private List<String> dependencies = new ArrayList<>();
}
