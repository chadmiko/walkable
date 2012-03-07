/**
 * 
 */
package me.walkable.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Christopher Butera
 *
 */
public class DealItems {
	
	public class Options {
		protected String title;
		protected String buyUrl;

		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getBuyUrl() {
			return buyUrl;
		}
		public void setBuyUrl(String buyUrl) {
			this.buyUrl = buyUrl;
		}
		public Options(){};
	}

	DealItems(Options[] opt){
		this.options = opt;
	}
	
	/**
	 * 
	 */
	public DealItems() {
	}

	protected Options[] options;
	
	public Options[] getOptions() {
		return options;
	}

	public void setOptions(Options[] options) {
		this.options = options;
	}

	@Override
	public String toString() {
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(this, DealItems.class);
		//Don't return null
		if (json != null)
			return json;
		else
			return ""; 
	}



}
