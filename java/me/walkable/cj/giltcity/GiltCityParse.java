/**
 * 
 */
package me.walkable.cj.giltcity;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import me.walkable.cj.CommissionJunctionObject;
import me.walkable.db.DatabaseUtil;
import me.walkable.db.Deal;
import me.walkable.db.DealByLocation;
import me.walkable.db.Location;
import me.walkable.util.Geocode;

/**
 * @author Christopher Butera
 *
 */
public class GiltCityParse {

	
	/**
	 * @param cjList
	 * @return int //This is the number of deals that gets inserted
	 */
	public static int parse(ArrayList<CommissionJunctionObject> cjList){
		Connection conn = null;
		int numDeals = 0;
		try {
			conn = DatabaseUtil.getConnection();

			String parseUrl = "www.giltcity.com";
			for (CommissionJunctionObject obj : cjList){
				//Parse out the crap at the beginning of the url
				String url = obj.getBuyUrl();
				String[] urlParts = url.split(parseUrl);
				if (urlParts != null && urlParts.length > 1){
					URI uri = new URI(parseUrl + urlParts[1]);
					obj.setBuyUrl("http://" + uri.getPath());
					Document doc = Jsoup.connect(obj.getBuyUrl()).get();
					Element elem = doc.select("div.vcard").first();
					if (elem == null){
						//System.out.println("No vcard address provided");
					}
					else {
						//Only insert if there is a valid address
						String street = elem.select("div.street-address").text();
						if (street.contains(",")){
							String[] delim = street.split(",");
							street = delim[0];
						}
						String locality = elem.select("span.locality").text();
						String region = elem.select("span.region").text();
						String postalCode = elem.select("span.postal-code").text();
						String address = street + "," +  postalCode;
						//System.out.println(address);

						Location location = Geocode.getGPS(address);
						if (location != null){							
							if (location.getStreet().contains("null")){ //Sometimes OSM is not returning the house number
								location.setStreet(street);
							}
							//System.out.println(street);
							location.setName(obj.getManufacturerName());
							Deal deal = new Deal(obj);

							int did = deal.insertDeal(conn);
							if (did > 0){
								DealByLocation dealByLocation = new DealByLocation();
								dealByLocation.setDid(did);
								int lid = location.insertLocation(conn);
								if (lid > 0){
									dealByLocation.setLid(lid);
									dealByLocation.insertDealByLocation(conn);
								}
							}
							++numDeals;
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
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
	return numDeals;
	}
}

