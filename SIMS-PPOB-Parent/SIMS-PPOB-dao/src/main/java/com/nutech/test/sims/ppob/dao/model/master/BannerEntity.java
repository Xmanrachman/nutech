package com.nutech.test.sims.ppob.dao.model.master;

import java.io.Serializable;


import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "bannerEntity")
@Table(name = "banner_sims")
@AllArgsConstructor
@NoArgsConstructor
public class BannerEntity implements Serializable {
	
	private static final long serialVersionUID = 0l;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_banner")
	private Long idBanner;
	@Column(name = "banner_name")
	private String bannerName;
	@Column(name = "banner_image")
	private String bannerImage;
	@Column(name = "description")
	private String description;
	
	
	
	

}
