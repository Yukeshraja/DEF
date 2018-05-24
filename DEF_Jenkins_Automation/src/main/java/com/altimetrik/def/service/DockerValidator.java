package com.altimetrik.def.service;

import com.altimetrik.def.model.CD;
import com.altimetrik.def.model.Response;

public interface DockerValidator extends CdValidator{

	Response dockerDataValidation(CD cd);
}
