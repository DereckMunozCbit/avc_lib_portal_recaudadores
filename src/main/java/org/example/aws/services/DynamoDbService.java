package org.example.aws.services;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import java.util.Map;

public interface DynamoDbService {
    void putItem(String tableName, Map<String,AttributeValue> item);
    Map<String,AttributeValue> getItem(String tableName, Map<String,AttributeValue> key);
}
