package com.nutech.test.sims.ppob.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nutech.test.sims.ppob.dao.model.master.BannerEntity;
import com.nutech.test.sims.ppob.dto.response.result.ResponseBannerDto;

@Component
public class BannerMapper {

	public List<Object> getAllBanner(List<BannerEntity> request) {
		
		List<Object> dataBanner = new ArrayList<>();;
		
		for (BannerEntity bannerLoop : request) {
			ResponseBannerDto banner = new ResponseBannerDto();
			banner.setBannerName(bannerLoop.getBannerName());
			banner.setBannerImage(bannerLoop.getBannerImage());
			banner.setDescription(bannerLoop.getDescription());
			dataBanner.add(banner);
		}
		
		return dataBanner;

	}

}
