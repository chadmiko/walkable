package me.walkable;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import me.walkable.cj.CommissionJunction;
import me.walkable.cj.CommissionJunctionObject;
import me.walkable.cj.CommissionJunctionParse;
import me.walkable.cj.giltcity.GiltCityParse;
import me.walkable.foursquare.*;
import me.walkable.groupon.*;
import me.walkable.util.CategoryTree;
import me.walkable.util.UtcTime;
import me.walkable.util.WriteArchiveFile;
import me.walkable.yelp.*;

/**
 * 
 */

/**
 * @author Christopher Butera
 * Fri Feb 3, 2012
 *
 * This is a working example of a yelp API call both search or business ID
 *
 */
public class ApiCall {

	static final String fourClientID = "GXJ3J33W0NXGVAGGLHBORLNQHL14HCIZNTWKC2XU2RK510VL";
	static final String fourSecret = "1VKEVJQZHMF22ZZTM0WIX05U2KH32T02WJHSINTDHKKOC2CN";
	


	public static void getFourSquare(){
		String lat = "41.9485056";
		String lng = "-87.6616156";

		//FourSquare Example
		FourSquare fourSquare = new FourSquare(fourClientID, fourSecret);
		//		fourSquare.getToken();
		String fourResponse = fourSquare.getDeals(lat, lng);
		//		System.out.println(fourResponse);

		FileWriter writer; // I orginally used BufferedWriter - but it couldn't write everything to the file - kept creating incomplete files.
		try { 
			String fileName = "./fourSquare.json";
			writer = new FileWriter ( fileName ) ;
			writer.write(fourResponse);
			writer.close();
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}

	}

	public static void getGiltCity(){
		CommissionJunction cj = new CommissionJunction();
		String cjXML = cj.search(CommissionJunction.VENDOR_GILT_CITY);
		WriteArchiveFile.writeFile("GiltCity", cjXML, ".xml"); //Write to file
		ArrayList<CommissionJunctionObject> cjList = CommissionJunctionParse.parse(cjXML);
		int numGilt = GiltCityParse.parse(cjList);
		System.out.println("Done inserting/updating " + numGilt + " deals");
	}
	
	public static void main(String[] args) {
		//		getFourSquare();
		getGiltCity();

	}

}
