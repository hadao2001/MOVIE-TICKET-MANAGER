public class Employee extends Ticket {

	private double priceTicket;
	private int id;
	
	public Employee(String movieName, String rating, int day, int time, int id, String format, String type) {
		
		super(movieName, rating, day, time, id, format, type);
		super.setTicketPrice(calculateTicketPrice());
		this.id = id;
	}
	
	@Override
	public double calculateTicketPrice() {

		priceTicket=0;
		
		if(super.getTime()>=8 && super.getTime()<18) {
			priceTicket += super.getBefore6PriceAdult();
		}
		else if(super.getTime() >= 18) {
			priceTicket += super.getAfter6PriceAdult();
		}
		if(super.getFeature().equalsIgnoreCase("IMAX")) {
			priceTicket += super.getIMAXAdultPrice();
		}
		else if(super.getFeature().equalsIgnoreCase("3D")) {
			priceTicket += super.getTHREE_D_AdultPrice();
		}
		else if(super.getFeature().equalsIgnoreCase("NONE")) {
			priceTicket +=0;
		}
		
		priceTicket = priceTicket / 2.0;
		
		double vat= priceTicket*super.getTAXRATE();
		
		priceTicket += vat;	
		
		return priceTicket;
	}

	@Override
	public int getId() {
		
		return this.id;
	}

	@Override
	public String toString() {

		String ticketInfo="";
		ticketInfo+="EMPLOYEE ";
		ticketInfo+=super.toString();
		
		return ticketInfo;

	}
}

