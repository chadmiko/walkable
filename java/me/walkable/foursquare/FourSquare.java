package me.walkable.foursquare;

import java.util.Scanner;

import org.scribe.builder.api.Foursquare2Api;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 * 
 */

/**
 * @author Christopher Butera
 * 
 */

public class FourSquare {

	OAuthService service;
	Token accessToken;
	private static final String SEARCH_URL = "https://api.foursquare.com/v2/specials/search";
	private static final Token EMPTY_TOKEN = null;
	private static String token = "EI2VRYZAIKGRSP1C3N21ITXEG4CGUIR1AUXDCZLEMIBYUMH0";
//	private static String tokenSecret = "";
	
	private String clientID;
	private String clientSecret;
	
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
	public FourSquare(String consumerKey, String consumerSecret) {
		this.service = new ServiceBuilder().provider(Foursquare2Api.class).apiKey(consumerKey).apiSecret(consumerSecret).callback("http://walkable.me/").build();
//		this.accessToken = new Token(token, consumerSecret);
		this.clientID = consumerKey;
		this.clientSecret = consumerSecret;
	}

	public void getToken(){
	    Scanner in = new Scanner(System.in);

	    System.out.println("=== Foursquare2's OAuth Workflow ===");
	    System.out.println();

	    // Obtain the Authorization URL
	    System.out.println("Fetching the Authorization URL...");
	    String authorizationUrl = service.getAuthorizationUrl(EMPTY_TOKEN);
	    System.out.println("Got the Authorization URL!");
	    System.out.println("Now go and authorize Scribe here:");
	    System.out.println(authorizationUrl);
	    System.out.println("And paste the authorization code here");
	    System.out.print(">>");
	    Verifier verifier = new Verifier(in.nextLine());
	    System.out.println();
	    
	    // Trade the Request Token and Verfier for the Access Token
	    System.out.println("Trading the Request Token for an Access Token...");
	    Token accessToken = service.getAccessToken(EMPTY_TOKEN, verifier);
	    System.out.println("Got the Access Token!");
	    System.out.println("(if your curious it looks like this: " + accessToken + " )");
	    System.out.println();

	    // Now let's go and ask for a protected resource!
	    System.out.println("Now we're going to access a protected resource...");
	    OAuthRequest request = new OAuthRequest(Verb.GET, SEARCH_URL + accessToken.getToken());
	    service.signRequest(accessToken, request);
	    Response response = request.send();
	    System.out.println("Got it! Lets see what we found...");
	    System.out.println();
	    System.out.println(response.getCode());
	    System.out.println(response.getBody());

	    System.out.println();
	    System.out.println("Thats it man! Go and build something awesome with Scribe! :)");
	  }	
	
	/**
	 * Search with term and location.
	 *
	 * @param term Search term
	 * @param latitude Latitude
	 * @param longitude Longitude
	 * @return JSON string response
	 */
//	public String search(String term, double latitude, double longitude) {
//		OAuthRequest request = new OAuthRequest(Verb.GET, searchURL);
//		request.addQuerystringParameter("term", term);
//		request.addQuerystringParameter("ll", latitude + "," + longitude);
//		this.service.signRequest(this.accessToken, request);
//		Response response = request.send();
//		return response.getBody();
//	}
//
//	public String getBusiness(String id){
//		OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/business/" + id + "?");
//		this.service.signRequest(this.accessToken, request);
//		Response response = request.send();
//		return response.getBody();
//	}
	
	public String getDeals(String lat, String lng){
		OAuthRequest request = new OAuthRequest(Verb.GET, SEARCH_URL);
		String ll = lat + "," + lng;
		request.addQuerystringParameter("client_id", clientID);
		request.addQuerystringParameter("client_secret", clientSecret);
		request.addQuerystringParameter("ll", ll);
		Response response = request.send();
		return response.getBody();		
	}
	
}