public abstract class Ticket {
	
	protected enum Format{
		Adult,
		Child,
		Employee,
		MoviePass,
		IMAX,
		THREE_D
	}

	private String movie;
	private String rating;
	private int day;
	private int time;
	private int id;
	private String format;
	private String type;
	private double price;
	
	private final double BEFORE_6_PRICE_ADULT = 10.50;
	private final double AFTER_6_PRICE_ADULT = 13.50;
	
	private final double BEFORE_6_PRICE_CHILD = 5.75;
	private final double AFTER_6_PRICE_CHILD = 10.75;
	
	private final double IMAX_PRICE_ADULT = 3.00;
	private final double IMAX_PRICE_CHILD = 2.00;
	
	private final double THREE_D_PRICE_ADULT = 2.50;
	private final double THREE_D_PRICE_CHILD = 1.50;
	
	private final double TAX = 0.096;
	
	 /**
	 * no args constructor
	 */
	public Ticket() {
		//initialize default values for fields
		movie = "";
		rating = "";
		day = 1;
		time = 8;
		id = 0;
		format = "";
		//feature = Format.NONE;
		type = "NONE";
	}
	

	/**
	 * constructor for super class
	 * @param movie - Movie name
	 * @param rating - Movie rating
	 * @param day - Movie date
	 * @param time - Movie time
	 * @param id - id of the patron
	 * @param format - type of patron
	 * @param type - Movie type
	 */
	public Ticket(String movie, String rating, int day, int time, int id, String format, String type)
	{
		this.movie = movie;
		this.rating = rating;
		this.day = day;
		this.time = time;
		this.id = id;
		this.format = format;
		this.type = type;
	}
	
	/**
	 * This method calculates ticket price based information
	 * @return The ticket price
	 */
	public abstract double calculateTicketPrice();
	
	/**
	 * returns the id of the patron
	 * @return the id of the patron
	 */
	public abstract int getId();
	
	
	/**
	 * toString method shows the ticket information such as movie name, rating, date, time, and price
	 */
	public String toString() 
	{
		// ADD THE TICKET TYPE AT THE TICKET MANAGER
		String ticketInfo = "";
		if(id!=0 && id!=-1)
		{
			ticketInfo+=" - "+id+" ";
		}
		if(type.equalsIgnoreCase("IMAX"))
		{
			ticketInfo+=type+" ";
		}
		if(type.equalsIgnoreCase("3D"))
		{
			ticketInfo+=type+" ";
		}
		ticketInfo+="Movie: "+movie+" ";
		ticketInfo+="Rating: "+rating+" ";
		ticketInfo+="Day: "+day+" ";
		ticketInfo+="Time: "+time+" ";
		ticketInfo+="Price: $"+price;
		
		return ticketInfo;
	}
	
	
	//Getters and Setters
	/**
	 * returns the movie title
	 * @return movie
	 */
	public String getMovie()
	{
		return movie;
	}
	/**
	 * returns the movie's rating
	 * @return rating
	 */
	public String getRating()
	{
		return rating;
	}
	/**
	 * returns the date the ticket is purchased
	 * @return day
	 */
	public int getDay()
	{
		return day;
	}
	/**
	 * returns the time the ticket is purchased
	 * @return time
	 */
	public int getTime()
	{
		return time;
	}
	/**
	 * returns the type of ticket (Adult, Child, Employee, or MoviePass)
	 * @return ticket type
	 */
	public String getPatron()
	{
		return format;
	}
	/**
	 * returns the type of movie
	 * @return feature
	 */
	public String getFeature()
	{
		return type;
	}
	/**
	 * returns the ticket's price
	 * @return the ticketPrice
	 */
	public double getTicketPrice()
	{
		return price;
	}
	/**
	 * returns the amount added for IMAX feature for adult
	 * @return IMAX_PRICE_ADULT
	 */
	public double getIMAXAdultPrice()
	{
		return IMAX_PRICE_ADULT;
	}
	/**
	 * returns the amount added for 3D feature for adult
	 * @return THREE_D_PRICE_ADULT
	 */
	public double getTHREE_D_AdultPrice()
	{
		return THREE_D_PRICE_ADULT;
	}
	/**
	 * returns the amount added for IMAX feature for child
	 * @return IMAX_PRICE_CHILD
	 */
	public double getIMAXChildPrice()
	{
		return IMAX_PRICE_CHILD;
	}
	/**
	 * returns the amount added for 3D feature for child
	 * @return BEFORE_6_PRICE_CHILD
	 */
	public double getTHREE_D_ChildPrice()
	{
		return THREE_D_PRICE_CHILD;
	}
	/**
	 * returns the price of ticket before 6pm for adult
	 * @return BEFORE_6_PRICE_ADULT
	 */
	public double getBefore6PriceAdult()
	{
		return BEFORE_6_PRICE_ADULT;
	}
	/**
	 * returns the price of ticket after 6pm for adult
	 * @return AFTER_6_PRICE_ADULT
	 */
	public double getAfter6PriceAdult()
	{
		return AFTER_6_PRICE_ADULT;
	}
	/**
	 * returns the price of ticket before 6pm for child
	 * @return BEFORE_6_PRICE_CHILD
	 */
	public double getBefore6PriceChild()
	{
		return BEFORE_6_PRICE_CHILD;
	}
	/**
	 * returns the price of ticket after 6pm for child
	 * @return AFTER_6_PRICE_CHILD
	 */
	public double getAfter6PriceChild()
	{
		return AFTER_6_PRICE_CHILD;
	}
	/**
	 * returns the tax rate
	 * @return TAX
	 */
	public double getTAXRATE()
	{
		return TAX;
	}
	
	
	
	
	/**
	 * Sets the name of the movie
	 * @param movie name of the movie
	 */
	public void setMovie(String movie)
	{
		this.movie = movie;
	}
	/**
	 * Sets the rating of the movie
	 * @param rating rating of the movie
	 */
	public void setRating(String rating)
	{
		this.rating = rating;
	}
	/**
	 * Sets the date of the ticket purchase
	 * @param day date of ticket purchase
	 */
	public void setDay(int day)
	{
		this.day = day;
	}
	/**
	 * Sets the time of the movie in purchased ticket
	 * @param time time of the movie in purchased ticket
	 */
	public void setTime(int time)
	{
		this.time = time;
	}
	/**
	 * Sets the id of the patron that purchased the ticket
	 * @param id the id of the patron
	 */
	public void setId(int id)
	{
		this.id = id;
	}
	/**
	 * Sets the feature of the movie on the purchased ticket
	 * @param feature the movie feature
	 */
	public void setFeature(String feature)
	{
		this.type = feature;
	}
	/**
	 * Set the type of patron purchasing the ticket
	 * @param patron the patron
	 */
	public void setPatron(String patron)
	{
		this.format = patron;
	}
	/**
	 * Set the ticket's price
	 * @param ticketPrice ticket price
	 */
	public void setTicketPrice(double ticketPrice)
	{
		this.price = ticketPrice;
	}
}


