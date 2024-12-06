package com.nutech.test.sims.ppob.controller.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutech.test.sims.ppob.response.ResponseBanner;

import com.nutech.test.sims.ppob.service.master.InqueryBannerService;

@RestController
@RequestMapping("/auth")
public class InqueryBannerController {

	@Autowired
	private InqueryBannerService inqBannerService;

	@GetMapping("/banner")
	public ResponseBanner getAllBanner() {
		return inqBannerService.getAllBanner();
	}

}
