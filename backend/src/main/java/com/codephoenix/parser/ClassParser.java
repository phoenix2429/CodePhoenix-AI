package com.codephoenix.parser;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;

public class ClassParser {

    public static void parse(CompilationUnit cu, JavaClassModel model) {
        cu.getPackageDeclaration().ifPresent(pkg -> model.setPackageName(pkg.getNameAsString()));

        cu.findFirst(ClassOrInterfaceDeclaration.class).ifPresent(cid -> {
            model.setName(cid.getNameAsString());
            model.setInterface(cid.isInterface());

            // Process Annotations
            for (AnnotationExpr annotation : cid.getAnnotations()) {
                String annotationName = annotation.getNameAsString();
                model.getAnnotations().add(annotationName);
                
                if (annotationName.equals("RestController") || annotationName.equals("Controller")) {
                    model.setController(true);
                } else if (annotationName.equals("Service")) {
                    model.setService(true);
                } else if (annotationName.equals("Repository")) {
                    model.setRepository(true);
                }
            }

            // Process Fields
            for (FieldDeclaration field : cid.getFields()) {
                field.getVariables().forEach(var -> {
                    model.getFields().add(field.getElementType().asString() + " " + var.getNameAsString());
                });
            }
        });
    }
}
