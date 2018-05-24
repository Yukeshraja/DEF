package com.altimetrik.def.service;

import com.altimetrik.def.model.CD;
import com.altimetrik.def.model.Response;

public interface CdValidator extends DefValidator{

	Response dataValidation(CD cd);
	
}
