package com.cleverbank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	private Long id;
	private String accountNumber;
	private BigDecimal balance;
	private User user;
	private Bank bank;
	private LocalDate creationDate;
	private boolean isBlocked;
}
