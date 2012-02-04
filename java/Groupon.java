import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;


/**
 * @author Christopher Butera
 * 

/**
 * Example for accessing the Groupon API.
 */
public class Groupon {

	private final String baseURL = "http://api.groupon.com/v2/deals";
	
	//OAuthService service;
	//Token accessToken;
	private String clientID;
	private final String format = ".json";

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
	public Groupon(String clientID) {
//		this.service = new ServiceBuilder().provider(GrouponApi.class).apiKey(clientID).apiSecret("").build();
		this.clientID = clientID;
	}

	//Need to revisit once we get affiliate ID.
	
	/**
	 * Search with term.
	 *
	 * @param term Search term
	 */
	public String search(String city) {
		OAuthRequest request = new OAuthRequest(Verb.GET, baseURL);
		request.addQuerystringParameter("client_id", clientID);
		request.addQuerystringParameter("division_id", city);
		Response response = request.send();
		return response.getBody();
	}

	
}