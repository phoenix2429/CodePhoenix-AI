package com.codephoenix.modernization;

import com.codephoenix.parser.JavaClassModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LegacyApiDetector {

    public List<String> detect(JavaClassModel classModel) {
        List<String> findings = new ArrayList<>();
        String code = classModel.getContent();

        if (code.contains("Vector")) {
            findings.add("Vector usage detected in " + classModel.getName());
        }
        if (code.contains("Hashtable")) {
            findings.add("Hashtable usage detected in " + classModel.getName());
        }
        if (code.contains("SimpleDateFormat")) {
            findings.add("SimpleDateFormat usage detected in " + classModel.getName());
        }
        if (code.contains("java.util.Date") || code.contains("java.util.Calendar")) {
            findings.add("Legacy java.util Date/Calendar API usage detected in " + classModel.getName());
        }
        if (code.contains("StringBuffer")) {
            findings.add("StringBuffer usage detected in " + classModel.getName());
        }

        return findings;
    }
}
