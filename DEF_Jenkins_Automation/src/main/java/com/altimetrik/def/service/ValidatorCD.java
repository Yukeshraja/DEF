package com.altimetrik.def.service;

import com.altimetrik.def.model.CDModel;
import com.altimetrik.def.model.CDResponse;

public interface ValidatorCD {

	CDResponse validateCD(CDModel cdModel);
}
