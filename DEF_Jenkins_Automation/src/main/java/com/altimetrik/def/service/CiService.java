package com.altimetrik.def.service;

import com.altimetrik.def.model.CIModel;
import com.altimetrik.def.model.CiModelResponse;

public interface CiService {

	CiModelResponse createCi(CIModel ciModel);
	CiModelResponse updateCi(CIModel ciModel);
	CiModelResponse getAllCi();
	CiModelResponse getOneCi(String ciName);
	CiModelResponse deleteCi(String ciName);
}
