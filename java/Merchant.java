import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Christopher Butera
 *
 */
public class Merchant {

	private int mid;
	private String name;
	private String url;
	private String yelp_id;
	
	private ArrayList<Location> locations;
	
	public void addLocation(Location l){
		locations.add(l);
	}

	//This function will return the mid (Merchant ID)
	public int insertMerchant(Connection conn){
		String insertMerchant = "INSERT INTO merchants " 
				+ "(name, url, yelp_id) "
				+ "VALUES(?, ?, ?, ?)";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(insertMerchant, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, this.name);
			ps.setString(2, this.url);
			ps.setString(4, this.yelp_id);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();

			if (rs.next()) {
				this.mid = rs.getInt(1);
			} else {
				throw new SQLException("Failed to insert");
				// throw an exception from here
			}

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
		}
		return this.mid;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
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

	public String getYelp_id() {
		return yelp_id;
	}

	public void setYelp_id(String yelp_id) {
		this.yelp_id = yelp_id;
	}	

}
