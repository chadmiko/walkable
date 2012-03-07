/**
 * 
 */
package me.walkable.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * @author Christopher Butera
 *
 */
public class ExpireDeals {

	//Expire all deals if they haven't been updated for this much time - this is currently most useful for Yelp
	public final static String expireTime = "00:05:00";  // 5mins
	
	public static void expireRemovedDeals(Connection conn, String vendor){
		String insertDealHistory = "INSERT INTO deals_history "
				+ "(did, vendor, title, link_url, start_date, end_date, utc_offset, updated_at, items) "
				+ "(SELECT did, vendor, title, link_url, start_date, end_date, utc_offset, updated_at, items "
				+ "FROM deals "
				+ "WHERE SUBTIME(UTC_TIMESTAMP(), '" + expireTime + "') > updated_at  "
				+ "AND vendor = ? ) ";
		String insertDealByLocations = "INSERT INTO deal_locations_history "
				+ "(SELECT dl.did, dl.lid "
				+ "FROM deals d, deal_locations dl "
				+ "WHERE d.did = dl.did " 
				+ "AND SUBTIME(UTC_TIMESTAMP(), '" + expireTime + "') > d.updated_at "
				+ "AND vendor = ? ) ";
		String deleteDealHistory = "DELETE FROM deals "
				+ "WHERE did IN "
				+ "(SELECT did "
				+ "FROM deals_history) ";
		String deleteDealByLocationHistory = "DELETE FROM deal_locations "
				+ "WHERE did IN "
				+ "(SELECT did "
				+ "FROM deals_history) ";		


		PreparedStatement ps = null;
		ResultSet rs = null;
		int numDeleted = 0;
		try {

			ps = conn.prepareStatement(insertDealHistory);
			ps.setString(1, vendor);
			ps.executeUpdate();
			ps = conn.prepareStatement(insertDealByLocations);
			ps.setString(1, vendor);
			ps.executeUpdate();			
			ps = conn.prepareStatement(deleteDealHistory, Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();			
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				numDeleted = rs.getInt(1);
			}
			ps = conn.prepareStatement(deleteDealByLocationHistory);
			ps.executeUpdate();			

			System.out.println("Expired " + numDeleted + " deals because they were removed by " + vendor);

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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /*ignored*/ }
			}

		}

	}


	public static void expireDealsByDate(Connection conn){
		String insertDealHistory = "INSERT INTO deals_history "
				+ "(did, vendor, title, link_url, start_date, end_date, utc_offset, updated_at, items) "
				+ "(SELECT did, vendor, title, link_url, start_date, end_date, utc_offset, updated_at, items "
				+ "FROM deals "
				+ "WHERE UTC_TIMESTAMP() > end_date ) ";
		String insertDealByLocations = "INSERT INTO deal_locations_history "
				+ "(SELECT dl.did, dl.lid "
				+ "FROM deals d, deal_locations dl "
				+ "WHERE d.did = dl.did " 
				+ "AND UTC_TIMESTAMP > d.end_date ) ";
		String deleteDealHistory = "DELETE FROM deals "
				+ "WHERE did IN "
				+ "(SELECT did "
				+ "FROM deals_history) ";
		String deleteDealByLocationHistory = "DELETE FROM deal_locations "
				+ "WHERE did IN "
				+ "(SELECT did "
				+ "FROM deals_history) ";		



		PreparedStatement ps = null;
		ResultSet rs = null;
		int numDeleted = 0;

		try {
			ps = conn.prepareStatement(insertDealHistory);
			ps.executeUpdate();
			ps = conn.prepareStatement(insertDealByLocations);
			ps.executeUpdate();			
			ps = conn.prepareStatement(deleteDealHistory, Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				numDeleted = rs.getInt(1);
			}
			ps = conn.prepareStatement(deleteDealByLocationHistory);
			ps.executeUpdate();			
			
			System.out.println("Expired " + numDeleted + " deals because they expired");

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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /*ignored*/ }
			}

		}

	}

	public static void main(String[] args) {
		Connection conn = null;

		try {
			conn = DatabaseUtil.getConnection();

			expireDealsByDate(conn);
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
