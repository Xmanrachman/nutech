package com.nutech.test.sims.ppob.dto.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EntryTransactionRequestDto {
	
	@JsonProperty("top_up_amount")
	private Integer topUpAmount;
	
	@JsonProperty("service_code")
	private String serviceCode;
	
	
}
