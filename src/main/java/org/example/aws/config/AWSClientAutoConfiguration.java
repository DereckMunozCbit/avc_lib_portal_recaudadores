package org.example.aws.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;

@Configuration
@EnableConfigurationProperties(AwsProperties.class)
public class AWSClientAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AwsCredentialsProvider awsCredentialsProvider(AwsProperties props) {
        if (props.getAccessKey() != null && props.getSecretKey() != null) {
            return StaticCredentialsProvider
                    .create(
                            AwsBasicCredentials.create(
                                    props.getAccessKey(),
                                    props.getSecretKey()
                            )
                    );
        }
        return DefaultCredentialsProvider.create();
    }

    @Bean
    @ConditionalOnMissingBean
    public Region awsRegion(AwsProperties props) {
        return Region.of(props.getRegion());
    }

    @Bean
    @ConditionalOnMissingBean
    public AWSClientProvider awsClientProvider(
            AwsCredentialsProvider creds,
            Region region
    ) {
        return new AWSClientProvider(creds, region);
    }
}
