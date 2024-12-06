package com.nutech.test.sims.ppob.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import com.nutech.test.sims.ppob.dao.model.master.ServicePPOBEntity;
import com.nutech.test.sims.ppob.dto.response.result.ResponseServicePPOBDto;

@Component
public class ServicePPOBMapper {
	
	
	
	public List<Object> getAllService(List<ServicePPOBEntity> request) {
		List<Object> dataBanner = new ArrayList<>();

		
		for (ServicePPOBEntity servicePPOBLoop : request) {
			ResponseServicePPOBDto servicePPOB = new ResponseServicePPOBDto();
			
			servicePPOB.setServiceCode(servicePPOBLoop.getServiceCode());
			servicePPOB.setServiceName(servicePPOBLoop.getServiceName());
			servicePPOB.setServiceIcon(servicePPOBLoop.getServiceIcon());
			servicePPOB.setServiceTarif(servicePPOBLoop.getServiceTarif());
			dataBanner.add(servicePPOB);
		}

		return dataBanner;

	}
	
}
