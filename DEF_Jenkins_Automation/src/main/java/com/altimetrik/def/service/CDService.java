package com.altimetrik.def.service;

import com.altimetrik.def.model.CDModel;
import com.altimetrik.def.model.CDResponse;


public interface CDService {

	CDResponse createCd(CDModel cd);
	CDResponse updateCd(CDModel cd);
	CDResponse getAllCd();
	CDResponse getOneCd(String CDId);
	CDResponse deleteCd(String CDId);
}
