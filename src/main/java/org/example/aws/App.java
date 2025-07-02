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

  public static void main(String[] args) throws Exception {
    StaticCredentialsProvider creds = StaticCredentialsProvider.create(
            AwsBasicCredentials.create("AKIAXQIQACOVAA6CJM5Q", "zSwbVSN5C6BQqoef4zJ+mq6bIo27fSa8WtR0LAtY")
    );
    AWSClientProvider provider = new AWSClientProvider(creds, Region.US_EAST_1);

    DynamoDbService dynamo = new DynamoDbServiceImpl(provider);
    SecretsManagerService secrets = new SecretsManagerServiceImpl(provider);
    ParameterStoreService params  = new ParameterStoreServiceImpl(provider);
    S3StorageService s3      = new S3StorageServiceImpl(provider);

    String tabla = "prueba";
    Map<String,AttributeValue> item = Map.of(
            "id",     AttributeValue.builder().s("123").build(),
            "nombre", AttributeValue.builder().s("Juan").build()
    );
    dynamo.putItem(tabla, item);

    Map<String,AttributeValue> key = Map.of(
            "id", AttributeValue.builder().s("123").build()
    );
    Map<String,AttributeValue> result = dynamo.getItem(tabla, key);
    System.out.println("Recuperado: " + result);

    String secretJson = secrets.getSecretValue("my-db-secret");
    System.out.println("Secret: " + secretJson);

    String param = params.getParameter("/myApp/dev/test", false);
    System.out.println("Param: " + param);

    s3.downloadObject("bucket-avc", "test.txt", Path.of("descargado.txt"));
  }
}
