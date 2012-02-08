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
	private int mid; // Merchant ID
	private String street;
	private String street2;
	private String neighborhood;
	private String zip;
	private double lat;
	private double lng;

	//This function will return the lid (Location ID)
	public int insertLocation(){
		int lid=0;
		String insertLocations = "INSERT INTO locations "
				+ "(mid, street, street2, neighborhood, zip, lat, lng)"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(insertLocations, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, this.mid);
			ps.setString(2, this.street);
			ps.setString(3, this.street2);
			ps.setString(4, this.neighborhood);
			ps.setString(5, this.zip);
			ps.setDouble(6, this.lat);
			ps.setDouble(7, this.lng);
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
          if (conn != null) {
              try {
                  conn.close();
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

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
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

}
