package com.rms.smartdocs.controllers;

import java.util.List;
import java.util.Optional;

import com.rms.smartdocs.models.dto.DocumentResponseDto;
import com.rms.smartdocs.services.DocumentService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/documents")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;

    @GetMapping("")
    public ResponseEntity<List<DocumentResponseDto>> getListDocuments() {
        var documents = documentService.getListDocuments();
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentResponseDto> getDocumentById(@PathVariable String id) {
        Optional<DocumentResponseDto> document = documentService.getDocumentById(id);
        return document.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DocumentResponseDto> postCreateDocument(@RequestParam("file") MultipartFile file) {
        var document = documentService.createDocument(file);
        return ResponseEntity.ok(document);
    }
}
