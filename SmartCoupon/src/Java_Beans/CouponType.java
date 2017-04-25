package Java_Beans;

/**
 * Coupon type that can be used
 * @author Gidi
 */

public enum CouponType {
	FOOD(1),
	RESTURANS(2),
	ELECTRICITY(3),
	HELTH(4),
	SPORT(5),
    CAMPING (6) ,
	TRAVELLING (7),
	MUSIC_INSTRUMENTS (8);
	
	
	private int value; 
	private CouponType(int value) { 
		this.value = value; 
	}
	/**
	 * get value
	 * @return int value of CouponType
	 */
	public  int getValue() {
		return value;
	}
	/**
	 * @param int value of CouponType
	 * @return CouponType
	 */
	public static CouponType getCouponType(int value) {
		
		switch (value) {
	       case 1:
	    	   return CouponType.FOOD;
	       case 2:
	    	   return CouponType.RESTURANS;
	       case 3:
	    	   return CouponType.ELECTRICITY;
	       case 4:
	    	   return CouponType.HELTH;
	       case 5:
	    	   return CouponType.SPORT;
	       case 6:
	    	   return CouponType.CAMPING;
	       case 7:
	    	   return CouponType.TRAVELLING;
	       case 8:
	    	   return CouponType.MUSIC_INSTRUMENTS;
	       default: 
	    	   break;
		}
		return null;
	    	   
	}
	
	/**
	 * @param String coupon type
	 * @return CouponType
	 */
	public static CouponType getCouponType(String couponType) {
		
		switch (couponType) {
	       case "FOOD":
	    	   return CouponType.FOOD;
	       case "RESTURANS":
	    	   return CouponType.RESTURANS;
	       case "ELECTRICITY":
	    	   return CouponType.ELECTRICITY;
	       case "HELTH":
	    	   return CouponType.HELTH;
	       case "SPORT":
	    	   return CouponType.SPORT;
	       case "CAMPING":
	    	   return CouponType.CAMPING;
	       case "TRAVELLING":
	    	   return CouponType.TRAVELLING;
	       case "MUSIC_INSTRUMENTS":
	    	   return CouponType.MUSIC_INSTRUMENTS;
	       default: 
	    	   break;
		}
		return null;
	    	   
	}
}
