package me.walkable.yelp;

/**
 * @author Christopher Butera
 *
 */
public class YelpDealData {

	protected class YelpDeal{
		protected class YelpOptions{
			protected int original_price;
			protected String title;
			protected int price;
			protected String purchase_url;
			protected String formatted_original_price;
			protected String formatted_price;
			protected int remaining_count;
			protected boolean is_quantity_limited;
			YelpOptions(){};
		}
		//End YelpOptions object
		
		protected String what_you_get;
		protected long time_start;
		protected String title;
		protected String url;
		protected YelpOptions[] options;
		
		protected String image_url;
		protected String special_terms;
		protected String id;
		protected String currency_code;
		
		YelpDeal(){};
	}
	//End YelpDeal object
		
	protected class YelpLocation{
		protected class YelpCoordinate{
			protected double latitude;
			protected double longitude;
			YelpCoordinate(){};
		}
		
		protected String city;
		protected String[] display_address;
		protected int geo_accuracy;
		protected String[] neighborhoods;
		protected String postal_code;
		protected String country_code;
		protected String[] address;
		protected YelpCoordinate coordinate;

		protected String state_code;
		
		YelpLocation(){};
	}
	
	protected double rating;
	protected String mobile_url;
	protected String rating_img_url;
	protected int review_count;
	protected String name;
	protected String rating_img_url_small;
	protected String url;
	protected YelpDeal[] deals;
	
	protected String phone;
	protected String snippet_text;
	protected String snippet_image_url;
	protected String display_phone;
	protected String rating_img_url_large;
	protected String id;
	protected String[][] categories;
	protected YelpLocation location;
	
	YelpDealData(){};
}
