package com.codephoenix.parser;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

public class MethodParser {

    public static void parse(CompilationUnit cu, JavaClassModel model) {
        cu.findAll(MethodDeclaration.class).forEach(md -> {
            JavaMethodModel methodModel = new JavaMethodModel();
            methodModel.setName(md.getNameAsString());
            methodModel.setReturnType(md.getType().asString());
            
            // Parameters
            md.getParameters().forEach(param -> {
                methodModel.getParameters().add(param.getType().asString() + " " + param.getNameAsString());
            });

            // Annotations
            md.getAnnotations().forEach(anno -> {
                methodModel.getAnnotations().add(anno.getNameAsString());
            });

            // Start/End line
            md.getBegin().ifPresent(pos -> methodModel.setStartLine(pos.line));
            md.getEnd().ifPresent(pos -> methodModel.setEndLine(pos.line));

            // Body
            md.getBody().ifPresent(body -> methodModel.setBody(body.toString()));

            model.getMethods().add(methodModel);
        });
    }
}
