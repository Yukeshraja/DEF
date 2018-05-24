package com.altimetrik.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.altimetrik.dto.KeyPairDTO;
import com.altimetrik.services.KeyPairService;
import com.amazonaws.services.ec2.model.CreateKeyPairResult;

/**
 * @author nmuthusamy
 *
 */
@Controller
public class KeyPairController {

	@Autowired
	KeyPairService service;
	
	@RequestMapping(value = "/getAllKeyPairs", produces = "application/json", 
			consumes = "application/json", method = RequestMethod.GET )
	public @ResponseBody List<KeyPairDTO> getAllKeyPairs(){
		return service.getAllKeyPairs();
	}
	
	@RequestMapping(value = "/createKeyPair", produces = "application/json", 
			consumes = "application/json", method = RequestMethod.POST )
	public @ResponseBody CreateKeyPairResult createKeyPair(@RequestBody KeyPairDTO keyPairDTO){
		 return service.createKeyPair(keyPairDTO);
	}
	
}
