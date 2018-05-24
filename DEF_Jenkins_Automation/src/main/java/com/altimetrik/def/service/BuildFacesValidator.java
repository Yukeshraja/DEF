package com.altimetrik.def.service;

import com.altimetrik.def.model.BuildFaces;
import com.altimetrik.def.model.Response;

public interface BuildFacesValidator extends DefValidator{

	Response buildCycleValidation(BuildFaces input);
	Response dockerizationValidation(BuildFaces input);
	//Response dockerImageNameandDockerFilePathChkIfDockerSelected(BuildFaces input);
	
}
