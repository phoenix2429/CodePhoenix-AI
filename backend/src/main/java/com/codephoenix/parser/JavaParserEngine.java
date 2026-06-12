package com.codephoenix.parser;

import com.codephoenix.util.FileUtils;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class JavaParserEngine {

    public static JavaClassModel parse(File file) {
        JavaClassModel model = new JavaClassModel();
        model.setFilePath(file.getAbsolutePath());
        
        try {
            String content = FileUtils.readFileToString(file);
            model.setContent(content);

            CompilationUnit cu = StaticJavaParser.parse(file);
            
            ClassParser.parse(cu, model);
            MethodParser.parse(cu, model);
            DependencyParser.parse(cu, model);

        } catch (Exception e) {
            log.warn("Failed to parse Java file: {} using JavaParser. Falling back to regex parser.", file.getName(), e);
            parseFallback(file, model);
        }
        
        if (model.getName() == null) {
            String fileName = file.getName();
            int dotIdx = fileName.lastIndexOf('.');
            String className = dotIdx > 0 ? fileName.substring(0, dotIdx) : fileName;
            model.setName(className);
        }
        
        return model;
    }

    private static void parseFallback(File file, JavaClassModel model) {
        try {
            String content = FileUtils.readFileToString(file);
            model.setContent(content);
            
            String fileName = file.getName();
            String className = fileName.substring(0, fileName.lastIndexOf('.'));
            model.setName(className);

            java.util.regex.Pattern pkgPattern = java.util.regex.Pattern.compile("package\\s+([a-zA-Z0-9._]+);");
            java.util.regex.Matcher pkgMatcher = pkgPattern.matcher(content);
            if (pkgMatcher.find()) {
                model.setPackageName(pkgMatcher.group(1));
            }

            if (content.contains("public interface " + className)) {
                model.setInterface(true);
            }

            if (content.contains("@RestController") || content.contains("@Controller")) {
                model.setController(true);
                model.getAnnotations().add("RestController");
            }
            if (content.contains("@Service")) {
                model.setService(true);
                model.getAnnotations().add("Service");
            }
            if (content.contains("@Repository")) {
                model.setRepository(true);
                model.getAnnotations().add("Repository");
            }

        } catch (Exception ex) {
            log.error("Fallback parser also failed for {}", file.getName(), ex);
        }
    }
}
