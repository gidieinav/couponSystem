package Java_Beans;

import java.util.Date;
/**
 * Class to hold information about a Coupon
 * This Class extends {@link DbComponent} 
 * @author Gidi
 */

public class Coupon extends DbComponent {

	private long id;
	private String title;
	private Date startDate;
	private Date endDate;
	private int amount;
	private CouponType type;
	private String message;
	private double price;
	private String image;
	/**
	 * Constructor
	 */
	public Coupon() {
		super();
	}
	/**
	 * Constructor
	 * @param String title of Coupon
	 */
	public Coupon(String title) {
		super();
		this.title = title;
	}
	/**
	 * Constructor
	 * @param String title of Coupon
	 * @param Date endDate of Coupon
	 * @param Double price of Coupon
	 */
	public Coupon(String title, Date endDate, Double price) {
		super();
		this.title = title;
		this.endDate = endDate;
		this.price = price;
	}
	/**
	 * Constructor 
	 * @param long id of Coupon
	 * @param String title of Coupon
	 * @param Date startDate of Coupon
	 * @param Date endDate of Coupon
	 * @param int amount of Coupon
	 * @param CouponType type of Coupon
	 * @param String message of Coupon
	 * @param double price of Coupon
	 * @param String image of Coupon
	 */
	public Coupon(long id, String title, Date startDate, Date endDate, int amount, CouponType type, String message,
			double price, String image) {
		super();
		this.id = id;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
		this.image = image;
	}
	/**
	 * get id of Coupon
	 * @return long id of Coupon
	 */
	public long getId() {
		return id;
	}
	/**
	 * set id of Coupon
	 * @param long id of Coupon
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * get title of Coupon
	 * @return String Title of Coupon
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * set title of Coupon
	 * @param String Title of Coupon
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * get start date of Coupon
	 * @return Date startDate of Coupon
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * set start date of Coupon
	 * @param Date startDate of Coupon
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * get end date of Coupon
	 * @return Date EndtDate of Coupon
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * set end date of Coupon
	 * @param Date EndtDate of Coupon
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * get amount of Coupon
	 * @return int Amount of Coupon
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * set amount of Coupon
	 * @param int Amount of Coupon
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	/**
	 * get coupon type
	 * @return {@link CouponType}
	 */
	public CouponType getType() {
		return type;
	}
	/**
	 * set coupon type
	 * @param {@link CouponType}
	 */
	public void setType(CouponType type) {
		this.type = type;
	}
	/**
	 * get Coupon message  
	 * @return String message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * set Coupon message
	 * @param String message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * get Coupon price
	 * @return double price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * set Coupon price
	 * @param double price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * get Coupon image
	 * @return String image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * set Coupon image
	 * @param String image
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * override of toString method
	 */
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", amount=" + amount + ", type=" + type + ", message=" + message + ", price=" + price + ", image="
				+ image + "]";
	}
	
	
	

}
