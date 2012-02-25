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
	// Convert deg to Radians
	private double sinLat; // sin (radians(lat))
	private double cosCos; // cos (radians(lat)) * cos(radians(lng))
	private double cosSin; // cos (radians(lat)) * sin (radians(lng))
	
	//This function will return the lid (Location ID)
	public int insertLocation(Connection conn){

		//Calculate values for Haversine
		calculateSinCos();

		int lid=0;
		String insertLocations = "INSERT INTO locations "
				+ "(street, street2, neighborhood, zip, lat, lng, name, url, sin_lat, cos_cos, cos_sin)"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			int x=1;
			ps = conn.prepareStatement(insertLocations, Statement.RETURN_GENERATED_KEYS);
			ps.setString(x++, this.street);
			ps.setString(x++, this.street2);
			ps.setString(x++, this.neighborhood);
			ps.setString(x++, this.zip);
			ps.setDouble(x++, this.lat);
			ps.setDouble(x++, this.lng);
			ps.setString(x++, this.name);
			ps.setString(x++, this.url);
			ps.setDouble(x++, this.sinLat);
			ps.setDouble(x++, this.cosCos);
			ps.setDouble(x++, this.cosSin);
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
//			System.err.println("Found Duplicate Location");
			lid = findLocation(conn);
			
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

	private int findLocation(Connection conn){
		int lid=0;
		String findLocation = "SELECT lid " 
				+ "FROM locations l "
				+ "WHERE l.lat = ? "
				+ "AND l.lng = ? ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(findLocation, Statement.RETURN_GENERATED_KEYS);
			ps.setDouble(1, this.lat);
			ps.setDouble(2, this.lng);
			rs = ps.executeQuery();
			
		    if (rs.next()) {
		        lid = rs.getInt(1);
		    } else {
		    	throw new SQLException("Failed to find duplicate lid");
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

			return lid;

	}
	
	public void calculateSinCos(){
		double sinLat = Math.sin(Math.toRadians(this.lat));		
		double sinLng = Math.sin(Math.toRadians(this.lng));		
		double cosLat = Math.cos(Math.toRadians(this.lat));
		double cosLng = Math.cos(Math.toRadians(this.lng));
		
		this.sinLat = sinLat;
		this.cosCos = cosLat * cosLng;
		this.cosSin = cosLat * sinLng;
		
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

	public double getSinLat() {
		return sinLat;
	}

	public void setSinLat(double sinLat) {
		this.sinLat = sinLat;
	}

	public double getCosCos() {
		return cosCos;
	}

	public void setCosCos(double cosCos) {
		this.cosCos = cosCos;
	}

	public double getCosSin() {
		return cosSin;
	}

	public void setCosSin(double cosSin) {
		this.cosSin = cosSin;
	}	

}
