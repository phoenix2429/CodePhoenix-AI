package com.codephoenix.controller;

import com.codephoenix.service.DocumentationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documentation")
@RequiredArgsConstructor
public class DocumentationController {

    private final DocumentationService documentationService;

    @GetMapping("/{projectId}")
    public String generateDocumentation(
            @PathVariable Long projectId) {

        return documentationService
                .generateDocumentation(projectId);
    }
}