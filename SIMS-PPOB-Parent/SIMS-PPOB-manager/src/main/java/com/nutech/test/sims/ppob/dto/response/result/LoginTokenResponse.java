package com.nutech.test.sims.ppob.dto.response.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginTokenResponse {
	
	@JsonProperty("token")
	private String token;
	
	@JsonProperty("expiresIn")
	private long expiresIn;
}
