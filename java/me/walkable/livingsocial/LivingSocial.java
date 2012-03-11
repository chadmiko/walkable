package me.walkable.livingsocial;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import me.walkable.db.DatabaseUtil;
import me.walkable.db.Deal;
import me.walkable.db.DealByLocation;
import me.walkable.db.DealItems;
import me.walkable.db.Location;
import me.walkable.util.XMLReader;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * @author Christopher Butera
 * 
 **/

public class LivingSocial {

	public final static String URL_CHICAGO = "http://www.livingsocial.com/cities/6.atom";

	public static int parse(String cityUrl){
		Connection conn = null;
		int numDeals = 0;
		try {
			conn = DatabaseUtil.getConnection();
			URL u = new URL(cityUrl);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(u.openStream());
			doc.getDocumentElement().normalize();
			Deal deal = new Deal();
			NodeList nList = doc.getElementsByTagName("entry");
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					XMLReader reader = new XMLReader(eElement);
					//Parse LS Date format 
					//     <published>2012-03-06T05:00:00-05:00</published>
					String startAt = reader.getTagValue("published");
					String endAt = reader.getTagValue("offer_ends_at");
					int t = startAt.indexOf('T');
					int z = startAt.lastIndexOf('-');  // This only works probably for offsets that are negative
					String startTime = startAt.substring(0, t) + " " + startAt.substring(t+1,  z);
					String endTime = endAt.substring(0, t) + " " + endAt.substring(t+1,  z);
					int offset = Integer.valueOf(startAt.substring(z+1, z+3));
					offset *= -3600;  //Get offset in seconds

					//Parse currency into double
					NumberFormat cf = NumberFormat.getCurrencyInstance();
					Number numberPrice = null, numberValue = null;  
					try  
					{  
						numberPrice = cf.parse(reader.getTagValue("price")); 
						numberValue = cf.parse(reader.getTagValue("value"));
					}  
					catch (ParseException e)  
					{  
						System.out.print(e);  
					}  

					deal.setOffset(offset);
					deal.setStart_date(Timestamp.valueOf(startTime));
					deal.setEnd_date(Timestamp.valueOf(endTime));
					deal.setLink_url(reader.getAttributeValue("link", "href"));
					deal.setTitle(reader.getTagValue("long_title"));
					deal.setPrice(Double.valueOf(numberPrice.doubleValue()));
					deal.setValue(Double.valueOf(numberValue.doubleValue()));
					deal.setVendor(Deal.VENDOR_LIVING_SOCIAL);

					ArrayList<DealItems.Options> opts = new ArrayList<DealItems.Options>();
					DealItems.Options dealOpts = deal.getItems().new Options();
					dealOpts.setTitle(reader.getTagValue("description"));
					dealOpts.setBuyUrl(reader.getAttributeValue("link", "href"));
					opts.add(dealOpts);
					deal.setItems(opts);

					Location loc = new Location();
					NodeList nListLoc = eElement.getElementsByTagName("ls:location");
					for (int tempLoc = 0; tempLoc < nListLoc.getLength(); tempLoc++) {

						Node nNodeLoc = nListLoc.item(tempLoc);
						if (nNodeLoc != null){
							
							if (nNodeLoc.getNodeType() == Node.ELEMENT_NODE) {
								Element eElementLoc = (Element) nNodeLoc;
								XMLReader readerLoc = new XMLReader(eElementLoc);
								loc.setName(reader.getTagValue("merchant_name"));
								loc.setStreet(readerLoc.getTagValue("ls:address1"));
								loc.setStreet2(readerLoc.getTagValue("ls:address2"));
								loc.setZip(readerLoc.getTagValue("ls:zip"));
								loc.setLat(Double.valueOf(readerLoc.getTagValue("ls:latitude")));
								loc.setLng(Double.valueOf(readerLoc.getTagValue("ls:longitude")));							
							}
							//					}
							if (loc != null){
														System.out.println(loc);
								int did = deal.insertDeal(conn);
								int lid = loc.insertLocation(conn);
								DealByLocation dealByLocation = new DealByLocation();
								if (lid > 0){
									dealByLocation.setDid(did);
									dealByLocation.setLid(lid);
									dealByLocation.insertDealByLocation(conn);
								}
								++numDeals;
							}
						}			
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

		return numDeals;
	}

}