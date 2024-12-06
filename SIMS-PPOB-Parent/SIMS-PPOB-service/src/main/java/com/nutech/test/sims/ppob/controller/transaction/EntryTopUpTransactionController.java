package com.nutech.test.sims.ppob.controller.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutech.test.sims.ppob.dto.transaction.EntryTransactionRequestDto;
import com.nutech.test.sims.ppob.response.ResponseGeneral;
import com.nutech.test.sims.ppob.service.transaction.EntryTransactionService;


@RestController
@RequestMapping("/auth")
public class EntryTopUpTransactionController {
	
	@Autowired
	private EntryTransactionService entryTransaction;
	
	
	@PostMapping("/topup")
	public ResponseGeneral entryTopUp(@RequestBody EntryTransactionRequestDto request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return entryTransaction.entryTopUp(request, authentication);
		
	}
	
	@PostMapping("/transaction")
	public ResponseGeneral transactionService(@RequestBody EntryTransactionRequestDto request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return entryTransaction.transactionService(request, authentication);
	}

}
