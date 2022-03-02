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
 
@WebServlet("/changeDate")
public class changeDate extends HttpServlet {     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {    
		PrintWriter writer = response.getWriter();
		try {
			//poluchenie
			HttpSession session = request.getSession();
			String fromCity = (String) session.getAttribute("fromCity");
			String toCity = (String) session.getAttribute("toCity");
			String whenTo = request.getParameter("id");
			//String whenBack = (String) session.getAttribute("whenBack");
			int psKol = (int)session.getAttribute("passengersKol");
			int vzKol = (int)session.getAttribute("vzKol");
			int dKol = (int)session.getAttribute("dKol");
			int mlKol = (int)session.getAttribute("mlKol");
			
			String ticketType = (String)session.getAttribute("ticketType");
			
			String fromAp = MyDB.getAp(fromCity);
			String fromCountry = MyDB.getCountry(fromCity);
			String toAp = MyDB.getAp(toCity);
			String toCountry = MyDB.getCountry(toCity);
			
			//get fromCity, toCity

			
			//convert date to a weekday
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Calendar c = Calendar.getInstance();
			
			Date date1 = formatter.parse(whenTo);
			//CHECK IF DATE HAS PASSED
			Date now = new Date();  
		    if (now.after(date1)) {
		    	response.sendRedirect("index.jsp");
		    }
		    //
			c.setTime(date1);
			int dayOfWeek1 = c.get(Calendar.DAY_OF_WEEK) - 1;
			
			whenTo = new SimpleDateFormat("yyyy-MM-dd").format(date1);
			
			c.add(Calendar.DAY_OF_MONTH, -1);
			Date dateM = c.getTime();
			String dateMinus = new SimpleDateFormat("yyyy-MM-dd").format(dateM);
			
			c.add(Calendar.DAY_OF_MONTH, 2);
			Date dateP = c.getTime();
			String datePlus = new SimpleDateFormat("yyyy-MM-dd").format(dateP);
			
			int dayOfWeek2 = -1;
			/*
			if (whenBack != "") {
				Date date2 = formatter.parse(whenBack);
				c.setTime(date2);
				dayOfWeek2 = c.get(Calendar.DAY_OF_WEEK) - 1;
			}*/
			
			//avTickets
			//ArrayList<AvailableTicket> avTickets = MyDB.getAvTickets(fromAp, toAp, dayOfWeek1, ticketType, whenTo);
			
			//peredacha
			/*
			session.setAttribute("fromCity", fromCity);
			session.setAttribute("toCity", toCity);
			
			//session.setAttribute("whenBack", whenBack);
			
			session.setAttribute("whenTo1", dateMinus);
			session.setAttribute("whenTo2", whenTo);
			session.setAttribute("whenTo3", datePlus);
			
			session.setAttribute("vzKol", vzKol);
			session.setAttribute("dKol", dKol);
			session.setAttribute("mlKol", mlKol);
			session.setAttribute("ticketType", ticketType);	
			*/
	        //session.setAttribute("avTickets", avTickets);

			
			String url = "search?fromCity=" + fromAp + "+" + fromCity + "%2C+" + fromCountry + "+&toCity=" + toAp + "+" + toCity + "%2C+" + toCountry + "+&whenTo=" + whenTo + "&passengersKol=" + psKol + "+passenger&vzKol=" + vzKol + "&dKol=" + dKol + "&mlKol=" + mlKol + "&ticketType=" + ticketType;
			//System.out.println(url);
			//http://localhost:12446/ramavia/search?fromCity=NQZ+Nur-Sultan%2C+Kazakhstan+&toCity=GYD+Baku%2C+Azerbaijan+&whenTo=2021-12-21&passengersCol=1+passenger&vzKol=1&dKol=0&mlKol=0&ticketType=econom
            //getServletContext().getRequestDispatcher(url).forward(request, response); 
            response.sendRedirect(url);
			
        }
        catch(Exception ex) {
            writer.println(ex);
        }
    }
}