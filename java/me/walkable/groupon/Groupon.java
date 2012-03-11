package me.walkable.groupon;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;


/**
 * @author Christopher Butera
 * 
 */

public class Groupon {

	private final String baseURL = "http://api.groupon.com/v2/deals";	
	//THIS FIELD SHOULD BE PROTECTED!!!
	private static final String clientID = "8ad4ef59d1e755157121d9ac5f3ff16aeb89d93d";

	public Groupon() {
//		this.service = new ServiceBuilder().provider(GrouponApi.class).apiKey(clientID).apiSecret("").build();
//		this.clientID = clientID;
	}

	//Need to revisit once we get affiliate ID.
	
	public String search(String city) {
		OAuthRequest request = new OAuthRequest(Verb.GET, baseURL);
		request.addQuerystringParameter("client_id", clientID);
		request.addQuerystringParameter("division_id", city);
		Response response = request.send();
		return response.getBody();
	}
	
}