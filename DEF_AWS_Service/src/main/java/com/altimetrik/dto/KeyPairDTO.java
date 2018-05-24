package com.altimetrik.dto;

import org.springframework.stereotype.Component;

@Component
public class KeyPairDTO {

	private String keyPairName;
	private String fingerprint;
	
	public String getKeyPairName() {
		return keyPairName;
	}
	public void setKeyPairName(String keyPairName) {
		this.keyPairName = keyPairName;
	}
	public String getFingerprint() {
		return fingerprint;
	}
	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}
	
	
}
