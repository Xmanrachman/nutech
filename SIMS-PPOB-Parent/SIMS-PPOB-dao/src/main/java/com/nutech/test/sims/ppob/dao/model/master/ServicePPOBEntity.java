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
@Entity(name = "servicePPOBEntity")
@Table(name ="service_ppob")
@AllArgsConstructor
@NoArgsConstructor
public class ServicePPOBEntity implements Serializable {
	private static final long serialVersionUID = 0l;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_service")
	private Long idService;
	@Column(name = "service_code")
	private String serviceCode;
	@Column(name = "service_name")
	private String serviceName;
	@Column(name = "service_icon")
	private String serviceIcon;
	@Column(name = "service_tarif")
	private Integer serviceTarif;
	

}
