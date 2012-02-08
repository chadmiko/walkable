import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 */

/**
 * @author Christopher Butera
 *
 */
public class YelpParse {

	public static YelpDealObject parse(String json){
		Gson gson = new GsonBuilder().create();
		YelpDealObject object = gson.fromJson(json, YelpDealObject.class);
		return object;
	}
	
	

}
