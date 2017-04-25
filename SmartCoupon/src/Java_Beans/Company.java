package Java_Beans;

import java.util.ArrayList;

/**
 * Class to hold information about a Company
 * This Class extends {@link DbComponent} 
 * @author Gidi
 */


public class Company extends DbComponent {
	
	private long id;
	private String compName;
	private String password;
	private String email;
	private ArrayList<Coupon> Coupons;
	
	/**
	 * Constructor
	 */
	public Company() {
		super();
	}
	/**
	 * Constructor
	 * @param long id of Company
	 */
	public Company(long id) {
		super();
		this.id = id;
	}
	/**
	 * Constructor
	 * @param String compName of Company
	 */
	public Company(String compName) {
		super();
		this.compName = compName;
	}
	/**
	 * Constructor
	 * @param String compName of Company
	 * @param String password of Company
	 * @param String email of Company
	 */
	public Company(String compName, String password, String email) {
		super();
		this.compName = compName;
		this.password = password;
		this.email = email;
	}
	/**
	 * Constructor
	 * @param long id of Company
	 * @param String compName of Company
	 * @param String password of Company
	 * @param String email of Company
	 */
	public Company(long id, String compName, String password, String email) {
		super();
		this.id = id;
		this.compName = compName;
		this.password = password;
		this.email = email;
	}
	/**
	 * get id of Company
	 * @return long id of Company
	 */
	public long getId() {
		return id;
	}
	/**
	 * set id of Company
	 * @param long id of Company
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * get name of Company
	 * @return String Name of Company
	 */
	public String getCompName() {
		return compName;
	}
	/**
	 * set name of Company
	 * @param String compName of Company
	 */
	public void setCompName(String compName) {
		this.compName = compName;
	}
	/**
	 * get password of Company
	 * @return String Password of Company
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * set password of Company
	 * @param String password of Company
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * get email of Company
	 * @return String Email of Company
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * set email of Company
	 * @param String Email of Company
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * get an ArrayList of coupons that belong to a specific company
	 * @return ArrayList<Coupon> of coupons that belong to a specific company
	 */
	public ArrayList<Coupon> getCoupons() {
		return Coupons;
	}
	/**
	 * set ArrayList coupons of Company
	 * @param ArrayList<Coupon> coupons
	 */
	public void setCoupons(ArrayList<Coupon> coupons) {
		Coupons = coupons;
	}
	/**
	 * override of toString method
	 */
	@Override
	public String toString() {
		final int maxLen = 1000;
		return "Company [id=" + id + ", compName=" + compName + ", password=" + password + ", email=" + email
				+ ", Coupons=" + (Coupons != null ? Coupons.subList(0, Math.min(Coupons.size(), maxLen)) : null) + "]";
	}
	
}
