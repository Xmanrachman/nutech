package com.nutech.test.sims.ppob.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.nutech.test.sims.ppob.dao.model.user.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
	
	Optional<UserEntity> findByEmailUser(String emailUser);
	
	Optional<UserEntity> findByEmailUserAndPassword(String emailUser, String Password);
	
	

}
