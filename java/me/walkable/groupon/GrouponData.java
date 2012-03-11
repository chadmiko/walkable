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
	protected class GrouponRatings {

		protected String linkText;
		protected int reviewsCount;
		protected String id;
		protected String url;
		protected double rating;
		GrouponRatings(){};
	}

	protected class GrouponDealTypes {

		protected String name;
		protected String id;
		GrouponDealTypes(){};
	}

	protected class GrouponMerchant {

		protected GrouponRatings[] ratings;
		protected String name;
		protected String id;
		protected String websiteUrl;
		GrouponMerchant(){};
	}

	protected class GrouponTextAd {

		protected String line1;
		protected String line2;
		protected String headline;	
		GrouponTextAd(){};
	}

	protected class GrouponSays {

		protected String websiteContent;
		protected String websiteContentHtml;
		protected String title;
		protected String id;
		protected String emailContentHtml;
		GrouponSays(){};
	}

	protected class GrouponDivision {

		protected String timezone;
		protected int timezoneOffsetInSeconds;
		protected String name;
		protected String id;
		protected double lng;
		protected double lat;
		GrouponDivision(){};
	}

	protected class GrouponArea{
		GrouponArea(){};
	}


	protected class GrouponTags{
		protected String name;
		GrouponTags(){};
	}
	
	protected class GrouponChannel{
		GrouponChannel(){};
	}
	
	protected class GrouponPrice {
		protected int amount;
		protected String currencyCode;
		protected String formattedAmount;
		GrouponPrice(){};
	}

	protected class GrouponValue{
		protected int amount;
		protected String currencyCode;
		protected String formattedAmount;
		GrouponValue(){};
	}

	protected class GrouponLocation {

		protected String city;
		protected String neighborhood;
		protected String state;
		protected String streetAddress1;
		protected String streetAddress2;
		protected String name;
		protected String country;
		protected double lat;
		protected double lng;
		protected String phoneNumber;
		protected String postalCode;
		
		GrouponLocation(){};
	}

	protected class GrouponDetails{
		protected String description;
		GrouponDetails(){};
	}

	protected class GrouponDiscount{

		protected int amount;
		protected String currencyCode;
		protected String formattedAmount;
		protected int soldQuantity;
		
		GrouponDiscount(){};
	}
	
	protected class GrouponCustomFields{
		
		GrouponCustomFields(){};
	}
	
	protected class GrouponOptions {
		protected GrouponCustomFields[] customFields;
		protected String expiresAt;
		protected GrouponPrice price;
		
		protected int maximumPurchaseQuantity;
		protected String buyUrl;
		protected int discountPercent;
		protected int initialQuantity;
		protected int remainingQuantity;
		protected boolean isLimitedQuantity;
		protected GrouponValue value;
	
		protected GrouponLocation[] redemptionLocations;
	
		protected String title;
		protected String externalUrl;
		protected GrouponDetails[] details;
		
		protected String soldQuantityMessage;
		protected int minimumPurchaseQuantity;
		protected boolean isSoldOut;
		protected int id;
		protected GrouponDiscount discount;
		
		GrouponOptions(){};
	}
	
	//Member variables
	protected String vip;
	protected String type;
	protected String grouponRating;
	protected GrouponDealTypes[] dealTypes;

	protected String pitchHtml;
	protected String smallImageUrl;
	protected String status;
	protected String highlightsHtml;
	protected String grid4ImageUrl;
	protected String placementPriority;
	protected String announcementTitle;
	protected GrouponMerchant merchant;

	protected String isAutoRefundEnabled;
	protected String grid6ImageUrl;
	protected String title;
	protected String redemptionLocation;
	protected String dealUrl;
	protected GrouponTextAd textAd;

	protected String startAt;
	protected String soldQuantityMessage;
	protected GrouponSays says;

	protected String largeImageUrl;
	protected String shippingAddressRequired;
	protected String locationNote;
	protected String tippingPoint;
	protected String isSoldOut;
	protected String endAt;
	protected GrouponDivision division;

	protected GrouponArea[] areas;
	protected String mediumImageUrl;
	protected GrouponTags[] tags; 

	protected boolean isNowDeal;
	protected String finePrint;
	protected GrouponChannel[] channels;
	protected String id;
	protected String tippedAt;
	protected int soldQuantity;
	protected GrouponOptions[] options;
		
	protected boolean isTipped;
	protected String sidebarImageUrl;

	GrouponData(){};



}
