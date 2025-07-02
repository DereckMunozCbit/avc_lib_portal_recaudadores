package org.example.aws.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "aws")
public class AwsProperties {
    private String accessKey;
    private String secretKey;
    private String region = "us-east-1";
}
