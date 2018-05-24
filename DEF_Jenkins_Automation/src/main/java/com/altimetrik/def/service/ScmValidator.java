package com.altimetrik.def.service;

import com.altimetrik.def.model.Response;
import com.altimetrik.def.model.SCM;

public interface ScmValidator extends DefValidator {

	Response chkDetailsValidation(SCM scm);
	Response urlDetailsValidation(SCM scm);
}
