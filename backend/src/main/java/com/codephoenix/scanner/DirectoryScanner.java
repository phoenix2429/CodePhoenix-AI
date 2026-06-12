package com.codephoenix.scanner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryScanner {

    public static List<File> scan(File directory) {
        List<File> javaFiles = new ArrayList<>();
        scanRecursive(directory, javaFiles);
        return javaFiles;
    }

    private static void scanRecursive(File file, List<File> result) {
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) {
                    scanRecursive(child, result);
                }
            }
        } else if (file.getName().endsWith(".java")) {
            result.add(file);
        }
    }
}
