package com.cleverbank.dao;

import com.cleverbank.model.Bank;

import java.util.List;

public interface IBankDAO {
	void create(Bank bank);
	Bank getById(Long id);
	List<Bank> getAll();
	void update(Bank bank);
	void delete(Long id);
}
