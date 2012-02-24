package me.walkable.groupon;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.walkable.db.*;
import me.walkable.db.DealItems.Options;

/**
 * 
 */

/**
 * @author Christopher Butera
 *
 */
public class GrouponParse {

	public static GrouponObject parse(String json){
		Gson gson = new GsonBuilder().create();
		GrouponObject object = gson.fromJson(json, GrouponObject.class);
		return object;
	}

	public static void InsertGrouponData(GrouponObject obj){
		Connection conn = null;
		GrouponData[] deals = obj.deals;

		try {
			conn = DatabaseUtil.getConnection();

			for (GrouponData dealData : deals){
				ArrayList<Location> locations = new ArrayList<Location>();
				Deal deal = new Deal();
				DealItems items = new DealItems();
				ArrayList<DealItems.Options> opts = new ArrayList<DealItems.Options>();
				int optionNum=1;
				for (GrouponData.GrouponOptions opt : dealData.options){
					//Lets build the object here and insert later
					if (optionNum == 1){
						//Parse Groupon Date format 
						//      "startAt": "2012-02-12T06:00:48Z" 
						int t = dealData.startAt.indexOf('T');
						int z = dealData.startAt.indexOf('Z');
						String startTime = dealData.startAt.substring(0, t) + " " + dealData.startAt.substring(t+1,  z);
						String endTime = dealData.endAt.substring(0, t) + " " + dealData.endAt.substring(t+1,  z);

						deal.setVendor(Deal.VENDOR_GROUPON);
						deal.setTitle(dealData.announcementTitle);
						deal.setLink_url(dealData.dealUrl);
						deal.setStart_date(Timestamp.valueOf(startTime));
						deal.setEnd_date(Timestamp.valueOf(endTime));
						deal.setOffset(dealData.division.timezoneOffsetInSeconds);
						
						//					if (opt.isLimitedQuantity)
						//						deal.setRemaining_quantity(opt.remainingQuantity);
						//					deal.setPrice(new Double(opt.price.amount).doubleValue() / 100.00 ); //Shift decimal
						//					deal.setValue(new Double(opt.value.amount).doubleValue() / 100.00 ); //Shift decimal
						//					deal.setDiscount( 1 - (deal.getPrice() / deal.getValue()));
						//insert later
						
						//Assumption - Every option is offered at every location
						for (GrouponData.GrouponLocation gLoc : opt.redemptionLocations){
							//Pass mid into location
							Location location = new Location();
							location.setStreet(gLoc.streetAddress1);
							location.setStreet2(gLoc.streetAddress2);
							location.setNeighborhood(gLoc.neighborhood);
							location.setZip(gLoc.postalCode);
							location.setLat(gLoc.lat);
							location.setLng(gLoc.lng);
							location.setName(dealData.merchant.name);
							location.setUrl(dealData.merchant.websiteUrl);
							locations.add(location);
						}

					}
					
					DealItems.Options dealOpts = items.new Options();
					dealOpts.setTitle(opt.title);
					dealOpts.setBuyUrl(opt.buyUrl);
					opts.add(dealOpts);

					++optionNum;
				}
				DealItems.Options[] myOpts = opts.toArray(new DealItems.Options[opts.size()]);
				items.setOptions(myOpts);
				deal.setItems(items);

				//Insert into DB
				int did = deal.insertDeal(conn);
				for (Location loc : locations){
					int lid = loc.insertLocation(conn);

					//Insert row into dealByLocation
					DealByLocation dealByLocation = new DealByLocation();
					if (lid > 0){
						dealByLocation.setDid(did);
						dealByLocation.setLid(lid);
						dealByLocation.insertDealByLocation(conn);
					}
				}//End Insert

			}//End Deal Loop
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) { /*ignored*/ }
			}

			//			String insert2 = "INSERT INTO deals "
			//					+ "VALUES()";
			//			String insert3 = "INSERT INTO groupon "
			//					+ "VALUES('did', 'small_image', 'med_image', 'grid_image', 'pitch', 'highlights', 'placement_priority', 'announcement', 'location_note', 'ship_req', 'is_now_deal', 'fine_print')";

		}


	}

}
