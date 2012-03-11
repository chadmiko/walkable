/**
 * 
 */
package me.walkable;

import java.sql.Connection;
import java.sql.SQLException;

import me.walkable.db.DatabaseUtil;
import me.walkable.db.Deal;
import me.walkable.db.ExpireDeals;
import me.walkable.livingsocial.LivingSocial;

/**
 * @author Christopher Butera
 *
 */
public class RefreshLivingSocial {

	public static void main(String[] args) {

		int numDeals = LivingSocial.parse(LivingSocial.URL_CHICAGO);
		System.out.println("Processed " + numDeals + " LivingSocial Deals with Locations");
		
		if (numDeals > 0){
			Connection conn = null;

			try {
				conn = DatabaseUtil.getConnection();
				ExpireDeals.expireDealsByDate(conn);
				ExpireDeals.expireRemovedDeals(conn, Deal.VENDOR_LIVING_SOCIAL);
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
	
}
