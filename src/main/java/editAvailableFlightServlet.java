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
 

@WebServlet("/editAvailableFlight")
public class editAvailableFlightServlet extends HttpServlet { 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException { 
    	PrintWriter writer = response.getWriter();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            AvailableFlightsData availableFlight = MyDB.selectOne(id);
            request.setAttribute("availableFlight", availableFlight);
            if(availableFlight!=null) {               
                ArrayList<AvailableFlightsData> availableFlights = MyDB.selectAvailableFlights();
                ArrayList<AvailableFlightsData> deletedAvailableFlights = MyDB.selectDeletedAvailableFlights();                
                request.setAttribute("availableFlights", availableFlights);      
                request.setAttribute("deletedAvailableFlights", deletedAvailableFlights);                                                   
                getServletContext().getRequestDispatcher("/editAvailableFlight.jsp").forward(request, response);
                response.sendRedirect("/editAvailableFlight.jsp");
            }
            else {            	
                writer.println("error");
            }
        }
        catch(Exception ex) {
        	writer.println(ex);
        }
    }
     
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    	PrintWriter writer = response.getWriter();
        try {
        	int id = Integer.parseInt(request.getParameter("id"));
        	String fromCity = request.getParameter("fromCity");
            String toCity = request.getParameter("toCity");
            int ticketsNum = Integer.parseInt(request.getParameter("ticketsNum"));
            int period = Integer.parseInt(request.getParameter("period"));
            String depTime = request.getParameter("depTime");
            String arrTime = request.getParameter("arrTime");
            AvailableFlightsData availableFlight = new AvailableFlightsData(id, fromCity, toCity, ticketsNum, period, depTime, arrTime);            
            MyDB.update(availableFlight);
            //response.sendRedirect(request.getContextPath() + "/adminPanel.jsp");
            request.getRequestDispatcher("/isAuthorised").forward(request, response);
        }
        catch(Exception ex) {       
        	writer.println(ex);
        }
    }
}