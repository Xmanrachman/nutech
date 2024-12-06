package com.nutech.test.sims.ppob.controller.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutech.test.sims.ppob.response.ResponseGeneral;
import com.nutech.test.sims.ppob.service.transaction.InqueryTransactionService;

@RestController
@RequestMapping("/auth")
public class InqueryBalanceController {
	
	@Autowired
	private InqueryTransactionService inqTransactionService;
	
	
	@GetMapping("/balance")
	public ResponseGeneral inqueryBalanceUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return inqTransactionService.getBalanceUser(authentication);
	}

}
