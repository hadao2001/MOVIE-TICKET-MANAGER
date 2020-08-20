

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MovieTicketManagerTestPublic {
	private MovieTicketManager ticketList;
	

	@Before
	public void setUp() throws Exception {
		ticketList = new MovieTicketManager();
		
		//add adults
		ticketList.addTicket("Infinity Wars", "PG13", 4,19,"NONE","Adult",0);
	}

	@After
	public void tearDown() throws Exception {
		ticketList=null;
	}


	/**
	 * Test adding tickets of the "adult" type of tickets
	 */
	@Test
	public void testAddTicket() {
		MovieTicketManager tickets = new MovieTicketManager();
		//adult ticket
		assertEquals(14.80,tickets.addTicket("Infinity Wars", "PG13", 5,19,"NONE","Adult",0),.01);
	}

	/**
	 * Test the total of tickets sales for the month
	 */
	@Test
	public void testTotalSalesMonth() {
		assertEquals(14.796,ticketList.totalSalesMonth(),.1);
		
	}

}