package com.codephoenix.impact;

import org.springframework.stereotype.Component;

@Component
public class RiskPredictor {

    public String predictRisk(int affectedCount) {
        if (affectedCount >= 5) {
            return "HIGH";
        } else if (affectedCount >= 2) {
            return "MEDIUM";
        } else {
            return "LOW";
        }
    }
}
