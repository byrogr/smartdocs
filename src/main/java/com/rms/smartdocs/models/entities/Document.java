package com.rms.smartdocs.models.entities;

import java.time.Instant;

import com.azure.spring.data.cosmos.core.mapping.Container;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Container(containerName = "documents")
public class Document {
    @Id
    private String id;
    private String filename;
    private String status;
    private Instant uploadedAt;
}
