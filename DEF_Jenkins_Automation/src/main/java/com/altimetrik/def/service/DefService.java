package com.altimetrik.def.service;

import com.altimetrik.def.model.Def;
import com.altimetrik.def.model.JobParams;
import com.altimetrik.def.model.Response;

public interface DefService {

	public JobParams insertDetails(JobParams configurationDetails);
	public JobParams updateDetails(JobParams input);
	public JobParams getOneJobDetails(String jobName);
	public JobParams getAllJOb();
	public void deleteJobDetails(String jobName);
	public Response nullChkJenkinsJob(Def input);
}
