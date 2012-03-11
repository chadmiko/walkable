/**
 * 
 */
package me.walkable.livingsocial;

/**
 * @author Christopher Butera
 *
 */
public class LivingSocialObject {

	String id;
	String published;  //time
	String updated;
	String link;
	String title;
	String long_title;
	String deal_type;
	String market_id;
	String market_name;
	String georss_point;  //georss:point
	String georss_featureTypeTag; //georss:featureTypeTag
	String country_code;
	String subtitle;
	String offer_ends_at;
	String price;
	String value;
	String savings;
	String orders_count;
	String merchant_name;
	String image_url;
	String categories;
	boolean sold_out;
	boolean national;
	String description;
	String details;
	String content; //HTML
	String ls_merchant; //ls:merchant
	Location location;
	public class Location {
		String ls_address1; //ls:address1;
	String ls_city; //ls:city;
	String ls_state; //ls:state;
	String ls_zip; //ls:zip;
	String ls_country; //ls:country;
	String ls_phone; //ls:phone;
	String ls_georss_point; //georss:point;
	String ls_georss_featureTypeTag; //georss:featureTypeTag;
	String ls_latitude; //ls:latitude;
	String ls_longitude; //ls:longitude;

	}
}
