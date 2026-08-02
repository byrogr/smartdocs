package com.rms.smartdocs.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

import com.rms.smartdocs.models.dto.DocumentResponseDto;
import com.rms.smartdocs.models.entities.Document;
import com.rms.smartdocs.repositories.DocumentRepository;
import com.rms.smartdocs.storage.BlobStorageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final BlobStorageService blobStorageService;

    @Override
    public List<DocumentResponseDto> getListDocuments() {
        log.info("Getting list of documents");
        return StreamSupport.stream(documentRepository.findAll().spliterator(), false)
                .map(DocumentServiceImpl::toResponseDto)
                .toList();
    }

    @Override
    public Optional<DocumentResponseDto> getDocumentById(String id) {
        log.info("Getting document by ID: {}", id);
        return documentRepository.findById(id).map(DocumentServiceImpl::toResponseDto);
    }

    @Override
    public DocumentResponseDto createDocument(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El archivo es requerido");
        }

        var id = UUID.randomUUID().toString();
        blobStorageService.uploadFile(id, file);

        var document = new Document(id, file.getOriginalFilename(), "uploaded", Instant.now());

        log.info("Saving document metadata for filename: {}", document.getFilename());
        var saved = documentRepository.save(document);
        return toResponseDto(saved);
    }

    private static DocumentResponseDto toResponseDto(Document document) {
        return new DocumentResponseDto(
                document.getId(),
                document.getFilename(),
                document.getStatus(),
                document.getUploadedAt());
    }
}
