package com.workevr.api.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author rohit
 * @Date 23/09/21
 **/

@Configuration
public class AwsConfig {

    @Value("${alpha.s3.accessKey}")
    private String accessKey;

    @Value("${alpha.s3.secretKey}")
    private String secretKey;

    @Value("${alpha.s3.region}")
    private String region;

    public AmazonS3 getS3Client(){
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3Client.builder()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

}
