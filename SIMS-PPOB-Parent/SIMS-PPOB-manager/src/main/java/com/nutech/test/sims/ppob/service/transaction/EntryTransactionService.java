package com.nutech.test.sims.ppob.service.transaction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.nutech.test.sims.ppob.dao.model.history.TransactionHistoryEntity;
import com.nutech.test.sims.ppob.dao.model.master.ServicePPOBEntity;
import com.nutech.test.sims.ppob.dao.model.transaction.TransactionEntity;
import com.nutech.test.sims.ppob.dao.model.user.UserEntity;
import com.nutech.test.sims.ppob.dto.response.error.ResponseHandlingError;
import com.nutech.test.sims.ppob.dto.response.result.ResponseBalanceDto;
import com.nutech.test.sims.ppob.dto.response.result.ResponseTransactionServiceDto;
import com.nutech.test.sims.ppob.dto.transaction.EntryTransactionRequestDto;
import com.nutech.test.sims.ppob.mapper.UserMapper;
import com.nutech.test.sims.ppob.repository.ServiceRepository;
import com.nutech.test.sims.ppob.repository.TransactionHistoryRepository;
import com.nutech.test.sims.ppob.repository.TransactionRepository;
import com.nutech.test.sims.ppob.repository.UserRepository;
import com.nutech.test.sims.ppob.response.ResponseGeneral;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EntryTransactionService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private TransactionRepository transactionRepo;

	@Autowired
	private TransactionHistoryRepository transactionHistoryRepo;

	@Autowired
	private ServiceRepository serviceRepo;

	@Autowired
	private ResponseHandlingError handlingError;

	@Autowired
	private UserMapper userMapper;

	public ResponseGeneral entryTopUp(EntryTransactionRequestDto request, Authentication authentication) {

		ResponseGeneral response = new ResponseGeneral();
		ResponseBalanceDto responseBalance = new ResponseBalanceDto();
		UserEntity currentUser = chechCurrentUser(authentication);

		Optional<UserEntity> userFind = userRepo.findByEmailUser(currentUser.getEmailUser());

		if (!userFind.isPresent()) {
			response = handlingError.errorUnAuthoritationExpired();
			return response;
		}

		if (request.getTopUpAmount() < 0) {
			response = handlingError.errorBadRequestAmount();
			return response;
		}

		log.info("in the entryTOpUp");
		UserEntity user = userMapper.topUpTransaction(request, userFind.get());
		log.info("in the TOpUp");
		userRepo.saveAndFlush(user);
		log.info("getIdUser :" + user.getIdUser());
		if (user.getIdUser() != 0) {

			String transaction_type = "TOPUP";
			createTransaction(transaction_type, userFind.get().getSaldo(), request.getTopUpAmount(), userFind.get());

		}

		log.info("in before infoResultData");
		responseBalance = infoResultData(user, responseBalance);
		log.info("in after infoResultData " + responseBalance);

		response.setStatus(HttpStatus.SC_OK);
		response.setMessage("Top Up Balance berhasil");
		response.setData(responseBalance);

		return response;
	}

	public ResponseGeneral transactionService(EntryTransactionRequestDto request, Authentication authentication) {
		ResponseGeneral response = new ResponseGeneral();
		UserEntity currentUser = chechCurrentUser(authentication);
		ResponseTransactionServiceDto responseResult = new ResponseTransactionServiceDto();

		log.info("Check in the transactionService");
		Optional<UserEntity> userFind = userRepo.findByEmailUser(currentUser.getEmailUser());

		if (!userFind.isPresent()) {
			response = handlingError.errorUnAuthoritationExpired();
			return response;
		}

		if (userFind.get().getSaldo() < 0) {
			response = handlingError.errorBadRequestAmount();
			return response;
		}

		Optional<ServicePPOBEntity> dataService = serviceRepo.findByServiceCode(request.getServiceCode());
		if (!dataService.isPresent()) {
			response = handlingError.errorBadRequestService();
			return response;
		}

		log.info("in before checkBalance");
		boolean checkBalance = checkSaldo(userFind.get(), dataService.get().getServiceTarif());
		if (checkBalance == false) {
			response = handlingError.errorBadRequest();
			return response;
		}

		log.info("in before service code");
		
		if (request.getServiceCode().equalsIgnoreCase("PULSA")) {
			log.info("Check in the pulsa");
			responseResult = createTransactionService(request.getServiceCode(), userFind.get().getSaldo(),
					dataService.get().getServiceTarif(), userFind.get());
		} else if (request.getServiceCode().equalsIgnoreCase("PAJAK")) {
			responseResult = createTransactionService(request.getServiceCode(), userFind.get().getSaldo(),
					dataService.get().getServiceTarif(), userFind.get());
		} else if (request.getServiceCode().equalsIgnoreCase("PLN")) {
			responseResult = createTransactionService(request.getServiceCode(), userFind.get().getSaldo(),
					dataService.get().getServiceTarif(), userFind.get());
		} else if (request.getServiceCode().equalsIgnoreCase("PGN")) {
			responseResult = createTransactionService(request.getServiceCode(), userFind.get().getSaldo(),
					dataService.get().getServiceTarif(), userFind.get());
		} else if (request.getServiceCode().equalsIgnoreCase("MUSIK")) {
			responseResult = createTransactionService(request.getServiceCode(), userFind.get().getSaldo(),
					dataService.get().getServiceTarif(), userFind.get());
		} else if (request.getServiceCode().equalsIgnoreCase("TV")) {
			responseResult = createTransactionService(request.getServiceCode(), userFind.get().getSaldo(),
					dataService.get().getServiceTarif(), userFind.get());
		} else if (request.getServiceCode().equalsIgnoreCase("PAKET_DATA")) {
			responseResult = createTransactionService(request.getServiceCode(), userFind.get().getSaldo(),
					dataService.get().getServiceTarif(), userFind.get());
		} else if (request.getServiceCode().equalsIgnoreCase("VOUCHER_GAME")) {
			responseResult = createTransactionService(request.getServiceCode(), userFind.get().getSaldo(),
					dataService.get().getServiceTarif(), userFind.get());
		} else if (request.getServiceCode().equalsIgnoreCase("VOUCHER_MAKANAN")) {
			responseResult = createTransactionService(request.getServiceCode(), userFind.get().getSaldo(),
					dataService.get().getServiceTarif(), userFind.get());
		} else if (request.getServiceCode().equalsIgnoreCase("QURBAN")) {
			responseResult = createTransactionService(request.getServiceCode(), userFind.get().getSaldo(),
					dataService.get().getServiceTarif(), userFind.get());
		} else if (request.getServiceCode().equalsIgnoreCase("ZAKAT")) {
			responseResult = createTransactionService(request.getServiceCode(), userFind.get().getSaldo(),
					dataService.get().getServiceTarif(), userFind.get());
		}

		response.setStatus(HttpStatus.SC_OK);
		response.setMessage("Transaksi berhasil");
		response.setData(responseResult);

		return response;

	}

	public ResponseTransactionServiceDto createTransactionService(String transactionType, Integer saldo, Integer amount,
			UserEntity user) {
		TransactionEntity transaction = new TransactionEntity();
		String dateTransaction = convertDate(new Date(), "ddMMyyyy");
		String generatedInv = generatedInvoice(dateTransaction, user.getIdUser());

		ResponseTransactionServiceDto responseResult = new ResponseTransactionServiceDto();
		log.info("in the createTransaction");
		Integer calculated = 0;

		log.info("in else ");
		calculated = saldo - amount;
		transaction.setServiceCode(transactionType);
		transaction.setServiceName(transactionType);
		transaction.setInvoiceNumber(generatedInv);
		transaction.setCreatedOn(new Date());
		transaction.setTotalAmount(amount);
		transaction.setTransactionType(transactionType);
		
		user.setSaldo(calculated);
		userRepo.saveAndFlush(user);

		transactionRepo.saveAndFlush(transaction);
		if (transaction.getIdTransaction() != 0) {
			TransactionHistoryEntity transactionHis = new TransactionHistoryEntity();
			transactionHis.setDescription(transactionType);
			transactionHis.setTransaction(transaction);
			transactionHistoryRepo.save(transactionHis);

			responseResult.setInvoiceNumber(transaction.getInvoiceNumber());
			responseResult.setServiceCode(transaction.getServiceCode());
			responseResult.setServiceName(transaction.getServiceName());
			responseResult.setTransactionType("PAYMENT");
			responseResult.setTotalAmount(transaction.getTotalAmount());
			responseResult.setCreatedOn(transaction.getCreatedOn());
		}

		return responseResult;

	}

	public void createTransaction(String transactionType, Integer saldo, Integer amount, UserEntity user) {

		TransactionEntity transaction = new TransactionEntity();
		String dateTransaction = convertDate(new Date(), "ddMMyyyy");
		String generatedInv = generatedInvoice(dateTransaction, user.getIdUser());

		
		if (transactionType == "TOPUP") {
			log.info("in the  transactionType is TopUp");
			transaction.setServiceCode(transactionType);
			transaction.setServiceName(transactionType);
			transaction.setInvoiceNumber(generatedInv);
			transaction.setCreatedOn(new Date());
			transaction.setTotalAmount(amount);
			transaction.setTransactionType(transactionType);
			
			transactionRepo.saveAndFlush(transaction);
			if (transaction.getIdTransaction() != 0) {
				TransactionHistoryEntity transactionHis = new TransactionHistoryEntity();
				transactionHis.setDescription(transactionType);
				transactionHis.setTransaction(transaction);
				transactionHistoryRepo.save(transactionHis);
			}

		}
	}

	private ResponseBalanceDto infoResultData(UserEntity user, ResponseBalanceDto responseResult) {
		log.info("info saldo after topup");
		responseResult.setBalance(user.getSaldo());
		return responseResult;

	}

	private UserEntity chechCurrentUser(Authentication auth) {
		UserEntity user = (UserEntity) auth.getPrincipal();
		return user;
	}

	private String generatedInvoice(String date, Long idTransaction) {
		String generated = "INV" + date + String.valueOf(idTransaction);
		return generated;

	}

	private String convertDate(Date date, String formatDate) {
		SimpleDateFormat dateSdf = new SimpleDateFormat(formatDate);
		String dateConvert = dateSdf.format(date);

		return dateConvert;

	}

	private boolean checkSaldo(UserEntity user, Integer payment) {
		boolean checkingSaldo = true;
		if (user.getSaldo() < payment) {
			checkingSaldo = false;
		}

		return checkingSaldo;
	}
}
