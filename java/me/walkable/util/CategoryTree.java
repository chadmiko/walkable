/**
 * 
 */
package me.walkable.util;

import java.util.ArrayList;

/**
 * @author Christopher Butera
 *
 */
public class CategoryTree {
	private String category;
	private String unparsedDeals;
	private int count;
	private ArrayList<CategoryTree> subCategories;

	public CategoryTree(String category){
		this.category = category;
		this.subCategories = new ArrayList<CategoryTree>();
	}
	
	public CategoryTree addSubCategory(String subCategory){
		CategoryTree sub = new CategoryTree(subCategory);
		this.subCategories.add(sub);
		return sub;
	}
	
	public ArrayList<CategoryTree> getSubCategories(){
		return subCategories;
	}
	
	public boolean isLeaf(){
		if (this.subCategories.size() == 0)
			return true;
		else
			return false;
	}

//	@Override
//	public String toString() {
//		return category;
//	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getUnparsedDeals() {
		return unparsedDeals;
	}

	public void setUnparsedDeals(String unparsedDeals) {
		this.unparsedDeals = unparsedDeals;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
