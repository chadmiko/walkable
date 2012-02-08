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

	private	static final String userName = "java_walk";
	private	static final String password = "daburgh";
	private	static final String dbms = "mysql";
	private	static final String serverName = "walkable.me";
	private	static final String portNumber = "3306";
	private	static final String dbName = "walk";

	public static Connection getConnection()
			throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);

		conn = DriverManager.
				getConnection(
						"jdbc:" + dbms + "://" +
								serverName +
								":" + portNumber + "/" + dbName,
								connectionProps);
		return conn;
	}

	//    Connection con, String dbName)
	//    throws SQLException {
	//    Statement stmt = null;
	//    String query =
	//        "select COF_NAME, SUP_ID, PRICE, " +
	//        "SALES, TOTAL " +
	//        "from " + dbName + ".COFFEES";
	//    try {
	//        stmt = con.createStatement();
	//        ResultSet rs = stmt.executeQuery(query);
	//        while (rs.next()) {
	//            String coffeeName =
	//                rs.getString("COF_NAME");
	//            int supplierID = rs.getInt("SUP_ID");
	//            float price = rs.getFloat("PRICE");
	//            int sales = rs.getInt("SALES");
	//            int total = rs.getInt("TOTAL");
	//            System.out.println(
	//                coffeeName + "\t" + supplierID +
	//                "\t" + price + "\t" + sales +
	//                "\t" + total);
	//        }
	//    } catch (SQLException e ) {
	//        JDBCTutorialUtilities.printSQLException(e);
	//    } finally {
	//        if (stmt != null) { stmt.close(); }
	//	
}
