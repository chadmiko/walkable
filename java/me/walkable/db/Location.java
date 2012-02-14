package me.walkable.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 */

/**
 * @author Christopher Butera
 *
 */
public class Location {

	private int lid; // Location ID
	private String street;
	private String street2;
	private String neighborhood;
	private String zip;
	private double lat;
	private double lng;
	private String name;
	private String url;

	//This function will return the lid (Location ID)
	public int insertLocation(Connection conn){
		int lid=0;
		String insertLocations = "INSERT INTO locations "
				+ "(street, street2, neighborhood, zip, lat, lng, name, url)"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(insertLocations, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, this.street);
			ps.setString(2, this.street2);
			ps.setString(3, this.neighborhood);
			ps.setString(4, this.zip);
			ps.setDouble(5, this.lat);
			ps.setDouble(6, this.lng);
			ps.setString(7, this.name);
			ps.setString(8, this.url);			
			ps.executeUpdate();
			
		    rs = ps.getGeneratedKeys();
		    if (rs.next()) {
		        lid = rs.getInt(1);
		    } else {
		    	throw new SQLException("Failed to insert");
		        // throw an exception from here
		    }
		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
			//Ignore Duplicate
			//System.out.println("Found Duplicate");
			
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

			return lid;
	}

	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}	

}
