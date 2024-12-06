package com.nutech.test.sims.ppob.dao.model.history;

import java.io.Serializable;

import com.nutech.test.sims.ppob.dao.model.transaction.TransactionEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "transactionHistoryEntity")
@Table(name = "transaction_history")
@AllArgsConstructor
@NoArgsConstructor
public class TransactionHistoryEntity implements Serializable {

	private static final long serialVersionUID = 0l;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_transaction_history")
	private Long idTransactionHistory;
	
	@Column(name = "description")
	private String description;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_transaction", referencedColumnName = "id_transaction")
	private TransactionEntity transaction;
	
	
}
