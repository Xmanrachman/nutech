package com.nutech.test.sims.ppob.dto.response.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_NULL)
public class ResponseServicePPOBDto {
	
	@JsonProperty("service_code")
	private String serviceCode;
	@JsonProperty("service_name")
	private String serviceName;
	@JsonProperty("service_icon")
	private String serviceIcon;
	@JsonProperty( "service_tarif")
	private Integer serviceTarif;

}
