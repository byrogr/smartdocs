package com.rms.smartdocs.services;

import java.util.List;
import java.util.Optional;

import com.rms.smartdocs.models.dto.DocumentResponseDto;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {
    List<DocumentResponseDto> getListDocuments();
    Optional<DocumentResponseDto> getDocumentById(String id);
    DocumentResponseDto createDocument(MultipartFile file);
}
