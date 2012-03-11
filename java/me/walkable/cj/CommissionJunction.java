package me.walkable.cj;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;


/**
 * @author Christopher Butera
 * 

/**
 * Example for accessing the LivingSocial API.
 * http://help.cj.com/en/web_services/web_services.htm#product_catalog_search_service_rest.htm
 */
public class CommissionJunction {

	static final String cjDeveloperKey = "00a7d09d71018a5005a88dae5175e10d3f4b009e0f1211722779aa31bd866d95e6bb9faadafe3113fcd48032ef8987f969889541347a091bee4d3fb23a0014f60f/5867114962a2561d64f99e06d3ae46a83cfe8a452e237a9b49005b4b97f14ab1721fc44857795f1339cd78c3335580eadf665c56102bbc0badd8a7accd68e321";

	private final String baseURL = "https://product-search.api.cj.com/v2/product-search";
	
	//OAuthService service;
	//Token accessToken;
//	private String clientID;
	//private final String format = ".json";

	//This value is your Web site ID (PID), which enables the system to generate the appropriate link code in the response. The PID must match the Web site PID which you used to register for the developer key.
	private final String websiteID = "5640229";
	//joined: This special value (joined) restricts the search to advertisers with which you have a relationship.
	//private final String advertiserID = "joined";
	//Specifies the number of records to return in the request. Leaving this parameter blank assigns a default value of 50.
	//Note: 1000 results is the system limit for results per request. If you request a value greater than 1000, the system only returns 1000.
	private final String recordsPerPage = "1000";
	
	public final static String VENDOR_GILT_CITY = "3361226";
	public final static String VENDOR_ETHICALDEAL = "3351540";
	public final static String VENDOR_KGB_DEALS = "3131014";
	

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
	public CommissionJunction() {
//		this.service = new ServiceBuilder().provider(CommissionJunctionApi.class).apiKey(clientID).apiSecret("").build();
//		this.clientID = clientID;
	}

	//Need to revisit once we get affiliate ID.
	
	/**
	 * Search with term.
	 *
	 * @param term Search term
	 */
	public String search(String advertiserID) {
		OAuthRequest request = new OAuthRequest(Verb.GET, baseURL);
		request.addHeader("authorization", cjDeveloperKey );
		request.addQuerystringParameter("website-id", websiteID);
		request.addQuerystringParameter("advertiser-ids", advertiserID);
		request.addQuerystringParameter("records-per-page", recordsPerPage);		
		Response response = request.send();
		return response.getBody();
	}

	
}