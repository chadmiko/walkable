/**
 * 
 */
package me.walkable;

import java.sql.Connection;
import java.sql.SQLException;

import me.walkable.db.DatabaseUtil;
import me.walkable.db.Deal;
import me.walkable.db.ExpireDeals;
import me.walkable.util.CategoryTree;
import me.walkable.yelp.Yelp;
import me.walkable.yelp.YelpCategory;
import me.walkable.yelp.YelpDealObject;
import me.walkable.yelp.YelpParse;

/**
 * @author Christopher Butera
 *
 */
public class RefreshYelp {


	public static void yelpCategoryIterate(Yelp yelp, CategoryTree tree) throws Exception{

		if (tree.isLeaf()){
			YelpDealObject yObj = yelp.getDeals(Yelp.CHICAGO, "0", tree.getCategory());
			System.out.println("Found " + yObj.getTotalNumberOfDeals() + " in leaf category: " + tree.getCategory());
			YelpParse.InsertYelpData(yObj);
			if (yObj.getNumberOfDeals() != yObj.getTotalNumberOfDeals()){
				if (yObj.getTotalNumberOfDeals() <= 40){
					yObj = yelp.getDeals(Yelp.CHICAGO, "20", tree.getCategory());
					yObj = YelpParse.parse(tree.getUnparsedDeals());	
					YelpParse.InsertYelpData(yObj);
				}
				else {
					throw new Exception("More than 40 deals for child category.  Found " + yObj.getNumberOfDeals() + " " + tree.getCategory() + " in Yelp");
				}
			}
		}
		else {

			YelpDealObject yObj = yelp.getDeals(Yelp.CHICAGO, "0", tree.getCategory());
			System.out.println("Found " + yObj.getTotalNumberOfDeals() + " in branch category: " + tree.getCategory());
			if (yObj.getNumberOfDeals() == yObj.getTotalNumberOfDeals() || yObj.getTotalNumberOfDeals() < 40){
				YelpParse.InsertYelpData(yObj);
			}
			else if (yObj.getNumberOfDeals() != yObj.getTotalNumberOfDeals() && yObj.getTotalNumberOfDeals() < 40) {
				YelpParse.InsertYelpData(yObj);

				yObj = yelp.getDeals(Yelp.CHICAGO, "20", tree.getCategory());
				YelpParse.InsertYelpData(yObj);
			}
			else { //Over 40 deals
				for (CategoryTree subTree : tree.getSubCategories()){
					yelpCategoryIterate(yelp, subTree);
				}
			}
		}

	}

	public static void getYelp() throws Exception{
		Yelp yelp = new Yelp();
		YelpCategory ycats = new YelpCategory();
		for (CategoryTree subTree :ycats.getCategories()){
			yelpCategoryIterate(yelp, subTree);
		}
	}

	public static void main(String[] args) {

		Connection conn = null;

		try {
			getYelp();
			conn = DatabaseUtil.getConnection();
			ExpireDeals.expireDealsByDate(conn);
			ExpireDeals.expireRemovedDeals(conn, Deal.VENDOR_YELP);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
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