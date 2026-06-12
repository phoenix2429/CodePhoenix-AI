package com.codephoenix.modernization;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class SpringMigrationAdvisor {

    public List<String> getSpring3Recommendations() {
        List<String> recommendations = new ArrayList<>();
        recommendations.add("Migrate imports from javax.persistence.* / javax.servlet.* to jakarta.persistence.* / jakarta.servlet.*.");
        recommendations.add("Replace deprecated WebSecurityConfigurerAdapter with SecurityFilterChain bean declarations.");
        recommendations.add("Configure Spring Security HTTP security configurations using the new lambda DSL style.");
        recommendations.add("Upgrade Spring Data JPA method signatures to align with Hibernate 6 query executions.");
        return recommendations;
    }
}
