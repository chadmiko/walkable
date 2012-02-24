/**
 * 
 */
package me.walkable.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * @author Christopher Butera
 *
 */
public class ExpireDeals {

	public static void expireDeals(Connection conn){
		String insertDealHistory = "INSERT INTO deals_history "
				+ "(SELECT did, vendor, title, link_url, start_date, end_date, utc_offset, updated_at, items "
				+ "FROM deals "
				+ "WHERE UTC_TIMESTAMP > end_date ) ";
		String insertDealByLocations = "INSERT INTO deal_locations_history "
				+ "(SELECT dl.did, dl.lid "
				+ "FROM deals d, deal_locations dl "
				+ "WHERE UTC_TIMESTAMP > d.end_date ) ";
		String deleteDealHistory = "DELETE FROM deals "
				+ "WHERE did IN "
				+ "(SELECT did "
				+ "FROM deal_history) ";
		String deleteDealByLocationHistory = "DELETE FROM deal_locations "
				+ "WHERE did IN "
				+ "(SELECT did "
				+ "FROM deal_history) ";		
		
		
		
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(insertDealHistory);
			ps.executeUpdate();
			ps = conn.prepareStatement(insertDealByLocations);
			ps.executeUpdate();			
			ps = conn.prepareStatement(deleteDealHistory);
			ps.executeUpdate();			
			ps = conn.prepareStatement(deleteDealByLocationHistory);
			ps.executeUpdate();			
			
//		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
//			//Ignore Duplicate
//			//			System.err.println("Found Duplicate Location");

		} catch (SQLException e) {
			System.out.println(ps.toString());
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) { /*ignored*/ }
			}
		}

	}

	public static void main(String[] args) {
		Connection conn = null;

		try {
			conn = DatabaseUtil.getConnection();

			expireDeals(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) { /*ignored*/ }
			}

		}

	}
}
