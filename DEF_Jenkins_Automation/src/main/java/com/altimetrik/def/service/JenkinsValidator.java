package com.altimetrik.def.service;

import com.altimetrik.def.model.Response;

public interface JenkinsValidator extends CiValidator{

	Response ciValidateFolder(String folderName);
}
