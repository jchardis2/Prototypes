package com.infinityappsolutions.webdesigner.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.infinityappsolutions.server.lib.dao.AbstractDAOFactory;
import com.infinityappsolutions.server.lib.dao.IConnectionDriver;

public class TestDAOFactory extends AbstractDAOFactory implements
		IConnectionDriver {

	private static AbstractDAOFactory testInstance;

	public static AbstractDAOFactory getTestInstance() {
		if (testInstance == null)
			testInstance = new TestDAOFactory();
		return testInstance;
	}

	private TestDAOFactory() {

	}

	@Override
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/proto",
				"ias", "mytestpassword");
	}
}
