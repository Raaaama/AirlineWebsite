import java.io.IOException;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
 
import mysql.business.AvailableFlightsData;
import mysql.business.AirportsData;
import mysql.business.AvailableTicket;
import mysql.business.FlightData;
import mysql.business.FoundFlight;
import mysql.business.MyDB;
import java.util.ArrayList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
@WebServlet("/checkout")
public class checkoutServlet extends HttpServlet {     
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {    
		PrintWriter writer = response.getWriter();
		try {
			HttpSession session = request.getSession();
			String fromCity = (String)session.getAttribute("fromCity");
			String toCity = (String)session.getAttribute("toCity");
			String when = (String)session.getAttribute("whenTo2");
			String chosen = request.getParameter("chosen");
			int vzKol = (int)session.getAttribute("vzKol");
			int dKol = (int)session.getAttribute("dKol");
			int mlKol = (int)session.getAttribute("mlKol");
			String ticketType = (String)session.getAttribute("ticketType");	
			String[] temp = chosen.split(" ");
			int type =  Integer.parseInt(temp[0]);
			int ticketNum =  Integer.parseInt(temp[1]);
			ArrayList<AvailableTicket> avTickets = (ArrayList<AvailableTicket>) session.getAttribute("avTickets");
			AvailableTicket chosenTicket = avTickets.get(ticketNum);
			int price = chosenTicket.price;
			
			double tempPrice;
			if (type == 1) {
				tempPrice = price * 1.10;
				price = (int)tempPrice;
			}
			else if (type == 2) {
				tempPrice = price * 1.20;
				price = (int)tempPrice;
			}
			
			request.setAttribute("fromCity", fromCity);
        	request.setAttribute("toCity", toCity);
        	session.setAttribute("when", when);
        	request.setAttribute("price", price);
        	session.setAttribute("vzKol", vzKol);
        	session.setAttribute("dKol", dKol);
        	session.setAttribute("ml", mlKol);
        	session.setAttribute("chosenTicket", chosenTicket);
        	session.setAttribute("type", type);
        	request.setAttribute("ticketType", ticketType);
        	
        	getServletContext().getRequestDispatcher("/checkout.jsp").forward(request, response);           
		}
        catch(Exception ex) {
            writer.println(ex);
        }
    }
}