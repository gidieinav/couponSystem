package DbOperator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import Java_Beans.Coupon;
import Java_Beans.CouponType;
import Java_Beans.DbComponent;
/**
 * Class  that handling repeat actions on DB for Coupon
 * @author Gidi
 */
public class CouponDbOperator extends DbOperatorBase
{
	/**
	 * Override runSelectedQueryAndBuildArrayListComponent
	 */
	@Override
	public ArrayList<DbComponent> runSelectedQueryAndBuildArrayListComponent(Statement stmt, String query) throws SQLException, ParseException {
		
		ArrayList<DbComponent> coupons = new ArrayList<DbComponent>();	
		ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(rs.getLong("ID"));
				coupon.setTitle(rs.getString("TITLE").trim());
				coupon.setStartDate(rs.getDate("START_DATE"));
				coupon.setEndDate(rs.getDate("END_DATE"));
				coupon.setAmount(rs.getInt("AMOUNT"));
				coupon.setType(CouponType.valueOf(rs.getString("TYPE").trim()));
				coupon.setMessage(rs.getString("MESSAGE").trim());
				coupon.setPrice(rs.getDouble("PRICE"));
				coupon.setImage(rs.getString("IMAGE").trim());

				coupons.add(coupon);
		        }
		return coupons;

	}

}