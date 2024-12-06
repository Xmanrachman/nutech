package com.nutech.test.sims.ppob.dao.model.transaction;

import java.io.Serializable;
import java.util.Date;



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
@Entity(name = "transaction")
@Table (name = "transaction_sims")
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity implements Serializable {
	
	private static final long serialVersionUID = 0l;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_transaction")
	private Long idTransaction;
	@Column(name = "invoice_number")
	private String invoiceNumber;
	@Column(name = "service_code")
	private String serviceCode;
	@Column(name = "service_name")
	private String serviceName;
	@Column(name = "transaction_type")
	private String transactionType;
	@Column(name = "total_amount")
	private Integer totalAmount;
	@Column(name = "create_on")
	private Date createdOn;

}
