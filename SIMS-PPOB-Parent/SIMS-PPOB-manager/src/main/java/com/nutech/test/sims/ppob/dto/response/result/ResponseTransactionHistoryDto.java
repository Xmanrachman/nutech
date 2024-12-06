package com.nutech.test.sims.ppob.dto.response.result;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseTransactionHistoryDto {
	
	@JsonProperty("invoice_number")
	private String invoiceNumber;
	
	@JsonProperty("transaction_type")
	private String transactionType;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("total_amount")
	private Integer totalAmount;
	
	@JsonProperty("created_on")
	private Date createdOn;
	

}
