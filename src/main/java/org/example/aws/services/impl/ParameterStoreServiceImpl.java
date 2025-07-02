package org.example.aws.services.impl;

import org.example.aws.config.AWSClientProvider;
import org.example.aws.services.ParameterStoreService;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;

@Service
public class ParameterStoreServiceImpl implements ParameterStoreService {

    private final SsmClient client;

    public ParameterStoreServiceImpl(AWSClientProvider provider) {
        this.client = provider.ssmClient();
    }

    @Override
    public String getParameter(String name, boolean withDecryption) {
        return client.getParameter(GetParameterRequest.builder()
                        .name(name)
                        .withDecryption(withDecryption)
                        .build())
                .parameter()
                .value();
    }
}
