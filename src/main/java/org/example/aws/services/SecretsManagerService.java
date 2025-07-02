package org.example.aws.services;

public interface SecretsManagerService {
    String getSecretValue(String secretId);
}
