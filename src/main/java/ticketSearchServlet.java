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
 
@WebServlet("/search")
public class ticketSearchServlet extends HttpServlet {     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {    
		PrintWriter writer = response.getWriter();
		try {
			String fromCity = request.getParameter("fromCity");
			String toCity = request.getParameter("toCity");
			String whenTo = request.getParameter("whenTo");
			String psKol = request.getParameter("passengersKol");
			int passengersKol = Integer.parseInt(psKol.substring(0,1));
			int vzKol = Integer.parseInt(request.getParameter("vzKol"));
			int dKol = Integer.parseInt(request.getParameter("dKol"));
			int mlKol = Integer.parseInt(request.getParameter("mlKol"));
			String ticketType = request.getParameter("ticketType");		
			
			//get fromCity, toCity
			String[] fr = fromCity.split(" ");
			fromCity = fr[1].substring(0,fr[1].length() - 1);
			
			String[] to = toCity.split(" ");
			toCity = to[1].substring(0,to[1].length() - 1);
			
			//convert date to a weekday
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
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
			
			whenTo = new SimpleDateFormat("dd-MM-yyyy").format(date1);
			
			c.add(Calendar.DAY_OF_MONTH, -1);
			Date dateM = c.getTime();
			String dateMinus = new SimpleDateFormat("dd-MM-yyyy").format(dateM);
			
			c.add(Calendar.DAY_OF_MONTH, 2);
			Date dateP = c.getTime();
			String datePlus = new SimpleDateFormat("dd-MM-yyyy").format(dateP);
			
			int dayOfWeek2 = -1;
			
			//avTickets
			ArrayList<AvailableTicket> avTickets = MyDB.getAvTickets(fr[0], to[0], dayOfWeek1, ticketType, whenTo);
			
			//peredacha
			HttpSession session = request.getSession();
			session.setAttribute("fromCity", fromCity);
			session.setAttribute("toCity", toCity);
			
			session.setAttribute("whenTo1", dateMinus);
			session.setAttribute("whenTo2", whenTo);
			session.setAttribute("whenTo3", datePlus);
			
			session.setAttribute("passengersKol", passengersKol);
			session.setAttribute("vzKol", vzKol);
			session.setAttribute("dKol", dKol);
			session.setAttribute("mlKol", mlKol);
			session.setAttribute("ticketType", ticketType);	
			
	        session.setAttribute("avTickets", avTickets);
            getServletContext().getRequestDispatcher("/searchFrom.jsp").forward(request, response); 
			
        }
        catch(Exception ex) {
            writer.println(ex);
        }
    }
}