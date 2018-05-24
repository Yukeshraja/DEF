package com.altimetrik.services;

import java.util.List;

import com.altimetrik.dto.S3BucketDTO;
import com.amazonaws.services.s3.model.Bucket;

/**
 * @author nmuthusamy
 *
 */
public interface S3Service {

	public List<Bucket> getAllS3Bucket();
	public S3BucketDTO createBucket(S3BucketDTO s3BucketDTO);
}
