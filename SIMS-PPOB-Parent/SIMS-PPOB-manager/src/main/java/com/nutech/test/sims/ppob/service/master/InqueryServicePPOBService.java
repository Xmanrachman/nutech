package com.nutech.test.sims.ppob.service.master;

import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutech.test.sims.ppob.dao.model.master.ServicePPOBEntity;
import com.nutech.test.sims.ppob.dto.response.error.ResponseHandlingError;
import com.nutech.test.sims.ppob.mapper.ServicePPOBMapper;
import com.nutech.test.sims.ppob.repository.ServiceRepository;
import com.nutech.test.sims.ppob.response.ResponseService;

@Service
public class InqueryServicePPOBService {
	
	@Autowired
	private ServiceRepository serviceRepo;
	
	@Autowired
	private ResponseHandlingError handlingError;
	
	@Autowired
	private ServicePPOBMapper servicePPOBMapper;
	
	public ResponseService  getAllService() {
		
		ResponseService response = new ResponseService();

		List<ServicePPOBEntity> findAll = serviceRepo.findAll();
		if (findAll.size() <= 0) {
			handlingError.errorBadRequest();
		}
		
		List<Object> servicePPOBDatas = servicePPOBMapper.getAllService(findAll);
		response.setStatus(HttpStatus.SC_OK);
		response.setMessage("Sukses");
		response.setData(servicePPOBDatas);

		return response;

	}
	

}
