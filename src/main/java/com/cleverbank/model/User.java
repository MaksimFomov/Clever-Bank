package com.cleverbank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private Long id;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String email;
	private List<Account> accounts;
}
