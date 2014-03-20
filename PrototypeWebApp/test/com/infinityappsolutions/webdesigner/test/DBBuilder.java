package com.infinityappsolutions.webdesigner.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.naming.NamingException;

import com.infinityappsolutions.server.lib.dao.AbstractDAOFactory;
import com.infinityappsolutions.server.lib.dao.SQLFileCache;

/**
 * Drops and rebuilds the entire database. Also provides some utility methods.
 * DO NOT PUT TEST DATA HERE!!!
 * 
 * 
 */
public class DBBuilder {
	public static int numExecuted = 0;
	public static long queryTimeTaken = 0;

	private AbstractDAOFactory factory;

	public DBBuilder() {
		factory = TestDAOFactory.getTestInstance();
	}

	public DBBuilder(AbstractDAOFactory factory) {
		this.factory = factory;
	}

	public static void main(String[] args) throws Exception {
		rebuildAll();
	}

	public static void rebuildAll() throws FileNotFoundException, IOException, SQLException, NamingException {
		DBBuilder dbBuilder = new DBBuilder(TestDAOFactory.getTestInstance());
		dbBuilder.dropTables();
		dbBuilder.createTables();
		System.out.println("Operation Completed");
	}

	public void dropTables() throws FileNotFoundException, IOException, SQLException, NamingException {
		List<String> queries = SQLFileCache.getInstance().getQueries("sql/dropTables.sql");
		executeSQL(queries);
		System.out.println("Tables dropped.");
	}

	public void createTables() throws FileNotFoundException, IOException, SQLException, NamingException {
		List<String> queries = SQLFileCache.getInstance().getQueries("sql/createTables.sql");
		executeSQL(queries);
		System.out.println("Tables created.");
	}

	public void executeSQL(List<String> queries) throws SQLException, NamingException {
		Connection conn = factory.getConnection();
		long start = System.currentTimeMillis();
		for (String sql : queries) {
			System.out.println("Executing: " + sql);
			// System.out.println("\"" + sql.replace("\"", "\\'") + "\",");
			numExecuted++;
			Statement stmt = conn.createStatement();
			try {
				stmt.execute(sql);
			} catch (SQLException e) {
				throw new SQLException(e.getMessage() + " from executing: " + sql, e.getSQLState(), e.getErrorCode());
			} finally {
				stmt.close();
			}
		}
		queryTimeTaken += (System.currentTimeMillis() - start);
		System.out.println("Queries executed: " + numExecuted + "\nTime executing queries: " + queryTimeTaken + "ms");
		conn.close();
	}

	public void executeSQLFile(String filepath) throws FileNotFoundException, SQLException, IOException, NamingException {
		executeSQL(SQLFileCache.getInstance().getQueries((filepath)));
	}
}
