import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
/**
 * Movie ticket manager
 * implements MovieTicketManagerInterface
 * @author rashiqc
 *
 */


public class MovieTicketManager implements MovieTicketManagerInterface {

	private ArrayList<Ticket> ticketList = new ArrayList<Ticket>(100);
	
	
	public MovieTicketManager() { 
		// TODO Auto-generated constructor stub
	}

	@Override
	public int numVisits(int id) {

		int count = 0;
		
		for(int i = 0; i< ticketList.size(); i++) {
			if(ticketList.get(i).getId() == id) {
				count++;
			}
		}
		
		return count;
	}

	@Override
	public int numThisMovie(int id, String movie) {

		int count = 0;
		
		for(int i = 0; i<ticketList.size(); i++) {
			//if ID and Movie name match, then add
			if(ticketList.get(i).getId() == id && ticketList.get(i).getMovie() == movie) {
				count++;
			}
		}
		
		return count;
	}

	@Override
	public int numMoviesToday(int id, int date) {

		int count = 0;
		
		for(int i = 0; i<ticketList.size(); i++) {
			//if ID and date match, then add
			if(ticketList.get(i).getId() == id && ticketList.get(i).getDay() == date) {
				count++;
			}
		}
		
		
		return count;
	}

	@Override
	public double addTicket(String movieN, String rating, int d, int t, String f, String format, int id) {

		//check time for valid screen time
		if(t < 8 || t > 23) {
			
			return -2;//return -2 if invalid time
		}
		//check valid date
		if(d < 1 || d > 31) {
			
			return -3; //return -3 if invalid date
		}
		
		//check type of ticket
		if(format.equalsIgnoreCase("Adult")) {
			
			Adult ticket = new Adult(movieN, rating, d, t, id, format, f);
			ticketList.add(ticket);
			return ticket.getTicketPrice();
		}
		else if(format.equalsIgnoreCase("Child")) {
			
			Child ticket = new Child(movieN, rating, d, t, id, format, f);
			ticketList.add(ticket);
			return ticket.getTicketPrice();
		}
		else if(format.equalsIgnoreCase("Employee")) {
			
			int moviesRedeemedCount=0;
			
			//count how many movies the employee watched
			for(int i = 0; i < ticketList.size(); i++) {
				
				//search list for similar ID
				if(ticketList.get(i).getId() == id && ticketList.get(i).getPatron().equalsIgnoreCase(format)) {
					
					moviesRedeemedCount++;
				}
			}
			
			if(moviesRedeemedCount < 3) {
				
				Employee ticket = new Employee(movieN, rating, d, t, id, format, f);
				ticket.setTicketPrice(0); //if employee watched less than 3 movies set price to zero
				ticketList.add(ticket);
				return ticket.getTicketPrice();
			}
			else if(moviesRedeemedCount <= 3) {
				
				Employee ticket = new Employee(movieN, rating, d, t, id, format, f);
				ticketList.add(ticket);
				return ticket.getTicketPrice();
			}
		}
		else if(format.equalsIgnoreCase("MoviePass")) {
			
			//check for first movie of MoviePass
			boolean firstMovie = true;
			
			//check if different day and different movie
			boolean differentDay = true;
			boolean differentMovie = true;
			boolean notIMAXor3D = true;
			
			if(f.equalsIgnoreCase("IMAX") || f.equalsIgnoreCase("THREE_D")) {
				
				notIMAXor3D = false;
			}
			
			for(int i = 0; i< ticketList.size(); i++) {
				
				if(ticketList.get(i).getId() == id && ticketList.get(i).getPatron().equalsIgnoreCase(format)) {
					
					firstMovie = false;
					
					if(ticketList.get(i).getDay() == d) {
					
						differentDay = false;
					}
					if(ticketList.get(i).getMovie() == movieN) {
					
						differentMovie = false;
					}
				}
			}
			
			if(firstMovie) {
				
				if(notIMAXor3D) {
					
					MoviePass ticket = new MoviePass(movieN, rating, d, t, id,format, f);
					ticket.setTicketPrice(9.99); //ticket price is 9.99
					ticketList.add(ticket);
					return ticket.getTicketPrice();
				}
				else {
					
					MoviePass ticket = new MoviePass(movieN, rating, d, t, id,format, f);
					ticketList.add(ticket);
					//calculates regular adult price
					return ticket.getTicketPrice();					
				}
			}
			else {

				if(differentDay && differentMovie && notIMAXor3D) {
					
					MoviePass ticket = new MoviePass(movieN, rating, d, t, id, format, f);
					ticket.setTicketPrice(0);
					ticketList.add(ticket);
					return ticket.getTicketPrice();
				}
				else {
					
					MoviePass ticket = new MoviePass(movieN, rating, d, t, id, format, f);
					ticketList.add(ticket);
					return ticket.getTicketPrice();
				}
			}
		}
		
		return -999;
	}

	@Override
	public double totalSalesMonth() {

		double total = 0;
		
		for(int i = 0; i< ticketList.size(); i++) {
			
			total += ticketList.get(i).getTicketPrice();
		}
		
		return total;
	}

	@Override
	public String monthlySalesReport() {
		
		String result = "Monthly Sales Report\n\n\tNumber\tSales\n";
		int countAdult=0;
		double adultSales=0;
		int countChild=0;
		double childSales=0;
		int countEmployee=0;
		double employeeSales=0;
		int countMoviePass=0;
		double moviepassSales=0;
		
		for(int i = 0; i< ticketList.size(); i++) {
			
			if(ticketList.get(i).getPatron().equalsIgnoreCase("adult")) {
				countAdult++;
				adultSales += ticketList.get(i).getTicketPrice();
			}
			else if(ticketList.get(i).getPatron().equalsIgnoreCase("child")) {
				countChild++;
				childSales += ticketList.get(i).getTicketPrice();
			}
			else if(ticketList.get(i).getPatron().equalsIgnoreCase("employee")) {
				countEmployee++;
				employeeSales += ticketList.get(i).getTicketPrice();
			}
			else if(ticketList.get(i).getPatron().equalsIgnoreCase("moviepass")) {
				countMoviePass++;
				moviepassSales += ticketList.get(i).getTicketPrice();
			}
		}
		
		result += "ADULT "+countAdult+" $"+String.format("%.2f", adultSales)+"\n";
		result += "CHILD "+countChild+" $"+String.format("%.2f", childSales)+"\n";
		result += "EMPLOYEE "+countEmployee+" $"+String.format("%.2f", employeeSales)+"\n";
		result += "MOVIEPASS "+countMoviePass+" $"+String.format("%.2f", moviepassSales)+"\n"; 		
		result += "Total Monthly Sales: $"+String.format("%.2f", totalSalesMonth());
		return result;
	}

	@Override
	public ArrayList<String> get3DTickets() {

		sortByDay();
		
		ArrayList<String> Three_D_Tickets = new ArrayList<String>();
	
		for(int i = 0; i< ticketList.size(); i++) {
			
			if(ticketList.get(i).getFeature().equalsIgnoreCase("3D")) {
				Three_D_Tickets.add(ticketList.get(i).toString());
			}
		}
		
		return Three_D_Tickets;
	}

	@Override
	public ArrayList<String> getAllTickets() {

		sortByDay();
		
		ArrayList<String> All_Tickets = new ArrayList<String>();
		
		for(int i = 0; i< ticketList.size(); i++) {
			
			if(ticketList.get(i)!= null) {
				All_Tickets.add(ticketList.get(i).toString());
			}
		}
		
		return All_Tickets;
	}

	@Override
	public ArrayList<String> getMoviePassTickets() {

		sortById();
		
		ArrayList<String> Movie_Pass_Tickets = new ArrayList<String>();
		
		for(int i = 0; i< ticketList.size(); i++) {
			if(ticketList.get(i).getPatron().equalsIgnoreCase("MoviePass")) {
				Movie_Pass_Tickets.add(ticketList.get(i).toString());
			}
		}
		
		return Movie_Pass_Tickets;		
	}

	@Override
	public void readFile(File file) throws FileNotFoundException {

		Scanner sc = new Scanner(file);
		String[] ticketInfo = new String[7];
		
		while(sc.hasNextLine()) {
			
			ticketInfo = sc.nextLine().split(":");
			
			//Movie Name [0], rating [1], date [2], time [3], feature [4], type of patron [5], id [6]
			addTicket(ticketInfo[0], ticketInfo[1], Integer.valueOf(ticketInfo[2]), Integer.valueOf(ticketInfo[3]),
					ticketInfo[4], ticketInfo[5], Integer.valueOf(ticketInfo[6]));
		}
		
		sc.close();
	}
	
	/**
	 * sorts arraylist of the ticket object by day
	 */
	private void sortByDay() {
		
		Ticket temp;
		for(int i=1; i<ticketList.size(); i++) {
			for (int j=i; j>0; j--) {
				if (ticketList.get(j).getDay()<ticketList.get(j-1).getDay()) {
					temp=ticketList.get(j);
					ticketList.set(j, ticketList.get(j-1));
					ticketList.set(j-1, temp);
				}
			}
		}
	}
	
	/**
	 * sorts array list of the ticket object by id number
	 */
	private void sortById() {
		
		Ticket temp;
		for(int i=1; i<ticketList.size(); i++) {
			for (int j=i; j>0; j--) {
				if (ticketList.get(j).getId()<ticketList.get(j-1).getId()) {
					temp=ticketList.get(j);
					ticketList.set(j, ticketList.get(j-1));
					ticketList.set(j-1, temp);
	}
		}
		
		}		
	}
}
		
	