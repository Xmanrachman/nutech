package com.nutech.test.sims.ppob.mapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.nutech.test.sims.ppob.dao.model.user.UserEntity;
import com.nutech.test.sims.ppob.dto.master.user.EntryUserRequestDto;
import com.nutech.test.sims.ppob.dto.master.user.LoginRequestDto;
import com.nutech.test.sims.ppob.dto.master.user.UpdateUserRequestDto;
import com.nutech.test.sims.ppob.dto.transaction.EntryTransactionRequestDto;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserMapper {

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;


	public UserMapper(AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
	}

	public UserEntity registrationUser(EntryUserRequestDto input) {
		UserEntity user = new UserEntity();

		user.setFirstName(input.getFirstName());
		user.setEmailUser(input.getEmail());
		user.setLastName(input.getLastName());
		user.setPassword(passwordEncoder.encode(input.getPassword()));

		return user;
	}

	public UserEntity authenticate(LoginRequestDto input) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));
		log.info("Checking in the process authenticate");
		
		UserEntity userEntity = new UserEntity();
		userEntity.setEmailUser(input.getEmail());
		userEntity.setPassword(input.getPassword());
		
			
		return userEntity;
	}
	
	
	public UserEntity updateProfileUser(UserEntity user, UpdateUserRequestDto requestEdit) {
		
		user.setFirstName(requestEdit.getFirstName());
		user.setLastName(requestEdit.getLastName());
		
		return user;
	}
	
	public UserEntity topUpTransaction(EntryTransactionRequestDto requestTopUp, UserEntity user) {
		
		UserEntity userUpdate = new UserEntity();
		
		log.info("Checking requestupAmount :"+requestTopUp.getTopUpAmount()+" "+user.getSaldo());
		if (user.getSaldo() == null) 
			user.setSaldo(0);
		Integer calculatedSaldo = user.getSaldo() + requestTopUp.getTopUpAmount();
		
		userUpdate.setIdUser(user.getIdUser());
		userUpdate.setEmailUser(user.getEmailUser());
		userUpdate.setFirstName(user.getFirstName());
		userUpdate.setLastName(user.getLastName());
		userUpdate.setPassword(user.getPassword());
		userUpdate.setProfileImage(user.getProfileImage());
		userUpdate.setSaldo(calculatedSaldo);
		
		log.info("Checking userUpdate "+userUpdate);
		return userUpdate;
	}

}
