package com.altimetrik.def.service;

import com.altimetrik.def.model.Credential;
import com.altimetrik.def.model.CredentialResponse;

public interface CredentialMgmtInt {

	CredentialResponse createCredentials(Credential credentials);
	CredentialResponse fetchAllCredentials();
	CredentialResponse fetchOneCredentials(String credentialName);
	CredentialResponse updateCredentials(Credential credentiial);
	CredentialResponse deleteCredentials(String credentialName);
	CredentialResponse dataValidation(Credential credential);
}
