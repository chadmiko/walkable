/**
 * 
 */
package me.walkable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import me.walkable.cj.CommissionJunction;
import me.walkable.cj.CommissionJunctionObject;
import me.walkable.cj.CommissionJunctionParse;
import me.walkable.cj.giltcity.GiltCityParse;
import me.walkable.db.DatabaseUtil;
import me.walkable.db.Deal;
import me.walkable.db.ExpireDeals;
import me.walkable.groupon.Groupon;
import me.walkable.groupon.GrouponObject;
import me.walkable.groupon.GrouponParse;
import me.walkable.util.WriteArchiveFile;

/**
 * @author Christopher Butera
 *
 */
public class RefreshGiltCity {

	public static int getGiltCity(){
		CommissionJunction cj = new CommissionJunction();
		String cjXML = cj.search(CommissionJunction.VENDOR_GILT_CITY);
		WriteArchiveFile.writeFile("GiltCity", cjXML, ".xml"); //Write to file
		ArrayList<CommissionJunctionObject> cjList = CommissionJunctionParse.parse(cjXML);
		int numGilt = GiltCityParse.parse(cjList);
		System.out.println("Done inserting/updating " + numGilt + " deals");
		System.out.println((cjList.size() - numGilt) + " deals not inserted due to no location");
		return numGilt;
	}


	public static void main(String[] args) {

		int numDeals = getGiltCity();

		if (numDeals > 0){
			Connection conn = null;

			try {
				conn = DatabaseUtil.getConnection();
				ExpireDeals.expireDealsByDate(conn);
				ExpireDeals.expireRemovedDeals(conn, Deal.VENDOR_GILT_CITY);
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
