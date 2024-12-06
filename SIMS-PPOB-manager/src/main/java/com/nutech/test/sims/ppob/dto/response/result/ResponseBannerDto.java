package com.nutech.test.sims.ppob.dto.response.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseBannerDto {

	@JsonProperty("banner_name")
	private String bannerName;
	@JsonProperty("banner_image")
	private String bannerImage;
	@JsonProperty("description")
	private String description;
}
