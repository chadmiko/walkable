/**
 * 
 */
package me.walkable.cj;

/**
 * @author Christopher Butera
 *
 */
public class CommissionJunctionObject {

	//NOT USED	protected String adId;
	//NOT USED	protected String advertiserIid;
	protected String advertiserName;
	protected String advertiserCategory;
	private String buyUrl;
	//NOT USED	protected String catalogID;
	//NOT USED	protected String currency;
	protected String description;
	//NOT USED	protected String imageUrl;
	//NOT USED	protected String inStock;
	//NOT USED	protected String isbn;
	protected String manufacturerName;
	//NOT USED	protected String manufacturerSKU;
	protected String name;
	protected String price;
	protected String retailPrice;
	protected String salePrice;
	//NOT USED	protected String sku;
	//NOT USED	protected String upc;
	public String getBuyUrl() {
		return buyUrl;
	}
	public void setBuyUrl(String buyUrl) {
		this.buyUrl = buyUrl;
	}
	public String getAdvertiserName() {
		return advertiserName;
	}
	public String getAdvertiserCategory() {
		return advertiserCategory;
	}
	public String getDescription() {
		return description;
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public String getName() {
		return name;
	}
	public String getPrice() {
		return price;
	}
	public String getRetailPrice() {
		return retailPrice;
	}
	public String getSalePrice() {
		return salePrice;
	}
	
}
