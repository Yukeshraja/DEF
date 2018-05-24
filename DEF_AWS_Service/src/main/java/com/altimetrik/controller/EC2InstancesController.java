package com.altimetrik.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.altimetrik.dto.CreateInstanceDTO;
import com.altimetrik.dto.InstancesDTO;
import com.altimetrik.services.InstanceServices;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.StartInstancesResult;
import com.amazonaws.services.ec2.model.StopInstancesResult;
import com.amazonaws.services.ec2.model.TerminateInstancesResult;
import com.google.gson.JsonParser;

/**
 * @author nmuthusamy
 *
 */
@Controller
public class EC2InstancesController {
	final AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard().withRegion(Regions.AP_SOUTH_1).build();
	JsonParser parser = new JsonParser();

	@Autowired
	InstanceServices service;

	@RequestMapping(value = "/getAllInstances", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.GET)
	public @ResponseBody List<InstancesDTO> getAllInstances(HttpEntity<String> httpEntity) {

		return service.getAllInstances();

	}

	@RequestMapping(value = "/startInstance", consumes = { "application/json" }, produces = {
			"application/json" }, method = RequestMethod.POST)
	public @ResponseBody StartInstancesResult startInstance(HttpEntity<String> httpEntity) {

		return service.startInstance(httpEntity);

	}

	@RequestMapping(value = "/stopInstance", consumes = { "application/json" }, produces = {
			"application/json" }, method = RequestMethod.POST)
	public @ResponseBody StopInstancesResult stopInstance(HttpEntity<String> httpEntity) {

		return service.stopInstance(httpEntity);
	}

	@RequestMapping(value = "/createInstance", consumes = { "application/json" }, produces = {
			"application/json" }, method = RequestMethod.POST)
	public @ResponseBody RunInstancesResult createInstance(@RequestBody CreateInstanceDTO createInstancedto) {

		return service.createInstance(createInstancedto);

	}

	@RequestMapping(value = "/terminateInstance", consumes = { "application/json" }, produces = {
			"application/json" }, method = RequestMethod.POST)
	public @ResponseBody TerminateInstancesResult createInstance(@RequestBody InstancesDTO Instancesdto) {

		return service.terminateInstance(Instancesdto.getInstanceId());

	}

}
