package com.mybatis.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainTest {

	/**
	 * 原生的jdbc代码,用于测试数据库连接驱动是否可行
	 * @param args
	 */
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/tests?characterEncoding=utf-8",
							"root", "root");
			String sql = "SELECT * FROM user WHERE username = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "张三丰");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println("id:" + resultSet.getString("id")
						+ "\tusername:" + resultSet.getString("username")
						+ "\tbirthday:" + resultSet.getString("birthday")
						+ "\tsex" + resultSet.getString("sex") + "\taddress:"
						+ resultSet.getString("address"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
