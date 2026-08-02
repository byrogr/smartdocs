package com.rms.smartdocs.models.dto;

import java.time.Instant;

public record DocumentResponseDto(String id, String filename, String status, Instant uploadedAt) {}
