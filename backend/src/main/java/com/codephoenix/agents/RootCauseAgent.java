package com.codephoenix.agents;

import com.codephoenix.parser.JavaClassModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RootCauseAgent {

    public String analyze(JavaClassModel classModel, String issueType, String issueSummary) {
        log.info("RootCauseAgent analyzing issue for class: {}", classModel.getName());

        StringBuilder sb = new StringBuilder();
        sb.append("### ROOT CAUSE DIAGNOSIS\n");
        sb.append("- **Source File**: `").append(classModel.getName()).append(".java`\n");
        sb.append("- **Package**: `").append(classModel.getPackageName()).append("`\n");
        sb.append("- **Detected Issue**: ").append(issueType).append("\n");
        sb.append("- **Vulnerability Description**: ").append(issueSummary).append("\n\n");

        sb.append("### AFFECTED CALL FLOW PATHWAY\n");
        if (classModel.isController()) {
            sb.append("HTTP Endpoint Request\n");
            sb.append("  ↳ Controller: `").append(classModel.getName()).append("`\n");
            sb.append("    ↳ Service Delegate (Potential failure propagation)\n");
        } else if (classModel.isService()) {
            sb.append("Controller Entrypoint\n");
            sb.append("  ↳ Business Service Layer: `").append(classModel.getName()).append("` (Fault logic here)\n");
            sb.append("    ↳ Repository Database Layer\n");
        } else if (classModel.isRepository()) {
            sb.append("Service Business Logic Trigger\n");
            sb.append("  ↳ JPA Data Repository: `").append(classModel.getName()).append("` (Data/query level bottleneck)\n");
            sb.append("    ↳ Database Engine execution\n");
        } else {
            sb.append("Component Trigger\n");
            sb.append("  ↳ Helper Class: `").append(classModel.getName()).append("`\n");
        }

        sb.append("\n### REMEDIATION ACTION PLAN\n");
        sb.append("1. Isolate the affected class parameters.\n");
        sb.append("2. Implement validation constraints at input boundaries.\n");
        sb.append("3. Refactor logic to use non-synchronized/modern API classes.\n");

        return sb.toString();
    }
}
