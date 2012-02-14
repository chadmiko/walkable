package me.walkable.groupon;

/**
 * 
 */

/**
 * @author Christopher Butera
 *
 */
public class GrouponData {

	//Inner Classes
	public class GrouponRatings {

		public String linkText;
		public int reviewsCount;
		public String id;
		public String url;
		public double rating;
		GrouponRatings(){};
	}

	public class GrouponDealTypes {

		public String name;
		public String id;
		GrouponDealTypes(){};
	}

	public class GrouponMerchant {

		public GrouponRatings[] ratings;
		public String name;
		public String id;
		public String websiteUrl;
		GrouponMerchant(){};
	}

	public class GrouponTextAd {

		public String line1;
		public String line2;
		public String headline;	
		GrouponTextAd(){};
	}

	public class GrouponSays {

		public String websiteContent;
		public String websiteContentHtml;
		public String title;
		public String id;
		public String emailContentHtml;
		GrouponSays(){};
	}

	public class GrouponDivision {

		public String timezone;
		public int timezoneOffsetInSeconds;
		public String name;
		public String id;
		public double lng;
		public double lat;
		GrouponDivision(){};
	}

	public class GrouponArea{
		GrouponArea(){};
	}


	public class GrouponTags{
		public String name;
		GrouponTags(){};
	}
	
	public class GrouponChannel{
		GrouponChannel(){};
	}
	
	public class GrouponPrice {
		public String amount;
		public String currencyCode;
		public String formattedAmount;
		GrouponPrice(){};
	}

	public class GrouponValue{
		public int amount;
		public String currencyCode;
		public String formattedAmount;
		GrouponValue(){};
	}

	public class GrouponLocation {

		public String city;
		public String neighborhood;
		public String state;
		public String streetAddress1;
		public String streetAddress2;
		public String name;
		public String country;
		public double lat;
		public double lng;
		public String phoneNumber;
		public String postalCode;
		
		GrouponLocation(){};
	}

	public class GrouponDetails{
		public String description;
		GrouponDetails(){};
	}

	public class GrouponDiscount{

		public int amount;
		public String currencyCode;
		public String formattedAmount;
		public int soldQuantity;
		
		GrouponDiscount(){};
	}
	
	public class GrouponCustomFields{
		
		GrouponCustomFields(){};
	}
	
	public class GrouponOptions {
		public GrouponCustomFields[] customFields;
		public String expiresAt;
		public GrouponPrice price;
		
		public int maximumPurchaseQuantity;
		public String buyUrl;
		public int discountPercent;
		public int initialQuantity;
		public int remainingQuantity;
		public boolean isLimitedQuantity;
		public GrouponValue value;
	
		public GrouponLocation[] redemptionLocations;
	
		public String title;
		public String externalUrl;
		public GrouponDetails[] details;
		
		public String soldQuantityMessage;
		public int minimumPurchaseQuantity;
		public boolean isSoldOut;
		public int id;
		public GrouponDiscount discount;
		
		GrouponOptions(){};
	}
	
	//Member variables
	public String vip;
	public String type;
	public String grouponRating;
	public GrouponDealTypes[] dealTypes;

	public String pitchHtml;
	public String smallImageUrl;
	public String status;
	public String highlightsHtml;
	public String grid4ImageUrl;
	public String placementPriority;
	public String announcementTitle;
	public GrouponMerchant merchant;

	public String isAutoRefundEnabled;
	public String grid6ImageUrl;
	public String title;
	public String redemptionLocation;
	public String dealUrl;
	public GrouponTextAd textAd;

	public String startAt;
	public String soldQuantityMessage;
	public GrouponSays says;

	public String largeImageUrl;
	public String shippingAddressRequired;
	public String locationNote;
	public String tippingPoint;
	public String isSoldOut;
	public String endAt;
	public GrouponDivision division;

	public GrouponArea[] areas;
	public String mediumImageUrl;
	public GrouponTags[] tags; 

	public boolean isNowDeal;
	public String finePrint;
	public GrouponChannel[] channels;
	public String id;
	public String tippedAt;
	public int soldQuantity;
	public GrouponOptions[] options;
		
	public boolean isTipped;
	public String sidebarImageUrl;

	GrouponData(){};



}
