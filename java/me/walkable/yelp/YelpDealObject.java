package me.walkable.yelp;

/**
 * @author Christopher Butera
 *
 */
public class YelpDealObject {

	public class YelpRegion {
		public class YelpSpan{
			public double latitude_delta;
			public double longitude_delta;
			YelpSpan(){};
		}
		
		public class YelpCenter{
			public double latitude;
			public double longitude;
			YelpCenter(){};
		}
		
		public YelpSpan span;
		public YelpCenter center;
		YelpRegion(){};
	}
	
	public YelpRegion region;
	public int total;
	public YelpDealData[] businesses;
	
	YelpDealObject(){};
}

