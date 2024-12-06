package com.nutech.test.sims.ppob.dto.response.result;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseTransactionServiceDto {
	
	@JsonProperty("invoice_number")
	private String invoiceNumber;
	
	@JsonProperty("service_code")
	private String serviceCode;
	
	@JsonProperty("service_name")
	private String serviceName;
	
	@JsonProperty("transaction_type")
	private String transactionType;
	
	@JsonProperty("total_amount")
	private Integer totalAmount;
	
	@JsonProperty("created_on")
	private Date createdOn;
	

}
