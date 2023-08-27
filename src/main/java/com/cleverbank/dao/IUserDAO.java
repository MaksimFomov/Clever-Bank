package com.cleverbank.dao;

import com.cleverbank.model.User;

import java.util.List;

public interface IUserDAO {
	void create(User user);
	User getById(Long id);
	List<User> getAll();
	void update(User user);
	void delete(Long id);
}
