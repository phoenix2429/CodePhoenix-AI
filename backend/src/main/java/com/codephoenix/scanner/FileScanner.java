package com.codephoenix.scanner;

import com.codephoenix.parser.JavaClassModel;
import com.codephoenix.parser.JavaParserEngine;
import com.codephoenix.util.FileUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class FileScanner {

    public static JavaClassModel scanJavaFile(File file) {
        return JavaParserEngine.parse(file);
    }

    public static List<String> scanPomDependencies(File pomFile) {
        List<String> dependencies = new ArrayList<>();
        if (!pomFile.exists()) {
            return dependencies;
        }

        try {
            String content = FileUtils.readFileToString(pomFile);
            Pattern dependencyPattern = Pattern.compile(
                    "<dependency>\\s*(?:<!--.*?-->\\s*)*<groupId>([^<]+)</groupId>\\s*(?:<!--.*?-->\\s*)*<artifactId>([^<]+)</artifactId>",
                    Pattern.DOTALL
            );
            Matcher matcher = dependencyPattern.matcher(content);
            while (matcher.find()) {
                dependencies.add(matcher.group(2));
            }
        } catch (Exception e) {
            log.error("Failed to parse pom.xml: {}", pomFile.getName(), e);
        }

        return dependencies;
    }
}
