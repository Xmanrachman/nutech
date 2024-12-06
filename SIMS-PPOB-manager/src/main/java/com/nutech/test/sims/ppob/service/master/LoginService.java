package com.nutech.test.sims.ppob.service.master;

import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutech.test.sims.ppob.dao.model.user.UserEntity;
import com.nutech.test.sims.ppob.dto.master.user.LoginRequestDto;
import com.nutech.test.sims.ppob.dto.response.error.ResponseHandlingError;
import com.nutech.test.sims.ppob.dto.response.result.LoginTokenResponse;
import com.nutech.test.sims.ppob.mapper.UserMapper;
import com.nutech.test.sims.ppob.repository.UserRepository;
import com.nutech.test.sims.ppob.response.ResponseGeneral;
import com.nutech.test.sims.ppob.service.jwt.Jwtservice;
import com.nutech.test.sims.ppob.service.utility.UtilityPatternMatches;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginService {

	@Autowired
	private ResponseHandlingError handlingError;

	@Autowired
	private UtilityPatternMatches utilityPatternMatches;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private Jwtservice jwtService;

	@Autowired
	private UserRepository userRepo;

	public ResponseGeneral loginProcess(LoginRequestDto request) {

		ResponseGeneral response = new ResponseGeneral();
		String regexPattern = "^(.+)@(\\S+)$";

		if (request == null) {
			response = handlingError.errorBadRequest();
			return response;
		}

		boolean matches = utilityPatternMatches.patternMatches(request.getEmail(), regexPattern);
		if (matches != true) {
			response = handlingError.errorMatcheLengthEmail();
			return response;
		}
		int lengthPassword = request.getPassword().length();
		if (lengthPassword < 8) {
			response = handlingError.errorMatcheLengthPassword();
			return response;
		}
		log.info("checking authenticated ");
		UserEntity authenticationUser = userMapper.authenticate(request);
		Optional<UserEntity> userFind = userRepo.findByEmailUser(authenticationUser.getEmailUser());
		
		log.info("After login userFind");
		
		if (!userFind.isPresent()) {
			response = handlingError.errorUnAuthorized();
			return response;
		}

		log.info("in the process token ");
		// proses create token jwt

		String jwtToken = jwtService.generateToken(userFind.get());

		LoginTokenResponse responseToken = new LoginTokenResponse();
		responseToken.setToken(jwtToken);
		response.setStatus(HttpStatus.SC_OK);
		response.setMessage("Login Sukses");
		response.setData(responseToken);

		return response;
	}

}
