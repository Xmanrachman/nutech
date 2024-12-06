package com.nutech.test.sims.ppob.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutech.test.sims.ppob.dto.master.user.LoginRequestDto;
import com.nutech.test.sims.ppob.response.ResponseGeneral;
import com.nutech.test.sims.ppob.service.master.LoginService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@Slf4j
public class LoginUserController {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	public ResponseGeneral loginUser(@RequestBody LoginRequestDto request) {
		log.info("Checking to in login ");
		return loginService.loginProcess(request);
	}

}
