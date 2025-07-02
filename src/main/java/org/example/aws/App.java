package org.example.aws;


import org.example.aws.config.AWSClientProvider;
import org.example.aws.services.DynamoDbService;
import org.example.aws.services.ParameterStoreService;
import org.example.aws.services.S3StorageService;
import org.example.aws.services.SecretsManagerService;
import org.example.aws.services.impl.DynamoDbServiceImpl;
import org.example.aws.services.impl.ParameterStoreServiceImpl;
import org.example.aws.services.impl.S3StorageServiceImpl;
import org.example.aws.services.impl.SecretsManagerServiceImpl;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.nio.file.Path;
import java.util.Map;

public class App {

  public static void main(String[] args)  { }
}
