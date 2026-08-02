package com.rms.smartdocs.repositories;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.rms.smartdocs.models.entities.Document;

public interface DocumentRepository extends CosmosRepository<Document, String> {
}
