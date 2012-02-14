package me.walkable.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 */

/**
 * @author Christopher Butera
 *
 */
public class DealByLocation {

	private int did; //Deal ID
	private int lid; // Location ID

	public void insertDealByLocation(Connection conn){
		String insertDealByLocation = "INSERT INTO deal_by_location "
				+ "(did, lid) "
				+ "VALUES(?, ?)";
		PreparedStatement ps = null;
//		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(insertDealByLocation, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, this.did);
			ps.setInt(2, this.lid);
			ps.executeUpdate();
			
//		    rs = ps.getGeneratedKeys();
//		    if (rs.next()) {
//		        lid = rs.getInt(1);
//		    } else {
//		    	throw new SQLException("Failed to insert");
//		        // throw an exception from here
//		    }
		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
			//Ignore Duplicate
			System.out.println("Found Duplicate Deal By Location");
			
		} catch (SQLException e) {
			System.out.println(ps.toString());
			e.printStackTrace();
		} finally {
//          if (rs != null) {
//              try {
//                  rs.close();
//              } catch (SQLException e) { /*ignored*/ }
//          }
          if (ps != null) {
              try {
                  ps.close();
              } catch (SQLException e) { /*ignored*/ }
          }
		}
		//Return void
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}
	
	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

}