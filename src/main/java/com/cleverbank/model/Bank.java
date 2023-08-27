package com.cleverbank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bank {
	private long id;
	private String name;
	private List<Account> accounts;
}
