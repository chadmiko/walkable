import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 */

/**
 * @author Christopher Butera
 *
 */
public class GrouponParse {

	public static GrouponObject parse(String json){
		Gson gson = new GsonBuilder().create();
		GrouponObject object = gson.fromJson(json, GrouponObject.class);
		return object;
	}

}
