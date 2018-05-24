package com.altimetrik.def.service;

import com.altimetrik.def.model.JobParams;


public interface DefLoader {
	
	public JobParams createJob(JobParams jobParams);
	public JobParams updateJob(JobParams jobParams);
	
}
