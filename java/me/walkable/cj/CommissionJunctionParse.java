package me.walkable.cj;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

//Example Object
//<ad-id>10931325</ad-id>
//<advertiser-id>3361226</advertiser-id>
//<advertiser-name>Gilt City</advertiser-name>
//<advertiser-category>beauty</advertiser-category>
//<buy-url>http://www.dpbolvw.net/click-5640229-10931325?url=http%3A%2F%2Fad.doubleclick.net%2Fclk%3B248316538%3B74030263%3Be%3Fhttp%3A%2F%2Fwww.giltcity.com%2Fnewyork%2Fdangenenyc%3Faffid%3D350%26oaff%3Dbdaffiliate%26pkey%3Dcj&amp;cjsku=18135</buy-url>
//<catalog-id>cjo:1602</catalog-id>
//<currency>USD</currency>
//<description>Up to 66% Off Teen Acne Program or Anti-Aging Treatments</description>
//<image-url>http://cdn1.gilt.com/images/share/uploads/0000/0001/4091/140918181/orig.jpg?53_1330989226000</image-url>
//<in-stock/>
//<isbn/>
//<manufacturer-name>Dangene ? The Institute of Skinovation</manufacturer-name>
//<manufacturer-sku/>
//<name>Dangene ? The Institute of Skinovation - Anti-Aging Treatment</name>
//<price>500.0</price>
//<retail-price>1500.0</retail-price>
//<sale-price>0.0</sale-price>
//<sku>18135</sku>
//<upc/>


/**
 * @author Christopher Butera
 *
 */
public class CommissionJunctionParse {

	public static ArrayList<CommissionJunctionObject> parse(String xmlString){

		ArrayList<CommissionJunctionObject> cjList = new ArrayList<CommissionJunctionObject>();
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new StringReader(xmlString)));
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("product");
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					CommissionJunctionObject obj = new CommissionJunctionObject();
					//					obj.adId = getTagValue("ad-id", eElement);
					obj.advertiserName = getTagValue("advertiser-name", eElement);
					obj.advertiserCategory = getTagValue("advertiser-category", eElement);
					obj.setBuyUrl(getTagValue("buy-url", eElement));
					obj.description = getTagValue("description", eElement);
					obj.manufacturerName = getTagValue("manufacturer-name", eElement);
					obj.name = getTagValue("name", eElement);
					obj.price = getTagValue("price", eElement);
					obj.retailPrice = getTagValue("retail-price", eElement);
					obj.salePrice = getTagValue("sale-price", eElement);					
					cjList.add(obj);
				}
			}

		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cjList;
	}

	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}


	//NEED to at least return the number of records


	//	public static void InsertGrouponData(GrouponObject obj){
	//		Connection conn = null;
	//		GrouponData[] deals = obj.deals;
	//
	//		try {
	//			conn = DatabaseUtil.getConnection();
	//
	//			for (GrouponData dealData : deals){
	//				ArrayList<Location> locations = new ArrayList<Location>();
	//				Deal deal = new Deal();
	//				DealItems items = new DealItems();
	//				ArrayList<DealItems.Options> opts = new ArrayList<DealItems.Options>();
	//				int optionNum=1;
	//				for (GrouponData.GrouponOptions opt : dealData.options){
	//					//Lets build the object here and insert later
	//					if (optionNum == 1){
	//						//Parse Groupon Date format 
	//						//      "startAt": "2012-02-12T06:00:48Z" 
	//						int t = dealData.startAt.indexOf('T');
	//						int z = dealData.startAt.indexOf('Z');
	//						String startTime = dealData.startAt.substring(0, t) + " " + dealData.startAt.substring(t+1,  z);
	//						String endTime = dealData.endAt.substring(0, t) + " " + dealData.endAt.substring(t+1,  z);
	//
	//						deal.setVendor(Deal.VENDOR_GROUPON);
	//						deal.setTitle(dealData.announcementTitle);
	//						deal.setLink_url(dealData.dealUrl);
	//						deal.setStart_date(Timestamp.valueOf(startTime));
	//						deal.setEnd_date(Timestamp.valueOf(endTime));
	//						deal.setOffset(dealData.division.timezoneOffsetInSeconds);
	//						
	//						//					if (opt.isLimitedQuantity)
	//						//						deal.setRemaining_quantity(opt.remainingQuantity);
	//						//					deal.setPrice(new Double(opt.price.amount).doubleValue() / 100.00 ); //Shift decimal
	//						//					deal.setValue(new Double(opt.value.amount).doubleValue() / 100.00 ); //Shift decimal
	//						//					deal.setDiscount( 1 - (deal.getPrice() / deal.getValue()));
	//						//insert later
	//						
	//						//Assumption - Every option is offered at every location
	//						for (GrouponData.GrouponLocation gLoc : opt.redemptionLocations){
	//							//Pass mid into location
	//							Location location = new Location();
	//							location.setStreet(gLoc.streetAddress1);
	//							location.setStreet2(gLoc.streetAddress2);
	//							location.setNeighborhood(gLoc.neighborhood);
	//							location.setZip(gLoc.postalCode);
	//							location.setLat(gLoc.lat);
	//							location.setLng(gLoc.lng);
	//							location.setName(dealData.merchant.name);
	//							location.setUrl(dealData.merchant.websiteUrl);
	//							locations.add(location);
	//						}
	//
	//					}
	//					
	//					DealItems.Options dealOpts = items.new Options();
	//					dealOpts.setTitle(opt.title);
	//					dealOpts.setBuyUrl(opt.buyUrl);
	//					opts.add(dealOpts);
	//
	//					++optionNum;
	//				}
	////				DealItems.Options[] myOpts = opts.toArray(new DealItems.Options[opts.size()]);
	////				items.setOptions(myOpts);
	//				deal.setItems(opts);
	//
	//				//Insert into DB
	//				int did = deal.insertDeal(conn);
	//				for (Location loc : locations){
	//					int lid = loc.insertLocation(conn);
	//
	//					//Insert row into dealByLocation
	//					DealByLocation dealByLocation = new DealByLocation();
	//					if (lid > 0){
	//						dealByLocation.setDid(did);
	//						dealByLocation.setLid(lid);
	//						dealByLocation.insertDealByLocation(conn);
	//					}
	//				}//End Insert
	//
	//			}//End Deal Loop
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//		} finally {
	//			if (conn != null) {
	//				try {
	//					conn.close();
	//				} catch (SQLException e) { /*ignored*/ }
	//			}
	//
	//			//			String insert2 = "INSERT INTO deals "
	//			//					+ "VALUES()";
	//			//			String insert3 = "INSERT INTO groupon "
	//			//					+ "VALUES('did', 'small_image', 'med_image', 'grid_image', 'pitch', 'highlights', 'placement_priority', 'announcement', 'location_note', 'ship_req', 'is_now_deal', 'fine_print')";
	//
	//		}
	//
	//
	//	}

}
