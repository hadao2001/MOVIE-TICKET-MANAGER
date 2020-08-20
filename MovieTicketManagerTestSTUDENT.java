
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MovieTicketManagerTestSTUDENT {
	private MovieTicketManager ticketList;
	

	@Before
	public void setUp() throws Exception {
		ticketList = new MovieTicketManager();
		
		//add adults
				ticketList.addTicket("Knives Out", "PG13", 5,18,"NONE","Adult",0);
				ticketList.addTicket("Jumanji: The Next Level", "PG13", 3,10,"NONE","Adult",0);
				ticketList.addTicket("Playing With Fire", "PG13", 4,13,"3D","Adult",0);
				
				//add children
				ticketList.addTicket("Frozen 2", "G", 6,18,"NONE","Child",0);
				ticketList.addTicket("Frozen 2", "G", 3,11,"NONE","Child",0);
				ticketList.addTicket("Midways", "PG 13", 5,10,"3D","Child",0);
				
				//add employees
				ticketList.addTicket("Last Chrismas", "NR", 4,10,"NONE","Employee",12345);
				ticketList.addTicket("Joker", "NR", 7,12,"NONE","Employee",23456);
				ticketList.addTicket("Harriet", "PG13", 5,6,"IMAX","Employee",45678);
				
				//add MoviePass members
				ticketList.addTicket("Joker", "NR", 5,19,"NONE","MoviePass",22222);
				ticketList.addTicket("Joker", "NR", 1,13,"IMAX","MoviePass",55555);
				ticketList.addTicket("Solo", "PG13", 2,12,"NONE","MoviePass",33333);

	}

	@After
	public void tearDown() throws Exception {
		ticketList = null;
	}

	/**
	 * Student Test the number of visits to the theater within the month
	 * This only applied to those who have id members - Employees or MoviePass members
	 */
	@Test
	public void testNumVisits() {
		       //Employee
				assertEquals(1,ticketList.numVisits(12345));
				ticketList.addTicket("Joker", "NR", 8,18,"NONE","Employee",12345);
				assertEquals(2,ticketList.numVisits(12345));
				ticketList.addTicket("Joker", "PG13", 10,16,"NONE","Employee",12345);
				assertEquals(3,ticketList.numVisits(12345));
				
				//MoviePass member
				assertEquals(1,ticketList.numVisits(33333));
				ticketList.addTicket("Last Chrismas", "NR", 3,12,"NONE","MoviePass",33333);
				assertEquals(2,ticketList.numVisits(33333));
				ticketList.addTicket("Frozen 2", "G", 6,14,"NONE","MoviePass",33333);
				assertEquals(3,ticketList.numVisits(33333));
	
	}

	/**
	 * Student Test the number of times this movie has been viewed
	 * This only applied to those who have id numbers - Employees or MoviePass members
	 */
	@Test
	public void testNumThisMovie() {
		//Employee
		assertEquals(1,ticketList.numVisits(12345));
		ticketList.addTicket("Joker", "NR", 8,18,"NONE","Employee",12345);
		assertEquals(2,ticketList.numVisits(12345));
		ticketList.addTicket("Joker", "PG13", 10,16,"NONE","Employee",12345);
		assertEquals(3,ticketList.numVisits(12345));
		
		//MoviePass member
		assertEquals(1,ticketList.numVisits(33333));
		ticketList.addTicket("Last Chrismas", "NR", 3,12,"NONE","MoviePass",33333);
		assertEquals(2,ticketList.numVisits(33333));
		ticketList.addTicket("Frozen 2", "G", 6,14,"NONE","MoviePass",33333);
		assertEquals(3,ticketList.numVisits(33333));
		
	}

}