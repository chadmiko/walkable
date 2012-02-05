import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * 
 */

/**
 * @author Christopher Butera
 *
 */
public class GrouponParse {

	public static GrouponObject parse(String json){
//		GrouponData data = new GrouponData();
		Gson gson = new GsonBuilder().create();
		GrouponObject object = gson.fromJson(json, GrouponObject.class);
//		GrouponObject obj = gson.fromJson(json, GrouponObject.class);
//		JsonParser parser = new JsonParser();
//		JsonArray array = parser.parse(json).getAsJsonArray();
//		Iterator it = array.iterator();
//		while (it.hasNext()){
//			System.out.println(it.next());
//		}
//		String message = gson.fromJson(array.get(0), String.class);

		return object;
	}

}
