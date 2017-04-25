package Java_Beans;

import java.util.ArrayList;
/**
 * Class to hold information about a Customer
 * This Class extends {@link DbComponent} 
 * @author Gidi
 */

public class Customer extends DbComponent {
	private long id;
	private String custName;
	private String password;
	private ArrayList<Coupon> Coupons;
	
	/**
	 * Constructor
	 */
	public Customer() {
		super();
	}
	/**
	 * Constructor
	 * @param long id of Customer
	 */
	public Customer(long id) {
		super();
		this.id = id;
	}
	/**
	 * Constructor
	 * @param custName of Customer
	 */
	public Customer(String custName) {
		super();
		this.custName = custName;
	}
	/**
	 * Constructor
	 * @param custName of Customer
	 * @param password of Customer
	 */
	public Customer(String custName, String password) {
		super();
		this.custName = custName;
		this.password = password;
	}
	/**
	 * Constructor
	 * @param id of Customer
	 * @param custName of Customer
	 * @param password of Customer
	 */
	public Customer(long id, String custName, String password) {
		super();
		this.id = id;
		this.custName = custName;
		this.password = password;
	}
	/**
	 * get id of Customer
	 * @return long id of Customer
	 */
	public long getId() {
		return id;
	}
	/**
	 * set id of Customer
	 * @param long id of Customer
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * get name of Customer
	 * @return String custName
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * set name of Customer
	 * @param String custName
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	/**
	 * get password of Customer
	 * @return String password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * set password of Customer
	 * @param String password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * get Coupons of Customer
	 * @return ArrayList<Coupon> Coupons
	 */
	public ArrayList<Coupon> getCoupons() {
		return Coupons;
	}
	/**
	 * set Coupons of Customer
	 * @param ArrayList<Coupon> Coupons
	 */
	public void setCoupons(ArrayList<Coupon> coupons) {
		Coupons = coupons;
	}
	/**
	 * override of toString method
	 */
	@Override
	public String toString() {
		final int maxLen = 100;
		return "Customer [id=" + id + ", custName=" + custName + ", password=" + password + ", Coupons="
				+ (Coupons != null ? Coupons.subList(0, Math.min(Coupons.size(), maxLen)) : null) + "]";
	}
	
	

}
