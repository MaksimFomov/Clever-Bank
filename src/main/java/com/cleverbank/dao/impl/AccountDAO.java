package com.cleverbank.dao.impl;

import com.cleverbank.dao.DatabaseConnection;
import com.cleverbank.dao.IAccountDAO;
import com.cleverbank.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO implements IAccountDAO {
	@Override
	public void create(Account account) {
		String sql = "INSERT INTO accounts (account_number, balance, user_id, bank_id, creation_date, is_blocked) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, account.getAccountNumber());
			statement.setBigDecimal(2, account.getBalance());
			statement.setLong(3, account.getUser().getId());
			statement.setLong(4, account.getBank().getId());
			statement.setDate(5, java.sql.Date.valueOf(account.getCreationDate()));
			statement.setBoolean(6, account.isBlocked());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Account getById(Long id) {
		String sql = "SELECT * FROM accounts WHERE id = ?";
		ResultSet resultSet;

		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Account account = new Account();
				account.setId(resultSet.getLong("id"));
				account.setAccountNumber(resultSet.getString("account_number"));
				account.setBalance(resultSet.getBigDecimal("balance"));
				account.setCreationDate(resultSet.getDate("creation_date").toLocalDate());
				account.setBlocked(resultSet.getBoolean("is_blocked"));
				return account;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Account> getAll() {
		List<Account> accounts = new ArrayList<>();
		String sql = "SELECT * FROM accounts";
		ResultSet resultSet;

		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Account account = new Account();
				account.setId(resultSet.getLong("id"));
				account.setAccountNumber(resultSet.getString("account_number"));
				account.setBalance(resultSet.getBigDecimal("balance"));
				account.setCreationDate(resultSet.getDate("creation_date").toLocalDate());
				account.setBlocked(resultSet.getBoolean("is_blocked"));
				accounts.add(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public void update(Account account) {
		String sql = "UPDATE accounts SET account_number = ?, balance = ?, user_id = ?, bank_id = ?, creation_date = ?, is_blocked = ? WHERE id = ?";

		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, account.getAccountNumber());
			statement.setBigDecimal(2, account.getBalance());
			statement.setLong(3, account.getUser().getId());
			statement.setLong(4, account.getBank().getId());
			statement.setDate(5, java.sql.Date.valueOf(account.getCreationDate()));
			statement.setBoolean(6, account.isBlocked());
			statement.setLong(7, account.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM accounts WHERE id = ?";

		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

