package com.cleverbank.model;

import com.cleverbank.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
	private long id;
	private Account sourceAccount;
	private Account destinationAccount;
	private double amount;
	private LocalDateTime timestamp;
	private TransactionType transactionType;
}
