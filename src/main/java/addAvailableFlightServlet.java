import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;

import mysql.business.AvailableFlightsData;
import mysql.business.MyDB;
 
 
@WebServlet("/addAvailableFlight")
public class addAvailableFlightServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {    	    	
    	PrintWriter writer = response.getWriter();
        try {        	        	
            String fromCity = request.getParameter("fromCity");
            String toCity = request.getParameter("toCity");
            int ticketsNum = Integer.parseInt(request.getParameter("ticketsNum"));
            int period = Integer.parseInt(request.getParameter("period"));
            String depTime = request.getParameter("depTime");
            String arrTime = request.getParameter("arrTime");
            AvailableFlightsData availableFlight = new AvailableFlightsData(0, fromCity, toCity, ticketsNum, period, depTime, arrTime);
            MyDB.insert(availableFlight);              
                                    
            request.getRequestDispatcher("/isAuthorised").forward(request, response);            
        }
        catch(Exception ex) {             
        	writer.println(ex);
        }
        finally {
        	writer.close();        	        	
        }
    }    
}