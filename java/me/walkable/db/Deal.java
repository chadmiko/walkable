package me.walkable.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import me.walkable.util.UtcTime;

/**
 * 
 */

/**
 * @author Christopher Butera
 *
 */
public class Deal {

	private int did; // Deal ID
	private String vendor;
	private String title;
	private String link_url;
	private Timestamp start_date;
	private Timestamp end_date;
	private Timestamp updated_at;
	private int offset;
	private int remaining_quantity;
	private double price;
	private double value;
	private double discount;
	private DealItems items;
	
	public Deal(){
		items = new DealItems();
	}
	

	public static final String VENDOR_GROUPON = "groupon";
	public static final String VENDOR_YELP = "yelp";
	
	public static final int REMAINING_QUANTITY_UNLIMITED = -999999999;
	

	//This function will return the did (Deal ID)
	public int insertDeal(Connection conn){

		int did=0;
		String insertDeal = "INSERT INTO deals "
				+ "(vendor, title, link_url, start_date, end_date, updated_at, utc_offset, items) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(insertDeal, Statement.RETURN_GENERATED_KEYS);
//			ps.setInt(1, this.did);  This is autogenerated.
			int x=1;
			ps.setString(x++, this.vendor);
			ps.setString(x++, this.title);
			ps.setString(x++, this.link_url);
			ps.setTimestamp(x++, this.start_date);
			ps.setTimestamp(x++, this.end_date);
			ps.setTimestamp(x++, UtcTime.getCurrentTime());
			ps.setInt(x++, this.offset);
//			ps.setInt(x++, this.remaining_quantity);
//			ps.setDouble(x++, this.price);
//			ps.setDouble(x++, this.value);
//			ps.setDouble(x++, this.discount);
			ps.setString(x++, this.items.toString());
			ps.executeUpdate();
			
		    rs = ps.getGeneratedKeys();
		    if (rs.next()) {
		        did = rs.getInt(1);
		    } else {
		    	throw new SQLException("Failed to insert");
		        // throw an exception from here
		    }
		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
			//Ignore Duplicate
			//System.err.println("Found Duplicate Deal... Updating");
			did = findDeal(conn);
			updateDeal(conn, did);
			
		} catch (SQLException e) {
			System.out.println(ps.toString());
			e.printStackTrace();
		} finally {
          if (rs != null) {
              try {
                  rs.close();
              } catch (SQLException e) { /*ignored*/ }
          }
          if (ps != null) {
              try {
                  ps.close();
              } catch (SQLException e) { /*ignored*/ }
          }
		}

			return did;
	}

	/**
	 * @param conn
	 * @param did2
	 */
	private void updateDeal(Connection conn, int did) {
		String updateDeal = "Update deals "
				+ "SET vendor = ?, title = ?, link_url = ?, start_date = ?, "
				+ "end_date = ?, updated_at = ?, utc_offset = ?, items = ? "
				+ "WHERE did = ?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(updateDeal);
			int x=1;
			ps.setString(x++, this.vendor);
			ps.setString(x++, this.title);
			ps.setString(x++, this.link_url);
			ps.setTimestamp(x++, this.start_date);
			ps.setTimestamp(x++, this.end_date);
			ps.setTimestamp(x++, UtcTime.getCurrentTime());
			ps.setInt(x++, this.offset);
			ps.setString(x++, this.items.toString());
			ps.setInt(x++, did);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(ps.toString());
			e.printStackTrace();
		} finally {
          if (ps != null) {
              try {
                  ps.close();
              } catch (SQLException e) { /*ignored*/ }
          }
		}

	}

	private int findDeal(Connection conn){
		int did=0;
		String findLocation = "SELECT did " 
				+ "FROM deals d "
				+ "WHERE d.link_url = ? ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(findLocation, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, this.link_url);
			rs = ps.executeQuery();
			
		    if (rs.next()) {
		        did = rs.getInt(1);
		    } else {
		    	throw new SQLException("Failed to find duplicate did");
		        // throw an exception from here
		    }
		} catch (SQLException e) {
			System.out.println(ps.toString());
			e.printStackTrace();
		} finally {
          if (rs != null) {
              try {
                  rs.close();
              } catch (SQLException e) { /*ignored*/ }
          }
          if (ps != null) {
              try {
                  ps.close();
              } catch (SQLException e) { /*ignored*/ }
          }
		}

			return did;

	}

	
	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink_url() {
		return link_url;
	}

	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}

	public Timestamp getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Timestamp timestamp) {
		this.end_date = timestamp;
	}
	
	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getRemaining_quantity() {
		return remaining_quantity;
	}

	public void setRemaining_quantity(int remaining_quantity) {
		this.remaining_quantity = remaining_quantity;
	}

	public Timestamp getStart_date() {
		return start_date;
	}

	public void setStart_date(Timestamp start_date) {
		this.start_date = start_date;
	}

	public DealItems getItems() {
		return items;
	}

	public void setItems(DealItems items) {
		this.items = items;
	}
	
	public void setItems(ArrayList<DealItems.Options> opts){
		DealItems.Options[] myOpts = opts.toArray(new DealItems.Options[opts.size()]);
		this.items.setOptions(myOpts);
		this.setItems(items);

	}

}