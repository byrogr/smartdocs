package com.rms.smartdocs.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.smartdocs.models.dto.DocumentRequestDto;
import com.rms.smartdocs.services.DocumentService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/documents")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;

    @GetMapping("")
    public ResponseEntity<List<String>> getListDocuments() {
        var documents = documentService.getListDocuments();
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<String>> getDocumentById(@PathVariable String id) {
        var document = documentService.getDocumentById(id);
        return ResponseEntity.ok(document);
    }

    @PostMapping("")
    public ResponseEntity<String> postCreateDocument(@RequestBody DocumentRequestDto documentRequestDto) {
        var document = documentService.createDocument(documentRequestDto.name());
        return ResponseEntity.ok(document);
    }
}
