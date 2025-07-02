package org.example.aws.services.impl;

import org.example.aws.config.AWSClientProvider;
import org.example.aws.services.S3StorageService;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import java.nio.file.Path;

@Service
public class S3StorageServiceImpl implements S3StorageService {

    private final S3Client client;

    public S3StorageServiceImpl(AWSClientProvider provider) {
        this.client = provider.s3();
    }

    @Override
    public void downloadObject(String bucket, String key, Path destination) {
        client.getObject(GetObjectRequest.builder()
                        .bucket(bucket)
                        .key(key)
                        .build(),
                destination);
    }
}
