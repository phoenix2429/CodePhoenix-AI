package com.codephoenix.util;

public class PromptBuilder {

    public static String buildSystemPrompt(String roleInstruction) {
        return "You are an autonomous engineering agent specialized in: " + roleInstruction + 
               ". Analyze the code provided and produce precise, actionable feedback in JSON.";
    }

    public static String buildAnalysisPrompt(String task, String className, String code) {
        return "Task: " + task + "\nClass Name: " + className + "\nCode:\n" + code;
    }
}
