package com.rms.smartdocs.storage;

import java.io.IOException;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.options.BlobParallelUploadOptions;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
public class BlobStorageService {

    private final BlobServiceClient blobServiceClient;

    @Value("${smartdocs.storage.container-name}")
    private String containerName;

    public BlobStorageService(BlobServiceClient blobServiceClient) {
        this.blobServiceClient = blobServiceClient;
    }

    public String uploadFile(String id, MultipartFile file) {
        var containerClient = blobServiceClient.getBlobContainerClient(containerName);
        containerClient.createIfNotExists();

        var blobName = buildBlobName(id, file.getOriginalFilename());
        var blobClient = containerClient.getBlobClient(blobName);
        var headers = new BlobHttpHeaders().setContentType(file.getContentType());

        log.info("Uploading blob {} to container {}", blobName, containerName);
        try (var inputStream = file.getInputStream()) {
            blobClient.uploadWithResponse(
                    new BlobParallelUploadOptions(inputStream).setHeaders(headers), null, null);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al subir el archivo al storage", e);
        }

        return blobName;
    }

    private static String buildBlobName(String id, String originalFilename) {
        if (originalFilename == null) {
            return id;
        }
        int dotIndex = originalFilename.lastIndexOf('.');
        return dotIndex > 0 ? id + originalFilename.substring(dotIndex) : id;
    }
}
