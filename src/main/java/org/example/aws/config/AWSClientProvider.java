package org.example.aws.config;

import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.ssm.SsmClient;

public class AWSClientProvider implements AutoCloseable {

    private final AwsCredentialsProvider creds;
    private final Region region;

    private DynamoDbClient dynamoDbClient;
    private S3Client s3Client;
    private SecretsManagerClient secretsManagerClient;
    private SsmClient ssmClient;

    public AWSClientProvider(AwsCredentialsProvider creds, Region region) {
        this.creds  = creds;
        this.region = region;
    }

    public DynamoDbClient dynamoDb() {
        if (dynamoDbClient == null) {
            dynamoDbClient = DynamoDbClient.builder()
                    .credentialsProvider(creds)
                    .region(region)
                    .build();
        }
        return dynamoDbClient;
    }

    public S3Client s3() {
        if (s3Client == null) {
            s3Client = S3Client.builder()
                    .credentialsProvider(creds)
                    .region(region)
                    .build();
        }
        return s3Client;
    }

    public SecretsManagerClient secretsManager() {
        if (secretsManagerClient == null) {
            secretsManagerClient = SecretsManagerClient.builder()
                    .credentialsProvider(creds)
                    .region(region)
                    .build();
        }
        return secretsManagerClient;
    }

    public SsmClient ssmClient() {
        if (ssmClient == null) {
            ssmClient = SsmClient.builder()
                    .credentialsProvider(creds)
                    .region(region)
                    .build();
        }
        return ssmClient;
    }

    @Override
    public void close() {
        if (dynamoDbClient != null)       dynamoDbClient.close();
        if (s3Client != null)             s3Client.close();
        if (secretsManagerClient != null) secretsManagerClient.close();
        if (ssmClient != null)            ssmClient.close();
    }
}
