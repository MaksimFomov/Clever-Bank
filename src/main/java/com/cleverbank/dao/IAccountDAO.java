package com.cleverbank.dao;

import com.cleverbank.model.Account;

import java.util.List;

public interface IAccountDAO {
	void create(Account account);
	Account getById(Long id);
	List<Account> getAll();
	void update(Account account);
	void delete(Long id);
}
