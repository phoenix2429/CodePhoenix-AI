package com.codephoenix.parser;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DependencyParser {

    public static void parse(CompilationUnit cu, JavaClassModel model) {
        Set<String> deps = new HashSet<>();

        // Extract package imports
        cu.getImports().forEach(imp -> {
            String importName = imp.getNameAsString();
            int lastDot = importName.lastIndexOf('.');
            if (lastDot != -1) {
                deps.add(importName.substring(lastDot + 1));
            } else {
                deps.add(importName);
            }
        });

        // Extract referenced class types from fields, local vars, or parameters
        cu.findAll(ClassOrInterfaceType.class).forEach(cit -> {
            deps.add(cit.getNameAsString());
        });

        // Extract class instantiations (new ClassName())
        cu.findAll(ObjectCreationExpr.class).forEach(oce -> {
            deps.add(oce.getType().getNameAsString());
        });

        // Remove primitive/basic types and current class name
        deps.remove("String");
        deps.remove("Integer");
        deps.remove("Long");
        deps.remove("Boolean");
        deps.remove("Double");
        deps.remove("Float");
        deps.remove("Object");
        deps.remove("List");
        deps.remove("Set");
        deps.remove("Map");
        deps.remove("ArrayList");
        deps.remove("HashMap");
        deps.remove("HashSet");
        deps.remove(model.getName());

        model.setDependencies(new ArrayList<>(deps));
    }
}
