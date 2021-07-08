package com.test.test.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.test.test.repo.FooRepository;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackageClasses = FooRepository.class)
public class DynamoDBConfig {
  @Value("${aws.dynamodb.endpoint}")
  private String awsDynamoDBEndpoint;

  @Value("${aws.access-key-id}")
  private String awsAccessKeyId;

  @Value("${aws.secret-access-key}")
  private String awsSecretAccessKey;

  public AWSCredentialsProvider amazonAWSCredentialsProvider() {
    return new AWSStaticCredentialsProvider(amazonAWSCredentials());
  }

  @Bean
  public AWSCredentials amazonAWSCredentials() {
    return new BasicAWSCredentials(awsAccessKeyId, awsSecretAccessKey);
  }

  @Bean
  public AmazonDynamoDB amazonDynamoDB() {
    var builder = AmazonDynamoDBClientBuilder.standard();
    if (!awsDynamoDBEndpoint.isEmpty()) {
      builder.setEndpointConfiguration(
          new EndpointConfiguration(awsDynamoDBEndpoint, Regions.EU_CENTRAL_1.getName()));
    } else {
      builder.withRegion(Regions.EU_CENTRAL_1);
    }
    return builder.withCredentials(amazonAWSCredentialsProvider()).build();
  }
}
