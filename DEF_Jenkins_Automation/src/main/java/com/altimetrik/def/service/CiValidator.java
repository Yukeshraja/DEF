package com.altimetrik.def.service;

import com.altimetrik.def.model.Jenkins;
import com.altimetrik.def.model.Response;

public interface CiValidator extends DefValidator {

	Response ciValidateJob(Jenkins input);
	Response urlValidator(Jenkins input);
	Response ciValdatePermisson(Jenkins input);

}
