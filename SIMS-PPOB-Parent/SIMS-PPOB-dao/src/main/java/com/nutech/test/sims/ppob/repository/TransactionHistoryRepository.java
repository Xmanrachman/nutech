package com.nutech.test.sims.ppob.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.nutech.test.sims.ppob.dao.model.history.TransactionHistoryEntity;
import com.nutech.test.sims.ppob.dao.model.transaction.TransactionEntity;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistoryEntity, Long>, JpaSpecificationExecutor<TransactionHistoryEntity>{

	
	TransactionHistoryEntity findByTransaction(TransactionEntity transaction);
	
	
}
