package com.nutech.test.sims.ppob.controller.servicePPOB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutech.test.sims.ppob.response.ResponseService;
import com.nutech.test.sims.ppob.service.master.InqueryServicePPOBService;

@RestController
@RequestMapping("/auth")
public class InqueryServicePPOBController {
	
	@Autowired
	private InqueryServicePPOBService inqServicePPOB;
	
	@GetMapping("/services")
	public ResponseService getAllDataService() {
		return inqServicePPOB.getAllService();
		
	}

}
