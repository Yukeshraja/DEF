package com.altimetrik.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.config.AmazonService;
import com.altimetrik.dto.KeyPairDTO;
import com.altimetrik.services.KeyPairService;
import com.amazonaws.services.ec2.model.CreateKeyPairRequest;
import com.amazonaws.services.ec2.model.CreateKeyPairResult;
import com.amazonaws.services.ec2.model.DescribeKeyPairsResult;
import com.amazonaws.services.ec2.model.KeyPairInfo;

/**
 * @author nmuthusamy
 *
 */

@Service
public class KeyPairServiceImpl implements KeyPairService {

	final static Logger logger = Logger.getLogger(KeyPairServiceImpl.class);

	@Autowired
	AmazonService amazonService;

	@Override
	public List<KeyPairDTO> getAllKeyPairs() {

		logger.info("Getting all key pairs starting...");

		List<KeyPairDTO> keyPairList = new ArrayList<KeyPairDTO>();

		DescribeKeyPairsResult response = amazonService.ec2().describeKeyPairs();

		for (KeyPairInfo keyPair : response.getKeyPairs()) {
			KeyPairDTO dto = new KeyPairDTO();
			dto.setKeyPairName(keyPair.getKeyName());
			dto.setFingerprint(keyPair.getKeyFingerprint());
			keyPairList.add(dto);
		}
		logger.info("Grtting all key pairs ending...");
		return keyPairList;
	}

	@Override
	public CreateKeyPairResult createKeyPair(KeyPairDTO keyPairDTO) {

		logger.info("Creating key pair starting...");
		CreateKeyPairRequest request = new CreateKeyPairRequest().withKeyName(keyPairDTO.getKeyPairName());

		CreateKeyPairResult response = amazonService.ec2().createKeyPair(request);
		logger.info("Creating key pair ending...");

		return response;
	}

}
