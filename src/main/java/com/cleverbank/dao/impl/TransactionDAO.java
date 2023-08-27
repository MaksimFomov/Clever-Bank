package com.cleverbank.dao.impl;

import com.cleverbank.dao.DatabaseConnection;
import com.cleverbank.dao.ITransactionDAO;
import com.cleverbank.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO implements ITransactionDAO {
	@Override
	public void create(Transaction transaction) {
		String sql = "INSERT INTO transactions (source_account_id, target_account_id, amount, timestamp) VALUES (?, ?, ?, ?)";

		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, transaction.getSourceAccount().getId());
			statement.setLong(2, transaction.getTargetAccount().getId());
			statement.setBigDecimal(3, transaction.getAmount());
			statement.setTimestamp(4, java.sql.Timestamp.valueOf(transaction.getTimestamp()));
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Transaction getById(Long id) {
		String sql = "SELECT * FROM transactions WHERE id = ?";
		ResultSet resultSet;

		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Transaction transaction = new Transaction();
				transaction.setId(resultSet.getLong("id"));
				transaction.setAmount(resultSet.getBigDecimal("amount"));
				transaction.setTimestamp(resultSet.getTimestamp("timestamp").toLocalDateTime());
				return transaction;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Transaction> getAll() {
		List<Transaction> transactions = new ArrayList<>();
		String sql = "SELECT * FROM transactions";
		ResultSet resultSet;

		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Transaction transaction = new Transaction();
				transaction.setId(resultSet.getLong("id"));
				transaction.setAmount(resultSet.getBigDecimal("amount"));
				transaction.setTimestamp(resultSet.getTimestamp("timestamp").toLocalDateTime());
				transactions.add(transaction);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactions;
	}

	@Override
	public void update(Transaction transaction) {
		String sql = "UPDATE transactions SET source_account_id = ?, target_account_id = ?, amount = ?, timestamp = ? WHERE id = ?";

		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, transaction.getSourceAccount().getId());
			statement.setLong(2, transaction.getTargetAccount().getId());
			statement.setBigDecimal(3, transaction.getAmount());
			statement.setTimestamp(4, java.sql.Timestamp.valueOf(transaction.getTimestamp()));
			statement.setLong(5, transaction.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM transactions WHERE id = ?";
		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
