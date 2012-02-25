package me.walkable.yelp;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 */

/**
 * @author Christopher Butera
 * 

/**
 * Example for accessing the Yelp API.
 */
public class Yelp {
	
	public final static String CHICAGO = "Chicago";
	
	private OAuthService service;
	private Token accessToken;
	private final String searchURL = "http://api.yelp.com/v2/search";

	/**
	 * Setup the Yelp API OAuth credentials.
	 *
	 * OAuth credentials are available from the developer site, under Manage API access (version 2 API).
	 *
	 * @param consumerKey Consumer key
	 * @param consumerSecret Consumer secret
	 * @param token Token
	 * @param tokenSecret Token secret
	 */
	public Yelp(String consumerKey, String consumerSecret, String token, String tokenSecret) {
		this.service = new ServiceBuilder().provider(YelpApi.class).apiKey(consumerKey).apiSecret(consumerSecret).build();
		this.accessToken = new Token(token, tokenSecret);
	}

	/**
	 * Search with term and location.
	 *
	 * @param term Search term
	 * @param latitude Latitude
	 * @param longitude Longitude
	 * @return JSON string response
	 */
	public String search(String term, double latitude, double longitude) {
		OAuthRequest request = new OAuthRequest(Verb.GET, searchURL);
		request.addQuerystringParameter("term", term);
		request.addQuerystringParameter("ll", latitude + "," + longitude);
		this.service.signRequest(this.accessToken, request);
		Response response = request.send();
		return response.getBody();
	}

	public String search(String city, String offset) {
		OAuthRequest request = new OAuthRequest(Verb.GET, searchURL);
		request.addQuerystringParameter("location", city);
		request.addQuerystringParameter("offset", offset);
		this.service.signRequest(this.accessToken, request);
		Response response = request.send();
		return response.getBody();
	}


	public String getBusiness(String id){
		OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/business/" + id + "?");
		this.service.signRequest(this.accessToken, request);
		Response response = request.send();
		return response.getBody();
	}
	
	public int getNumberofDeals(String city){
		OAuthRequest request = new OAuthRequest(Verb.GET, searchURL);
		request.addQuerystringParameter("location", city);
		request.addQuerystringParameter("deals_filter","true");
		this.service.signRequest(this.accessToken, request);
		Response response = request.send();
		Gson gson = new GsonBuilder().create();
		YelpDealObject object = gson.fromJson(response.getBody(), YelpDealObject.class);
//		return response.getBody();
		return object.getTotalNumberOfDeals();
	}
	
	//http://www.yelp.com/developers/documentation/v2/search_api
//	public String getDeals(String city, String offset){
//		OAuthRequest request = new OAuthRequest(Verb.GET, searchURL);
//		request.addQuerystringParameter("location", city);
//		request.addQuerystringParameter("deals_filter","true");
//		request.addQuerystringParameter("limit", "20"); //This is the max
//		request.addQuerystringParameter("sort", "1"); //Sort mode: 0=Best matched (default), 1=Distance, 2=Highest Rated.
//		request.addQuerystringParameter("offset", offset);
//		this.service.signRequest(this.accessToken, request);
//		Response response = request.send();
//		return response.getBody();		
//	}

	public String getDeals(String city, String category){
		OAuthRequest request = new OAuthRequest(Verb.GET, searchURL);
		request.addQuerystringParameter("location", city);
		request.addQuerystringParameter("deals_filter","true");
		request.addQuerystringParameter("limit", "20"); //This is the max
		request.addQuerystringParameter("sort", "1"); //Sort mode: 0=Best matched (default), 1=Distance, 2=Highest Rated.
		request.addQuerystringParameter("category_filter", category);
		this.service.signRequest(this.accessToken, request);
		Response response = request.send();
		return response.getBody();		
	}
	
	public YelpDealObject getDeals(String city, String offset, String category){
		OAuthRequest request = new OAuthRequest(Verb.GET, searchURL);
		request.addQuerystringParameter("location", city);
		request.addQuerystringParameter("deals_filter","true");
		request.addQuerystringParameter("limit", "20"); //This is the max
		request.addQuerystringParameter("sort", "1"); //Sort mode: 0=Best matched (default), 1=Distance, 2=Highest Rated.
		request.addQuerystringParameter("category_filter", category);
		request.addQuerystringParameter("offset", offset);
		this.service.signRequest(this.accessToken, request);
		Response response = request.send();
//		System.out.println(response.getBody());
		return YelpParse.parse(response.getBody());		
	}

	
	
}