package com.altimetrik.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.config.AmazonService;
import com.altimetrik.dto.S3BucketDTO;
import com.altimetrik.services.S3Service;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CreateBucketRequest;

/**
 * @author nmuthusamy
 *
 */

@Service
public class S3ServiceImpl implements S3Service {
	
	final static Logger logger = Logger.getLogger(S3ServiceImpl.class);

	@Autowired
	AmazonService amazonService;

	@Override
	public List<Bucket> getAllS3Bucket() {

		List<Bucket> buckets = amazonService.s3().listBuckets();
		return buckets;
	}

	Bucket bucket = null;

	@Override
	public S3BucketDTO createBucket(S3BucketDTO s3BucketDTO) {

		S3BucketDTO bucketDTO = new S3BucketDTO();

		if (amazonService.s3().doesBucketExist(s3BucketDTO.getBucketName())) {
			logger.error("Bucket name already exist..");
			bucketDTO.setMessage("Bucket you are trying to create already exists... Please choose different name");
		} else {
			try {
				CreateBucketRequest request = new CreateBucketRequest(s3BucketDTO.getBucketName(),
						s3BucketDTO.getRegion());
				bucket = amazonService.s3().createBucket(request);
				String bucketLocation = amazonService.s3().getBucketLocation(bucket.getName());

				if (bucket != null) {
					bucketDTO.setBucketName(bucket.getName());
					bucketDTO.setMessage("Bucket created...");
					bucketDTO.setRegion(bucketLocation);
				}
			} catch (AmazonS3Exception e) {
				logger.error(e.getErrorMessage());
			}
		}

		return bucketDTO;
	}

}
