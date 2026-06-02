package com.rms.smartdocs.services;

import java.util.List;
import java.util.Optional;

public interface DocumentService {
    List<String> getListDocuments();
    Optional<String> getDocumentById(String id);
    String createDocument(String document);
}
