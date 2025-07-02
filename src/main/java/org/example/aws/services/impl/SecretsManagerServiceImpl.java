package org.example.aws.services.impl;

import org.example.aws.config.AWSClientProvider;
import org.example.aws.services.SecretsManagerService;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;

@Service
public class SecretsManagerServiceImpl implements SecretsManagerService {

    private final SecretsManagerClient client;

    public SecretsManagerServiceImpl(AWSClientProvider provider) {
        this.client = provider.secretsManager();
    }

    @Override
    public String getSecretValue(String secretId) {
        return client.getSecretValue(
                GetSecretValueRequest.builder()
                        .secretId(secretId)
                        .build()
        ).secretString();
    }
}
