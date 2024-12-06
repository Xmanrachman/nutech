package com.nutech.test.sims.ppob.service.master;

import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.nutech.test.sims.ppob.dao.model.user.UserEntity;
import com.nutech.test.sims.ppob.dto.response.error.ResponseHandlingError;
import com.nutech.test.sims.ppob.dto.response.result.ResponseProfileDto;
import com.nutech.test.sims.ppob.repository.UserRepository;
import com.nutech.test.sims.ppob.response.ResponseGeneral;


@Service
public class InqueryProfileService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ResponseHandlingError handlingError;
	
	
	public ResponseGeneral getAllProfile(Authentication authentication) {
		
		ResponseGeneral response = new ResponseGeneral();
		ResponseProfileDto responseResult = new ResponseProfileDto();

        UserEntity currentUser = (UserEntity) authentication.getPrincipal();
        Optional<UserEntity> userFind = userRepo.findByEmailUser(currentUser.getEmailUser());
        
        if (!userFind.isPresent()) {
			response = handlingError.errorUnAuthoritationExpired();
			return response;
		}
        
        responseResult.setEmail(currentUser.getEmailUser());
        responseResult.setFirstName(currentUser.getFirstName());
        responseResult.setLastName(currentUser.getLastName());
        responseResult.setProfileImage(currentUser.getProfileImage());

        response.setStatus(HttpStatus.SC_OK);
        response.setMessage("Sukses");
        response.setData(responseResult);
			 
		return response;
				
	}

}
