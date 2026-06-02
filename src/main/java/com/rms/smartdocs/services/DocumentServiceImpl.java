package com.rms.smartdocs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DocumentServiceImpl implements DocumentService {

    @Override
    public List<String> getListDocuments()  {
        log.info("Getting list of documents");
        return List.of("Document 1", "Document 2", "Document 3");
    }

    @Override
    public Optional<String> getDocumentById(String id) {
        log.info("Getting document by ID: {}", id);
        var document = "Document " + id;
        return Optional.of(document);
    }

    @Override
    public String createDocument(String document) {
        return document;
    }
}
