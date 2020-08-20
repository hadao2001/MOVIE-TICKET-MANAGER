public class Adult extends Ticket {

	public Adult(String movieName, String rating, int day, int time, int id, String format, String type) {
		
		super(movieName, rating, day, time, id, format, type);
		super.setTicketPrice(calculateTicketPrice());
	}
	
	@Override
	public double calculateTicketPrice() {

		double priceTicket=0;
		
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
		
		//calculate tax
		double vat= priceTicket*super.getTAXRATE();
		//add tax
		priceTicket += vat;	
		
		return priceTicket;
	}
	
	@Override
	public int getId() {

		return -1;
	}
	
	
	@Override
	public String toString() {

		String ticketInfo="";
		ticketInfo+="ADULT ";
		ticketInfo+=super.toString();
		
		return ticketInfo;
	}
}