package com.nutech.test.sims.ppob.service.master;


import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutech.test.sims.ppob.dao.model.user.UserEntity;
import com.nutech.test.sims.ppob.dto.master.user.EntryUserRequestDto;
import com.nutech.test.sims.ppob.dto.response.error.ResponseHandlingError;
import com.nutech.test.sims.ppob.mapper.UserMapper;
import com.nutech.test.sims.ppob.repository.UserRepository;
import com.nutech.test.sims.ppob.response.ResponseGeneral;
import com.nutech.test.sims.ppob.service.utility.UtilityPatternMatches;


@Service
public class EntryRegistrationUserService {

	@Autowired
	private ResponseHandlingError handlingError;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UtilityPatternMatches utilityPatternMatches;
	

	public ResponseGeneral entryRegistrationUser(EntryUserRequestDto requestRegUser) {

		ResponseGeneral response = new ResponseGeneral();
		String data = "null";
		String regexPattern = "^(.+)@(\\S+)$";

		if (requestRegUser == null) {
			response = handlingError.errorBadRequest();
			return response;
		}

		boolean matches = utilityPatternMatches.patternMatches(requestRegUser.getEmail(), regexPattern);
		if (matches != true) {
			response = handlingError.errorMatcheLengthEmail();
			return response;
		}
		int lengthPassword = requestRegUser.getPassword().length();
		if (lengthPassword < 8) {
			response = handlingError.errorMatcheLengthPassword();
			return response;
		}
		
		UserEntity user = userMapper.registrationUser(requestRegUser);
		
		userRepo.saveAndFlush(user);

		response.setStatus(HttpStatus.SC_OK);
		response.setMessage("Registrasi berhasil silahkan login");
		response.setData(data);

		return response;

	}
	
}
