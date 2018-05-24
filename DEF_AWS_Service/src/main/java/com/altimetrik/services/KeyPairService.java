package com.altimetrik.services;

import java.util.List;

import com.altimetrik.dto.KeyPairDTO;
import com.amazonaws.services.ec2.model.CreateKeyPairResult;

/**
 * @author nmuthusamy
 *
 */
public interface KeyPairService {

	public List<KeyPairDTO> getAllKeyPairs();
	public CreateKeyPairResult createKeyPair(KeyPairDTO keyPairDTO);
	
}
