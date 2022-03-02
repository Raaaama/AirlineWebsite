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
 
@WebServlet("/buyTicket")
public class buyTicketServlet extends HttpServlet {     
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {    
		PrintWriter writer = response.getWriter();
		try {
			HttpSession session = request.getSession();
			int psKol = (int)session.getAttribute("passengersKol");
			String dep = (String)session.getAttribute("when");
			AvailableTicket ticket = (AvailableTicket)session.getAttribute("chosenTicket");
			String ticketType = (String)session.getAttribute("ticketType");
			int price = ticket.price;
			int type = (int)session.getAttribute("type"); 
			String when = (String)session.getAttribute("when");
			for (int i = 1; i <= psKol; i++) {
				String name = request.getParameter("name" + i);
				String surname = request.getParameter("surname" + i);
				String dateOfBirth = request.getParameter("dateOfBirth" + i);
				String citizenship = request.getParameter("citizenship" + i);
				String passportNum = request.getParameter("passport" + i);
				
				int boughtBy = MyDB.addCustomer(name,surname,dateOfBirth,citizenship,passportNum);
				int ticketClass = 0;
				if (ticketType.equals("comfort")) {
					ticketClass = 1;
				}
				else if (ticketType.equals("business")) {
					ticketClass = 2;
				}
				else if (ticketType.equals("firstClass")) {
					ticketClass = 3;
				}
				int ticketId = MyDB.addTicket(boughtBy,dep,price,ticketClass,type);
				
				for (FoundFlight ff : ticket.flData) {
					MyDB.addFlight(ticketId, ff.id);
				}
			}
        	
			response.sendRedirect("index.jsp");
        	//getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);           
		}
        catch(Exception ex) {
            writer.println(ex);
        }
    }
}