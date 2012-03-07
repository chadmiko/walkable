package me.walkable.yelp;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.walkable.db.*;

/**
 * 
 */

/**
 * @author Christopher Butera
 *
 */
public class YelpParse {

	public static YelpDealObject parse(String json){
		Gson gson = new GsonBuilder().create();
		YelpDealObject object = gson.fromJson(json, YelpDealObject.class);
		//		InsertYelpData(object);
		return object;
	}

	public static void InsertYelpData(YelpDealObject obj){
		if (obj != null && obj.getNumberOfDeals() > 0){
			Connection conn = null;
			YelpDealData[] deals = obj.businesses;

			try {
				conn = DatabaseUtil.getConnection();

				for (YelpDealData yDeals : deals){
					DealByLocation dealByLocation = new DealByLocation();
					int did=0;
					int lid=0;
					int numDeals = 0;
					int numOptions=0;
					if (yDeals == null)
						break;
					for (YelpDealData.YelpDeal yDeal : yDeals.deals){
						for (YelpDealData.YelpDeal.YelpOptions yOption : yDeal.options){
							
							Deal deal = new Deal();
							deal.setVendor(Deal.VENDOR_YELP);

							DealItems items = new DealItems();
							ArrayList<DealItems.Options> opts = new ArrayList<DealItems.Options>();
							
							
							//Parse Title
//							String title = yDeal.what_you_get;
//							if (title.contains("\n")){
//								title = title.substring(0, title.indexOf('\n'));
//							}
//							deal.setTitle(title);
							deal.setTitle(yOption.title);
							deal.setLink_url(yOption.purchase_url);
							Timestamp startDate = new Timestamp(yDeal.time_start * 1000);
							//						startDate.setTime(yDeal.time_start);
							deal.setStart_date(startDate);
							//						System.out.println(yDeal.time_start + ": " + new java.util.Date().getTime());
							//				deal.setEnd_date(end_date)  Yelp Provides no enddate
							if (yOption.is_quantity_limited)
								deal.setRemaining_quantity(yOption.remaining_count);
							else
								deal.setRemaining_quantity(Deal.REMAINING_QUANTITY_UNLIMITED);
							deal.setPrice(new Double(yOption.price).doubleValue() / 100.00);
							deal.setValue(new Double(yOption.original_price).doubleValue() / 100.00);
							deal.setDiscount( 1 - (deal.getPrice() / deal.getValue()));
							
							//Set Options
							DealItems.Options dealOpts = items.new Options();
							dealOpts.setTitle(yDeal.what_you_get);
							dealOpts.setBuyUrl(yOption.purchase_url);
							opts.add(dealOpts);
							deal.setItems(opts);

							//Insert Deal
							did = deal.insertDeal(conn);
							dealByLocation.setDid(did);
							
							++numOptions;
						}
						++numDeals;
					}
					if (numDeals > 1)
						System.err.println("Found " + numDeals + " within Yelp Deal Object");
					if (numOptions > 1)
						System.err.println("Found " + numOptions + " within Yelp Deal Object");

					
					YelpDealData.YelpLocation yLoc = yDeals.location;
					Location location = new Location();
					//Parse Yelp Address ToDo
					if (yLoc.display_address.length > 0) {
						location.setStreet(yLoc.display_address[0]);
						if (yLoc.display_address.length > 1){
							location.setStreet2(yLoc.display_address[1]);
							if (yLoc.display_address.length > 2){
								//								System.out.println("More than 2 lines of display address");
							}
						}
					}
					//				location.setNeighborhood(yLoc);  //Is there Neighborhood data?
					//				location.setZip(yLoc.); //Zip unfortunately is encoded with display address
					location.setLat(yLoc.coordinate.latitude);
					location.setLng(yLoc.coordinate.longitude);
					location.setName(yDeals.name);
					//No URL Provided by Yelp
					if (did > 0){
						lid = location.insertLocation(conn);
						if (lid > 0){
							dealByLocation.setLid(lid);
							dealByLocation.insertDealByLocation(conn);
						}
					}

				}


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




