package com.onquantum.rockstar.common;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

/**
 * Created by Admin on 12/13/15.
 */
public class AWSHelper {
    private static String ACCESS_KEY_ID = "AKIAI7A4MG7MO5R2PNAA";
    private static String SECRET_ACCESS_KEY = "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";

    public static String bucketName = "onquantum";

    private AWSHelper(){}

    public static AmazonS3 GetAmazonS3Client() {
        AmazonS3 amazonS3 = new AmazonS3Client(new BasicAWSCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        amazonS3.setEndpoint("http://s3.amazonaws.com");
        return  amazonS3; //new AmazonS3Client(new BasicAWSCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
    }

}
