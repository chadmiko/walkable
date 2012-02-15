package me.walkable;

import java.io.FileWriter;
import java.io.IOException;

import me.walkable.foursquare.*;
import me.walkable.groupon.*;
import me.walkable.util.CategoryTree;
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

	static final String yConsumerKey = "j0sM9V8eFDcYn0PmBTdqTA";
	static final String yConsumerSecret = "ihXDy_ikwu8DFZEjk4PiK754Y7k"; 
	static final String yToken = "rw2ExtIjLfYk-BAp2mF-wUwh3-4IUGKi";
	static final String yTokenSecret = "5yNUcFAF53iGqkD7ROwHskqm2ys";

	static final String gClientID = "8ad4ef59d1e755157121d9ac5f3ff16aeb89d93d";

	//FourSquare
	//	Client ID GXJ3J33W0NXGVAGGLHBORLNQHL14HCIZNTWKC2XU2RK510VL
	//	Client Secret 1VKEVJQZHMF22ZZTM0WIX05U2KH32T02WJHSINTDHKKOC2CN

	static final String fourClientID = "GXJ3J33W0NXGVAGGLHBORLNQHL14HCIZNTWKC2XU2RK510VL";
	static final String fourSecret = "1VKEVJQZHMF22ZZTM0WIX05U2KH32T02WJHSINTDHKKOC2CN";

	public static void getYelp() throws Exception{
		//		double lat = 41.9485056;
		//		double lon = -87.6616156;

		//		//Yelp Example
//		Yelp yelp = new Yelp(yConsumerKey, yConsumerSecret, yToken ,yTokenSecret); 
//		String yResponse;
		//		yResponse = yelp.search("mexican", lat, lon);
		//		yResponse = yelp.getBusiness("uncommon-ground-chicago");
		//		int processed = 0;
		//		int total = yelp.getNumberofDeals("Chicago"); //placeholder
		//		while (processed < total){
		//			yResponse = yelp.getDeals("Chicago", String.valueOf(processed));
		//			YelpDealObject yObj = YelpParse.parse(yResponse);
		//			processed += yObj.getNumberOfDeals();
		////			total = yObj.getTotalNumberOfDeals();
		//			System.out.println(processed + "/" + total + " Yelp deals processed " );
		//		}
		//		System.out.println(yResponse);


		//		yResponse = yelp.search("Chicago", "25");

		//		FileWriter writer; // I orginally used BufferedWriter - but it couldn't write everything to the file - kept creating incomplete files.
		//		try { 
		//			String fileName = "./yelp.json";
		//			writer = new FileWriter ( fileName ) ;
		//			writer.write(yResponse);
		//			writer.close();
		//		} catch (IOException x) {
		//			System.err.format("IOException: %s%n", x);
		//		}

		YelpCategory ycats = new YelpCategory();
		for (CategoryTree subTree :ycats.getCategories()){
			yelpCategoryIterate(subTree);
		}
	}

	//	public static void getYelpDealsByCategory(){

	public static void yelpCategoryIterate(CategoryTree tree) throws Exception{

		Yelp yelp = new Yelp(yConsumerKey, yConsumerSecret, yToken ,yTokenSecret);
		if (tree.isLeaf()){
			YelpDealObject yObj = yelp.getDeals(Yelp.CHICAGO, "0", tree.getCategory());
			System.out.println("Found " + yObj.getTotalNumberOfDeals() + " in leaf category: " + tree.getCategory());
			YelpParse.InsertYelpData(yObj);
			if (yObj.getNumberOfDeals() != yObj.getTotalNumberOfDeals()){
				if (yObj.getTotalNumberOfDeals() <= 40){
					yObj = yelp.getDeals(Yelp.CHICAGO, "20", tree.getCategory());
					yObj = YelpParse.parse(tree.getUnparsedDeals());	
					YelpParse.InsertYelpData(yObj);
				}
				else {
					throw new Exception("More than 40 deals for child category.  Found " + yObj.getNumberOfDeals() + " " + tree.getCategory() + " in Yelp");
				}
			}
		}
		else {

			YelpDealObject yObj = yelp.getDeals(Yelp.CHICAGO, "0", tree.getCategory());
			System.out.println("Found " + yObj.getTotalNumberOfDeals() + " in branch category: " + tree.getCategory());
			if (yObj.getNumberOfDeals() == yObj.getTotalNumberOfDeals() || yObj.getTotalNumberOfDeals() < 40){
				YelpParse.InsertYelpData(yObj);
			}
			else if (yObj.getNumberOfDeals() != yObj.getTotalNumberOfDeals() && yObj.getTotalNumberOfDeals() < 40) {
				YelpParse.InsertYelpData(yObj);

				yObj = yelp.getDeals(Yelp.CHICAGO, "20", tree.getCategory());
				YelpParse.InsertYelpData(yObj);
			}
			else { //Over 40 deals
				for (CategoryTree subTree : tree.getSubCategories()){
					yelpCategoryIterate(subTree);
				}
			}
		}

	}

	public static void getGroupon(){
		//Groupon Example
		Groupon groupon = new Groupon(gClientID);
		String gResponse = groupon.search("chicago");
		//		System.out.println(gResponse);

		FileWriter gWriter; // I orginally used BufferedWriter - but it couldn't write everything to the file - kept creating incomplete files.
		try { 
			String fileName = "./groupon.json";
			gWriter = new FileWriter ( fileName ) ;
			gWriter.write(gResponse);
			gWriter.close();
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}

		GrouponObject obj = GrouponParse.parse(gResponse);
		GrouponParse.InsertGrouponData(obj);
		System.out.println(obj.deals.length + " Groupon deals processed");
	}

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

	public static void main(String[] args) {

//		try {
//			getYelp();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
				getGroupon();
		//		getFourSquare();



	}

}
