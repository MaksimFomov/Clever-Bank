package com.cleverbank.model;

import com.cleverbank.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
	private Long id;
	private Account sourceAccount;
	private Account targetAccount;
	private BigDecimal amount;
	private LocalDateTime timestamp;
	private TransactionType type;
}
