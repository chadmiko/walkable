package me.walkable.yelp;

/**
 * @author Christopher Butera
 *
 */
public class YelpDealObject {

	protected class YelpRegion {
		protected class YelpSpan{
			protected double latitude_delta;
			protected double longitude_delta;
			YelpSpan(){};
		}
		
		protected class YelpCenter{
			protected double latitude;
			protected double longitude;
			YelpCenter(){};
		}
		
		protected YelpSpan span;
		protected YelpCenter center;
		YelpRegion(){};
	}
	
	protected YelpRegion region;
	protected int total;
	protected YelpDealData[] businesses;
	
	YelpDealObject(){};
	
	public int getNumberOfDeals(){
		return businesses.length;
	}
	
	public int getTotalNumberOfDeals(){
		return total;
	}
}

