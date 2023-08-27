package com.cleverbank.dao.impl;

import com.cleverbank.dao.DatabaseConnection;
import com.cleverbank.dao.IUserDAO;
import com.cleverbank.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
	@Override
	public void create(User user) {
		String sql = "INSERT INTO users (first_name, last_name, birth_date, email) VALUES (?, ?, ?, ?)";

		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, java.sql.Date.valueOf(user.getBirthDate()));
			statement.setString(4, user.getEmail());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User getById(Long id) {
		String sql = "SELECT * FROM users WHERE id = ?";
		ResultSet resultSet;

		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getLong("id"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
				user.setEmail(resultSet.getString("email"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> getAll() {
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM users";
		ResultSet resultSet;

		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getLong("id"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
				user.setEmail(resultSet.getString("email"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public void update(User user) {
		String sql = "UPDATE users SET first_name = ?, last_name = ?, birth_date = ?, email = ? WHERE id = ?";

		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, java.sql.Date.valueOf(user.getBirthDate()));
			statement.setString(4, user.getEmail());
			statement.setLong(5, user.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM users WHERE id = ?";

		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

