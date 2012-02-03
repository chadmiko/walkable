

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

	static final String consumerKey = "j0sM9V8eFDcYn0PmBTdqTA";
	static final String consumerSecret = "ihXDy_ikwu8DFZEjk4PiK754Y7k"; 
	static final String token = "rw2ExtIjLfYk-BAp2mF-wUwh3-4IUGKi";
	static final String tokenSecret = "5yNUcFAF53iGqkD7ROwHskqm2ys";
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double lat = 41.9485056;
		double lon = -87.6616156;
		Yelp yelp = new Yelp(consumerKey, consumerSecret, token ,tokenSecret);
		String response;
//		response = yelp.search("mexican", lat, lon);
		response = yelp.getBusiness("uncommon-ground-chicago");
		System.out.println(response);

	}

}
