package com.nutech.test.sims.ppob.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.nutech.test.sims.ppob.dao.model.transaction.TransactionEntity;
import com.nutech.test.sims.ppob.dto.response.result.ResponseTransactionHistoryDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseTransactionHis {

	
	private Integer offset;
	
	private Integer limit;
	
	private List<ResponseTransactionHistoryDto> records;
}
