/**
 * 
 */
package me.walkable;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import me.walkable.db.DatabaseUtil;
import me.walkable.db.Deal;
import me.walkable.db.ExpireDeals;
import me.walkable.groupon.Groupon;
import me.walkable.groupon.GrouponObject;
import me.walkable.groupon.GrouponParse;
import me.walkable.util.UtcTime;
import me.walkable.util.WriteJsonFile;

/**
 * @author Christopher Butera
 *
 */
public class RefreshGroupon {

	public static void getGroupon(String city){
		Groupon groupon = new Groupon();
		String gResponse = groupon.search(city);
		WriteJsonFile.writeFile("Groupon", gResponse); //Write to file
		GrouponObject obj = GrouponParse.parse(gResponse); //Parse json to object
		GrouponParse.InsertGrouponData(obj);  // Insert object into Mysql
		System.out.println(obj.deals.length + " Groupon deals processed");
	}


	public static void main(String[] args) {

		getGroupon("chicago");
		Connection conn = null;

		try {
			conn = DatabaseUtil.getConnection();
			ExpireDeals.expireDealsByDate(conn);
			ExpireDeals.expireRemovedDeals(conn, Deal.VENDOR_GROUPON);
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
