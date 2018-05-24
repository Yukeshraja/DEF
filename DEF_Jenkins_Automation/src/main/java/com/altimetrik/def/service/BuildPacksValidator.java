package com.altimetrik.def.service;

import com.altimetrik.def.model.Buildpacks;
import com.altimetrik.def.model.Response;

public interface BuildPacksValidator extends DefValidator {
	
	Response dataVaidation(Buildpacks name);
}
