package com.cleverbank.dao.impl;

import com.cleverbank.dao.DatabaseConnection;
import com.cleverbank.dao.IBankDAO;
import com.cleverbank.model.Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankDAO implements IBankDAO {
	@Override
	public void create(Bank bank) {
		String sql = "INSERT INTO banks (name, location) VALUES (?, ?)";

		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, bank.getName());
			statement.setString(2, bank.getLocation());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Bank getById(Long id) {
		String sql = "SELECT * FROM banks WHERE id = ?";
		ResultSet resultSet;

		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, id);

			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Bank bank = new Bank();
				bank.setId(resultSet.getLong("id"));
				bank.setName(resultSet.getString("name"));
				bank.setLocation(resultSet.getString("location"));
				return bank;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Bank> getAll() {
		List<Bank> banks = new ArrayList<>();
		String sql = "SELECT * FROM banks";
		ResultSet resultSet;

		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Bank bank = new Bank();
				bank.setId(resultSet.getLong("id"));
				bank.setName(resultSet.getString("name"));
				bank.setLocation(resultSet.getString("location"));
				banks.add(bank);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return banks;
	}

	@Override
	public void update(Bank bank) {
		String sql = "UPDATE banks SET name = ?, location = ? WHERE id = ?";

		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, bank.getName());
			statement.setString(2, bank.getLocation());
			statement.setLong(3, bank.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM banks WHERE id = ?";

		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
