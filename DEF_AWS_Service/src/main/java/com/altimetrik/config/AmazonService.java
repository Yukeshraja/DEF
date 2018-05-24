package com.altimetrik.config;

import org.springframework.stereotype.Component;

import com.altimetrik.dto.AWSLoginCredDTO;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.elasticbeanstalk.AWSElasticBeanstalk;
import com.amazonaws.services.elasticbeanstalk.AWSElasticBeanstalkClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

//@PropertySource("classpath:aws.yml")
// @Configuration
/**
 * @author nmuthusamy
 *
 */
@Component
public class AmazonService {

	/**
	 * @return AmazonEC2Client 
	 * 			Client object to which communicates with Amazon EC2 services
	 * @param AWSAccessKeyId
	 *            AWSAccessKeyId of the user
	 * @param AWSSecretAccessKeyId
	 *            AWSSecretAccessKeyId of the user
	 */
	public AmazonEC2 ec2() {
		AWSLoginCredDTO dto = AWSLoginCredDTO.getAWSLoginCred();
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(dto.getAws_access_key_id(),
				dto.getAws_secret_access_key());
		return AmazonEC2ClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCreds))
				.withRegion(Regions.AP_SOUTH_1).build();
	}

	/**
	 * @return AmazonS3Client 
	 * 				Client object which communicates with Amazon S3 service
	 * @param AWSAccessKeyId
	 *            AWSAccessKeyId of the user
	 * @param AWSSecretAccessKeyId
	 *            AWSSecretAccessKeyId of the use
	 */
	public AmazonS3 s3() {
		AWSLoginCredDTO dto = AWSLoginCredDTO.getAWSLoginCred();
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(dto.getAws_access_key_id(),
				dto.getAws_secret_access_key());
		return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds))
				.withRegion(Regions.AP_SOUTHEAST_1).build();

	}

	/**
	 * @return AWSElasticBeanstalkClient 
	 * 			Client object which communicates with Amazon Elastic Beanstalk service
	 * @param AWSAccessKeyId
	 *            AWSAccessKeyId of the user
	 * @param AWSSecretAccessKeyId
	 *            AWSSecretAccessKeyId of the use
	 */
	public AWSElasticBeanstalk beansTalk() {
		AWSLoginCredDTO dto = AWSLoginCredDTO.getAWSLoginCred();
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(dto.getAws_access_key_id(),
				dto.getAws_secret_access_key());
		
		/* AWSElasticBeanstalk API is not working for region AP_SOUTH_1(Mumbai),
		  so that here AP_SOUTHEAST_1(Singapore) is being used */
		AWSElasticBeanstalk client = AWSElasticBeanstalkClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion(Regions.AP_SOUTHEAST_1).build();

		return client;
	}

}
