package org.example.aws.services;

import java.nio.file.Path;

public interface S3StorageService {
    void downloadObject(String bucket, String key, Path destination);
}