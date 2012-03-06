package me.walkable.cj;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;


/**
 * @author Christopher Butera
 * 

/**
 * Example for accessing the CommissionJunction API.
 * http://help.cj.com/en/web_services/web_services.htm#product_catalog_search_service_rest.htm
 */
public class CommissionJunction {

	private final String baseURL = "https://product-search.api.cj.com/v2/product-search";
	
	//OAuthService service;
	//Token accessToken;
	private String clientID;
	//private final String format = ".json";

	//This value is your Web site ID (PID), which enables the system to generate the appropriate link code in the response. The PID must match the Web site PID which you used to register for the developer key.
	private final String websiteID = "5640229";
	//joined: This special value (joined) restricts the search to advertisers with which you have a relationship.
	private final String advertiserID = "joined";
	//Specifies the number of records to return in the request. Leaving this parameter blank assigns a default value of 50.
	//Note: 1000 results is the system limit for results per request. If you request a value greater than 1000, the system only returns 1000.
	private final String recordsPerPage = "1000";
	
	

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
	public CommissionJunction(String clientID) {
//		this.service = new ServiceBuilder().provider(CommissionJunctionApi.class).apiKey(clientID).apiSecret("").build();
		this.clientID = clientID;
	}

	//Need to revisit once we get affiliate ID.
	
	/**
	 * Search with term.
	 *
	 * @param term Search term
	 */
	public String search() {
		OAuthRequest request = new OAuthRequest(Verb.GET, baseURL);
		request.addHeader("authorization", clientID);
		request.addQuerystringParameter("website-id", websiteID);
		request.addQuerystringParameter("advertiser-ids", advertiserID);
		request.addQuerystringParameter("records-per-page", recordsPerPage);		
		Response response = request.send();
		return response.getBody();
	}

	
}