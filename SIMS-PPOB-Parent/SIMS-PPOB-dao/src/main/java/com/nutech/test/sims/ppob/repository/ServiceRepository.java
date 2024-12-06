package com.nutech.test.sims.ppob.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.nutech.test.sims.ppob.dao.model.master.ServicePPOBEntity;

@Repository
public interface ServiceRepository extends JpaRepository<ServicePPOBEntity, Long>, JpaSpecificationExecutor<ServicePPOBEntity> {
	
	Optional<ServicePPOBEntity> findByServiceCode(String serviceCode);

}
