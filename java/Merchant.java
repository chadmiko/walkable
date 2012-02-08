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
public class Merchant {

	private int mid;
	private String name;
	private String url;
	private String groupon_id;
	private String yelp_id;

	//This function will return the mid (Merchant ID)
	public int insertMerchant(){
		String insertMerchant = "INSERT INTO merchants " 
				+ "(name, url, groupon_id, yelp_id) "
				+ "VALUES(?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(insertMerchant, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, this.name);
			ps.setString(2, this.url);
			ps.setString(3, this.groupon_id);
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
			if (conn != null) {
				try {
					conn.close();
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

	public String getGroupon_id() {
		return groupon_id;
	}

	public void setGroupon_id(String groupon_id) {
		this.groupon_id = groupon_id;
	}

	public String getYelp_id() {
		return yelp_id;
	}

	public void setYelp_id(String yelp_id) {
		this.yelp_id = yelp_id;
	}	

}
