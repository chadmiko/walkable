/**
 * 
 */
package me.walkable.cj.giltcity;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import me.walkable.cj.CommissionJunctionObject;

/**
 * @author Christopher Butera
 *
 */
public class GiltCityParse {

	public static void parse(ArrayList<CommissionJunctionObject> cjList){
		String parseUrl = "www.giltcity.com";
		for (CommissionJunctionObject obj : cjList){
			try {
				//Parse out the crap at the beginning of the url
				String url = obj.getBuyUrl();
				String[] urlParts = url.split(parseUrl);
				if (urlParts != null && urlParts.length > 1){
					URI uri = new URI(parseUrl + urlParts[1]);
					obj.setBuyUrl("http://" + uri.getPath());
					System.out.println(obj.getBuyUrl());
					Document doc = Jsoup.connect(obj.getBuyUrl()).get();
					Element elem = doc.select("div.vcard").first();
					if (elem == null){
						System.out.println("No vcard address provided");
					}
					else {
						String street = elem.select("div.street-address").text();
						String locality = elem.select("div.locality").text();
						String region = elem.select("div.region").text();
						String postalCode = elem.select("div.postal-code").text();
						System.out.println(street);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}

	}

}
