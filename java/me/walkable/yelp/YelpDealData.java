package me.walkable.yelp;

/**
 * @author Christopher Butera
 *
 */
public class YelpDealData {

	public class YelpDeal{
		public class YelpOptions{
			public int original_price;
			public String title;
			public int price;
			public String purchase_url;
			public String formatted_original_price;
			public String formatted_price;
			public boolean is_quantity_limited;
			YelpOptions(){};
		}
		//End YelpOptions object
		
		public String what_you_get;
		public int time_start;
		public String title;
		public String url;
		public YelpOptions[] options;
		
		public String image_url;
		public String special_terms;
		public String id;
		public String currency_code;
		
		YelpDeal(){};
	}
	//End YelpDeal object
		
	public class YelpLocation{
		public class YelpCoordinate{
			public double latitude;
			public double longitude;
			YelpCoordinate(){};
		}
		
		public String city;
		public String[] display_address;
		public int geo_accuracy;
		public String country_code;
		public String[] address;
		public YelpCoordinate coordinate;

		public String state_code;
		
		YelpLocation(){};
	}
	
	public double rating;
	public String mobile_url;
	public String rating_img_url;
	public int review_count;
	public String name;
	public String rating_img_url_small;
	public String url;
	public YelpDeal[] deals;
	
	public String phone;
	public String snippet_text;
	public String snippet_image_url;
	public String display_phone;
	public String rating_img_url_large;
	public String id;
	public String[][] categories;
	public YelpLocation location;
	
	YelpDealData(){};
}
