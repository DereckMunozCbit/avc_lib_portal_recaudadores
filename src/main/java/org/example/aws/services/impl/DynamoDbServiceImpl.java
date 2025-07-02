package org.example.aws.services.impl;

import org.example.aws.config.AWSClientProvider;
import org.example.aws.services.DynamoDbService;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import java.util.Collections;
import java.util.Map;

@Service
public class DynamoDbServiceImpl implements DynamoDbService {

    private final DynamoDbClient client;

    public DynamoDbServiceImpl(AWSClientProvider provider) {
        this.client = provider.dynamoDb();
    }

    @Override
    public void putItem(String tableName, Map<String, AttributeValue> item) {
        client.putItem(PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build());
    }

    @Override
    public Map<String, AttributeValue> getItem(String tableName, Map<String, AttributeValue> key) {
        GetItemResponse resp = client.getItem(GetItemRequest.builder()
                .tableName(tableName)
                .key(key)
                .build());
        return resp.hasItem() ? resp.item() : Collections.emptyMap();
    }
}
