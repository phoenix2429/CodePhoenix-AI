package com.codephoenix.parser;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class JavaMethodModel {
    private String name;
    private String returnType;
    private String body;
    private List<String> parameters = new ArrayList<>();
    private List<String> annotations = new ArrayList<>();
    private int startLine;
    private int endLine;
}
