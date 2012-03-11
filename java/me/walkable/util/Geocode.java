/**
 * 
 */
package me.walkable.util;

import java.net.URI;
import java.net.URISyntaxException;

import me.walkable.db.Location;
import me.walkable.yelp.YelpDealObject;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * @author Christopher Butera
 *
 */
public class Geocode {

	private final static String OSM_BASE_URL = "http://nominatim.openstreetmap.org/search/";

	/**
	 * http://wiki.openstreetmap.org/wiki/Nominatim
	 * @param address - A single line concatenated Street, City, State, Zip
	 * @return json 
	 */
	public static String openStreetMap(String address){
		OAuthRequest request = null;
		request = new OAuthRequest(Verb.GET, OSM_BASE_URL);		
		request.addQuerystringParameter("q", address);
		request.addQuerystringParameter("format", "json");
		request.addQuerystringParameter("addressdetails", "1");
		Response response = request.send();
		return response.getBody();
	}

	public class OpenStreetMapObject{
		public class osmAddress {
			public String country;
			public String country_code;
			public String county;
			public String hamlet;
			public String house_number;
			public String postcode;
			public String road;
			public String state;
		}
		public osmAddress address;
		public float[] boundingbox;
		//		public String class;  // NOT ALLOWED
		public String display_name;
		public float lat;
		public String licence;
		public float lon;
		public long place_id;
		public String type;

	}

	public static Location getGPS(String address){
		String osmJson = openStreetMap(address);
		OpenStreetMapObject object = null;
		Gson gson = new GsonBuilder().create();
		JsonParser parser = new JsonParser();
		JsonArray Jarray = null;
		try{
			Jarray = parser.parse(osmJson).getAsJsonArray();
		} catch (com.google.gson.JsonSyntaxException e){
			System.out.println(address);
			e.printStackTrace();
		}
		for(JsonElement obj : Jarray ){
			object = gson.fromJson(obj, OpenStreetMapObject.class);
		}

		if (object != null){
			return new Location(object);
		}
		return null;
	}

}
