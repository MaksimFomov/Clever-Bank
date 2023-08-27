package com.cleverbank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	private long id;
	private String accountNumber;
	private double balance;
	private User user;
	private Bank bank;
}
