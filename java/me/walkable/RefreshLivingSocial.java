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

		Connection conn = null;
		int attempts = 0;
		int numDeals = LivingSocial.parse(LivingSocial.URL_CHICAGO);
		try {

			while (numDeals <= 0 && attempts < 60){
				Thread.sleep(60000); //Sleep for 60 seconds and try again
				numDeals = LivingSocial.parse(LivingSocial.URL_CHICAGO);
				++attempts;
			}		
			System.out.println("Processed " + numDeals + " LivingSocial Deals with Locations");

			if (numDeals > 0){

				conn = DatabaseUtil.getConnection();
				ExpireDeals.expireDealsByDate(conn);
				ExpireDeals.expireRemovedDeals(conn, Deal.VENDOR_LIVING_SOCIAL);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();

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

