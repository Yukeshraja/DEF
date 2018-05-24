package com.altimetrik.def.service;

import com.altimetrik.def.model.SCM;
import com.altimetrik.def.model.ScmResponse;


public interface ScmService {

	ScmResponse createScm(SCM scm);
	ScmResponse updateScm(SCM scm);
	ScmResponse getAllScm();
	ScmResponse getOneScm(String ScmId);
	ScmResponse deleteScm(String ScmId);
}
