package com.infinityappsolutions.webdesigner.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.infinityappsolutions.server.lib.dao.AbstractDAOFactory;

/**
 * This TestDataGenerator class is in charge of centralizing all of the test
 * data calls. Most of the SQL is in the sql/something.sql files. A few design
 * conventions:
 * 
 * <ul>
 * <li>Any time you're using this class, be sure to run the "clearAllTables"
 * first. This is not a very slow method (it's actually quite fast) but it
 * clears all of the tables so that no data from a previous test can affect your
 * current test.</li>
 * <li>We do not recommend having one test method call another test method
 * (except "standardData" or other intentionally "meta" methods). For example,
 * loincs() should not call patient1() first. Instead, put BOTH patient1() and
 * loincs() in your test case. If we keep this convention, then every time you
 * call a method, you know that ONLY your sql file is called and nothing else.
 * The alternative is a lot of unexpected, extraneous calls to some test methods
 * like patient1().</li>
 * </ul>
 * 
 * 
 * 
 */
public class TestDataGenerator {
	public static void main(String[] args) throws IOException, SQLException,
			NamingException {
		TestDataGenerator gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();

	}

	private String DIR = "sql/data";

	private AbstractDAOFactory factory;

	public TestDataGenerator() {
		this.factory = TestDAOFactory.getTestInstance();
	}

	public TestDataGenerator(String projectHome, AbstractDAOFactory factory) {
		this.DIR = projectHome + "src/main/resources/sql/data";
		this.factory = factory;
	}

	public void clearAllTables() throws SQLException, FileNotFoundException,
			IOException, NamingException {
		new DBBuilder(factory).executeSQLFile(DIR + "/deleteFromAllTables.sql");
	}

	public void standardData() throws FileNotFoundException, IOException,
			SQLException, NamingException {
		standardUsers();
		standardRoles();
		standardUserRoles();
		standardOrgs();
		standardOrgUsers();
	}

	public void standardUsers() throws SQLException, FileNotFoundException,
			IOException, NamingException {
		new DBBuilder(factory).executeSQLFile(DIR + "/standardUsers.sql");
	}

	public void standardRoles() throws SQLException, FileNotFoundException,
			IOException, NamingException {
		new DBBuilder(factory).executeSQLFile(DIR + "/standardRoles.sql");
	}

	public void standardUserRoles() throws SQLException, FileNotFoundException,
			IOException, NamingException {
		new DBBuilder(factory).executeSQLFile(DIR + "/standardUserRoles.sql");
	}

	public void standardOrgs() throws SQLException, FileNotFoundException,
			IOException, NamingException {
		new DBBuilder(factory).executeSQLFile(DIR + "/standardOrgs.sql");
	}

	public void standardOrgUsers() throws SQLException, FileNotFoundException,
			IOException, NamingException {
		new DBBuilder(factory).executeSQLFile(DIR + "/standardOrgUsers.sql");
	}

}