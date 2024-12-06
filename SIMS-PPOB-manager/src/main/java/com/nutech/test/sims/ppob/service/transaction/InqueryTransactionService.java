package com.nutech.test.sims.ppob.service.transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.nutech.test.sims.ppob.dao.model.history.TransactionHistoryEntity;
import com.nutech.test.sims.ppob.dao.model.transaction.TransactionEntity;
import com.nutech.test.sims.ppob.dao.model.user.UserEntity;
import com.nutech.test.sims.ppob.dto.response.ResponseTransactionHis;
import com.nutech.test.sims.ppob.dto.response.error.ResponseHandlingError;
import com.nutech.test.sims.ppob.dto.response.result.ResponseBalanceDto;
import com.nutech.test.sims.ppob.dto.response.result.ResponseTransactionHistoryDto;
import com.nutech.test.sims.ppob.repository.TransactionHistoryRepository;
import com.nutech.test.sims.ppob.repository.TransactionRepository;
import com.nutech.test.sims.ppob.repository.UserRepository;
import com.nutech.test.sims.ppob.response.ResponseGeneral;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InqueryTransactionService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ResponseHandlingError handlingError;

	@Autowired
	private TransactionRepository transactionRepo;

	@Autowired
	private TransactionHistoryRepository transactionHisRepo;

	public ResponseGeneral getBalanceUser(Authentication authentication) {

		ResponseGeneral response = new ResponseGeneral();
		ResponseBalanceDto responseBalance = new ResponseBalanceDto();
		UserEntity currentUser = chechCurrentUser(authentication);

		Optional<UserEntity> userFind = userRepo.findByEmailUser(currentUser.getEmailUser());

		if (!userFind.isPresent()) {
			response = handlingError.errorUnAuthoritationExpired();
			return response;
		}
		responseBalance = infoResultData(userFind.get(), responseBalance);

		response.setStatus(HttpStatus.SC_OK);
		response.setMessage("Get Balance Berhasil");
		response.setData(responseBalance);

		return response;
	}

	public ResponseGeneral getTransactionHistory(Integer offset, Integer limit) {

		ResponseGeneral response = new ResponseGeneral();
		ResponseTransactionHis responseTransHis = new ResponseTransactionHis();
		List<ResponseTransactionHistoryDto> transactionHisList = null;
		ResponseTransactionHistoryDto transHisDto = null;
		if (limit == 0) {
			List<TransactionEntity> transactionHis = transactionRepo.findAll();
			transactionHisList = new ArrayList<>();
			Comparator<TransactionEntity> comporator = (c1, c2) -> {
				return Long.valueOf(c2.getCreatedOn().getTime()).compareTo(c1.getCreatedOn().getTime());
			};
			Collections.sort(transactionHis, comporator);

			for (TransactionEntity transactionLoop : transactionHis) {
				transHisDto = new ResponseTransactionHistoryDto();
				log.info("Checking id transaction" + transactionLoop.getIdTransaction());
				TransactionHistoryEntity transactionHistory = transactionHisRepo.findByTransaction(transactionLoop);
				log.info("Checking id transactionHistory" + transactionHistory.getDescription());

				transHisDto.setInvoiceNumber(transactionLoop.getInvoiceNumber());
				transHisDto.setTransactionType(transactionLoop.getTransactionType());
				transHisDto.setDescription(transactionHistory.getDescription());
				transHisDto.setCreatedOn(transactionLoop.getCreatedOn());
				transHisDto.setTotalAmount(transactionLoop.getTotalAmount());
				transactionHisList.add(transHisDto);

			}

			responseTransHis.setOffset(offset);
			responseTransHis.setLimit(limit);
			responseTransHis.setRecords(transactionHisList);

			response.setStatus(HttpStatus.SC_OK);
			response.setMessage("Get History Berhasil");
			response.setData(responseTransHis);

		} else {
			List<TransactionEntity> transaction = transactionRepo.findAll();

			Comparator<TransactionEntity> comporator = (c1, c2) -> {
				return Long.valueOf(c2.getCreatedOn().getTime()).compareTo(c1.getCreatedOn().getTime());
			};
			Collections.sort(transaction, comporator);

			Integer limitTransaction = 0;
			transaction.stream().sorted(comporator)
					.limit(limitTransaction < limit ? limitTransaction : limit)
					.collect(Collectors.toList());
			
			log.info("transaction"+transaction);
			transactionHisList = new ArrayList<>();
			for ( int i = 1; i <= limit; i++) {
				for (TransactionEntity transactionLoop : transaction)  {
				transHisDto = new ResponseTransactionHistoryDto();
				TransactionHistoryEntity transactionHistory = transactionHisRepo.findByTransaction(transactionLoop);
				log.info("how many looping entity");
				transHisDto.setInvoiceNumber(transactionLoop.getInvoiceNumber());
				transHisDto.setTransactionType(transactionLoop.getTransactionType());
				transHisDto.setDescription(transactionHistory.getDescription());
				transHisDto.setCreatedOn(transactionLoop.getCreatedOn());
				transHisDto.setTotalAmount(transactionLoop.getTotalAmount());
				transactionHisList.add(transHisDto);
				}

			}
			log.info("println " + transactionHisList);
			responseTransHis.setOffset(offset);
			responseTransHis.setLimit(limit);
			responseTransHis.setRecords(transactionHisList);

			response.setStatus(HttpStatus.SC_OK);
			response.setMessage("Get History Berhasil");
			response.setData(responseTransHis);
		}

		return response;
	}

	private ResponseBalanceDto infoResultData(UserEntity user, ResponseBalanceDto responseResult) {
		responseResult.setBalance(user.getSaldo());
		return responseResult;

	}

	private UserEntity chechCurrentUser(Authentication auth) {
		UserEntity user = (UserEntity) auth.getPrincipal();

		return user;
	}

}
