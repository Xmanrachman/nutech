package com.nutech.test.sims.ppob.service.master;

import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutech.test.sims.ppob.dao.model.master.BannerEntity;
import com.nutech.test.sims.ppob.dto.response.error.ResponseHandlingError;
import com.nutech.test.sims.ppob.mapper.BannerMapper;
import com.nutech.test.sims.ppob.repository.BannerRepository;
import com.nutech.test.sims.ppob.response.ResponseBanner;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InqueryBannerService {

	@Autowired
	private BannerRepository bannerRepo;

	@Autowired
	private BannerMapper bannerMapper;

	@Autowired
	private ResponseHandlingError handlingError;

	public ResponseBanner getAllBanner() {

		ResponseBanner response = new ResponseBanner();

		List<BannerEntity> findAll = bannerRepo.findAll();
		
		if (findAll.size() <= 0) {
			handlingError.errorBadRequest();
		}
		
		List<Object> bannerDatas = bannerMapper.getAllBanner(findAll);
		
		log.info("Checking bannerDatas  :"+bannerDatas);
		response.setStatus(HttpStatus.SC_OK);
		response.setMessage("Sukses");
		response.setData(bannerDatas);

		return response;

	}

}
