package com.nutech.test.sims.ppob.controller.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nutech.test.sims.ppob.response.ResponseGeneral;
import com.nutech.test.sims.ppob.service.transaction.InqueryTransactionService;

@RestController
@RequestMapping("/auth")
public class InqueryTransactionHistory {
	
	@Autowired
	private InqueryTransactionService inqueryTransaction;
	
	
	@GetMapping("/transaction/history")
	public ResponseGeneral getTransactionHistoryByLimit(@RequestParam("offset") Integer offset, 
			@RequestParam("limit") Integer limit) {
		
		return inqueryTransaction.getTransactionHistory(offset, limit);
		
	}

}
