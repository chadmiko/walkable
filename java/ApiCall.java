

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
		double lat = 41.9485056;
		double lon = -87.6616156;

		//Yelp Example
//		Yelp yelp = new Yelp(yConsumerKey, yConsumerSecret, yToken ,yTokenSecret);
//		String response;
//		response = yelp.search("mexican", lat, lon);
//		response = yelp.getBusiness("uncommon-ground-chicago");
//		System.out.println(response);

		//Groupon Example
		Groupon groupon = new Groupon(gClientID);
		String gResponse = groupon.search("chicago");
		System.out.println(gResponse);
		
	}

}
