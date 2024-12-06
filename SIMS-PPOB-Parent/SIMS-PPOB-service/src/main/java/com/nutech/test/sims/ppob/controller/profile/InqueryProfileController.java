package com.nutech.test.sims.ppob.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutech.test.sims.ppob.response.ResponseGeneral;
import com.nutech.test.sims.ppob.service.master.InqueryProfileService;

@RestController
@RequestMapping("/auth")
public class InqueryProfileController {
	
	@Autowired
	private InqueryProfileService inqueryProfileService;
	
	@GetMapping("/profile")
	public ResponseGeneral getAllProfile() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return inqueryProfileService.getAllProfile(authentication);
		
	}

}
