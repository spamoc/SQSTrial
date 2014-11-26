package jp.spamoc.sqstrial;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSClient;

abstract public class AbstractSQSManager {
    
    protected static final String url;
    
    protected static final AmazonSQSClient client;
    
    static{
        File file = new File("conf/aws.properties");
        Properties configuration = new Properties();
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
            configuration.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("properties file load error");
        }
        
        String accessKey = configuration.getProperty("aws.access.key");
        String secretKey = configuration.getProperty("aws.secret.key");
        String sqsUrl = configuration.getProperty("aws.sqs.url");
        url = sqsUrl;
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        client = new AmazonSQSClient(awsCredentials);
    }
}
