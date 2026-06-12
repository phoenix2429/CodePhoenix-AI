package com.codephoenix.modernization;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class JavaVersionAdvisor {

    public List<String> getJava21Recommendations(String currentVersion) {
        List<String> recommendations = new ArrayList<>();
        recommendations.add("Enable Virtual Threads for high-throughput concurrency (Servlet container execution).");
        recommendations.add("Replace boilerplates with Java Records for immutable data transfer objects (DTOs).");
        recommendations.add("Utilize Pattern Matching in switch expressions to clean nested instanceOf checks.");
        recommendations.add("Upgrade String block concatenations to Multiline Text Blocks (Introduced in Java 15).");
        return recommendations;
    }
}
