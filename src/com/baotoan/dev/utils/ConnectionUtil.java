package com.baotoan.dev.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionUtil {
	private DataSource dataSource;
	private Connection connection;
	private Statement statement;

	public ConnectionUtil() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/UsersDB");
			connection = dataSource.getConnection();
			statement = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Statement getStatement() {
		try {
			if(connection.isClosed()) {
				connection = dataSource.getConnection();
				statement = connection.createStatement();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return statement;
	}

	public PreparedStatement getPreparedStatement(String sql) {
		try {
			if(connection.isClosed()) {
				connection = dataSource.getConnection();
			}
			return connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public CallableStatement getCallableStatement(String sql) {
		try {
			if(connection.isClosed()) {
				connection = dataSource.getConnection();
			}
			return connection.prepareCall(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
