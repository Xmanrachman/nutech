package com.nutech.test.sims.ppob.service.master;

import java.io.IOException;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nutech.test.sims.ppob.dao.model.user.UserEntity;
import com.nutech.test.sims.ppob.dto.master.user.UpdateUserRequestDto;
import com.nutech.test.sims.ppob.dto.response.error.ResponseHandlingError;
import com.nutech.test.sims.ppob.dto.response.result.ResponseProfileDto;
import com.nutech.test.sims.ppob.mapper.UserMapper;
import com.nutech.test.sims.ppob.repository.UserRepository;
import com.nutech.test.sims.ppob.response.ResponseGeneral;
import com.nutech.test.sims.ppob.service.utility.ImageUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UpdateProfileService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ResponseHandlingError handlingError;

	@Autowired
	private UserMapper userMapper;
	
	public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

	public ResponseGeneral updateProfileUser(UpdateUserRequestDto requestProfileUser, Authentication authentication) {

		ResponseGeneral response = new ResponseGeneral();

		UserEntity currentUser = chechCurrentUser(authentication);

		Optional<UserEntity> userFind = userRepo.findByEmailUser(currentUser.getEmailUser());

		if (!userFind.isPresent()) {
			response = handlingError.errorUnAuthoritationExpired();
			return response;
		}

		UserEntity userEntity = userMapper.updateProfileUser(userFind.get(), requestProfileUser);
		userRepo.saveAndFlush(userEntity);
		if (userEntity.getIdUser() != 0) {
			ResponseProfileDto responseResult = new ResponseProfileDto();
			responseResult = infoDataProfile(userEntity, responseResult);

			response.setStatus(HttpStatus.SC_OK);
			response.setMessage("Update Pofile berhasil");
			response.setData(responseResult);

		}

		return response;

	}

	public ResponseGeneral updateProfileImage(MultipartFile file,  Authentication authentication) {
		ResponseGeneral response = new ResponseGeneral();

		UserEntity currentUser = chechCurrentUser(authentication);
		Optional<UserEntity> userFind = userRepo.findByEmailUser(currentUser.getEmailUser());
		
		if (!userFind.isPresent()) {
			response = handlingError.errorUnAuthoritationExpired();
			return response;
		}
		
		String contentType = file.getContentType();
		log.info("Checking data "+contentType);
		if (!contentType.equals("image/jpeg") && !contentType.equals("image/png")) {
			log.info("Checking data1 ");
			
			response = handlingError.errorFormatIamge();
			return response;
		}
		log.info("Checking data2 ");
		
		
		UserEntity dataUserCurrent = null;
		try {
			var imageToSave = UserEntity.builder()
			        .nameImage(file.getOriginalFilename())
			        .typeImage(file.getContentType())
			        .imageData(ImageUtils.compressImage(file.getBytes()))
			        .build();
			
			dataUserCurrent = userFind.get();
			dataUserCurrent.setNameImage(imageToSave.getNameImage());
			dataUserCurrent.setTypeImage(imageToSave.getTypeImage());
			dataUserCurrent.setImageData(imageToSave.getImageData());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        userRepo.save(dataUserCurrent);
        
        ResponseProfileDto responseResult = new ResponseProfileDto();
		responseResult = infoDataProfile(userFind.get(), responseResult);
		
        response.setStatus(HttpStatus.SC_OK);
		response.setMessage("Update Pofile Image berhasil");
		response.setData(responseResult);
		
		return response;

	}

	private ResponseProfileDto infoDataProfile(UserEntity user, ResponseProfileDto responseResult) {

		responseResult.setEmail(user.getEmailUser());
		responseResult.setFirstName(user.getFirstName());
		responseResult.setLastName(user.getLastName());
		responseResult.setProfileImage(user.getProfileImage());

		return responseResult;

	}

	private UserEntity chechCurrentUser(Authentication auth) {
		UserEntity user = (UserEntity) auth.getPrincipal();

		return user;
	}

}
