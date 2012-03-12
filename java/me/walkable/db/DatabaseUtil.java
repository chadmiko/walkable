package me.walkable.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 */

/**
 * @author Christopher Butera
 *
 */
public class DatabaseUtil {

	public static Connection getConnection()
			throws SQLException {

		Connection conn = null;
		Properties prop = new Properties();
		String dbUser, dbPass, dbms, serverName, portNumber, dbName;
		try {
			prop.load(new FileInputStream("./db.properties"));
			dbUser = prop.getProperty("db_user");
			dbPass = prop.getProperty("db_pass");
			dbms = prop.getProperty("dbms");
			serverName = prop.getProperty("server_name");
			portNumber = prop.getProperty("port_number");
			dbName = prop.getProperty("db_name");
			
			
			if (dbms.equals("mysql")){
				Class.forName("com.mysql.jdbc.Driver");
			}
			
			Properties connectionProps = new Properties();
			connectionProps.put("user", dbUser);
			connectionProps.put("password", dbPass);

			conn = DriverManager.
					getConnection(
							"jdbc:" + dbms + "://" +
									serverName +
									":" + portNumber + "/" + dbName,
									connectionProps);


		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return conn;
	}
}
