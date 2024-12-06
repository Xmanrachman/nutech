package com.nutech.test.sims.ppob.controller.profile;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nutech.test.sims.ppob.dto.master.user.UpdateUserRequestDto;
import com.nutech.test.sims.ppob.response.ResponseGeneral;
import com.nutech.test.sims.ppob.service.master.UpdateProfileService;


@RestController
@RequestMapping("/auth")

public class UpdateProfileController {
	
	@Autowired
	private UpdateProfileService updateProfileService;
	
	
	
	@PutMapping("/profile/update")
	public ResponseGeneral updateProfile(@RequestBody UpdateUserRequestDto request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return updateProfileService.updateProfileUser(request, authentication);
	}
	
	@PutMapping(value = "/profile/image", consumes = "multipart/form-data")
	public ResponseGeneral updateProfileImage(@RequestParam("file") MultipartFile fileRequest) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		
		return updateProfileService.updateProfileImage(fileRequest, authentication);
	}
	

}
