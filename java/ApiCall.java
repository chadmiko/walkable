
import java.io.FileWriter;
import java.io.IOException;



/**
 * 
 */

/**
 * @author Christopher Butera
 * Fri Feb 3, 2012
 *
 * This is a working example of a yelp API call both search or business ID
 *
 */
public class ApiCall {

	static final String yConsumerKey = "j0sM9V8eFDcYn0PmBTdqTA";
	static final String yConsumerSecret = "ihXDy_ikwu8DFZEjk4PiK754Y7k"; 
	static final String yToken = "rw2ExtIjLfYk-BAp2mF-wUwh3-4IUGKi";
	static final String yTokenSecret = "5yNUcFAF53iGqkD7ROwHskqm2ys";
	
	static final String gClientID = "8ad4ef59d1e755157121d9ac5f3ff16aeb89d93d";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		double lat = 41.9485056;
//		double lon = -87.6616156;

//		//Yelp Example
//		Yelp yelp = new Yelp(yConsumerKey, yConsumerSecret, yToken ,yTokenSecret);
//		String yResponse;
//		yResponse = yelp.search("mexican", lat, lon);
//		yResponse = yelp.getBusiness("uncommon-ground-chicago");
//		yResponse = yelp.getDeals("Chicago");
//		YelpDealObject yObj = YelpParse.parse(yResponse);
//		System.out.println(yObj.businesses[0].snippet_text);

		//Groupon Example
		Groupon groupon = new Groupon(gClientID);
		String gResponse = groupon.search("chicago");
//		System.out.println(gResponse);
		
		FileWriter writer; // I orginally used BufferedWriter - but it couldn't write everything to the file - kept creating incomplete files.
		try { 
			String fileName = "./groupon.json";
			writer = new FileWriter ( fileName ) ;
			writer.write(gResponse);
			writer.close();
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}

		GrouponObject obj = GrouponParse.parse(gResponse);
		GrouponParse.InsertGrouponData(obj);
		System.out.println(obj.deals.length + " deals processed");
		
		//DB Test
//		try{
//			Connection conn = DatabaseUtil.getConnection();
//			Statement stmt = conn.createStatement();
//			String select = "Show tables";
//			ResultSet rs = stmt.executeQuery(select);
//			while (rs.next()){
//				System.out.println(rs.getString(1));
//			}
//					
//			
//		} catch (SQLException e){
//			System.out.println("Failed to make DB Connection");
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			System.out.println("Failed to make DB Connection");
//			e.printStackTrace();
//		}
	}

}
