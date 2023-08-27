package com.cleverbank.dao;

import com.cleverbank.model.Transaction;

import java.util.List;

public interface ITransactionDAO {
	void create(Transaction transaction);
	Transaction getById(Long id);
	List<Transaction> getAll();
	void update(Transaction transaction);
	void delete(Long id);
}
