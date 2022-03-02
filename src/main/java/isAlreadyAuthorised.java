import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import mysql.business.AvailableFlightsData;
import mysql.business.AirportsData;
import mysql.business.MyDB;
 
@WebServlet("/isAuthorised")
public class isAlreadyAuthorised extends HttpServlet {     
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {       
    	    	   	
    	HttpSession session = request.getSession();
    	String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
    	if (username == null) {
    		response.sendRedirect("auth.html");    		
    	}    	    
    	else {
    		MyDB mydb = new MyDB(username, password);
    		boolean accountExists = mydb.checkAccount(username, password);    	              
            if (accountExists) {        		
            	ArrayList<AvailableFlightsData> availableFlights = mydb.selectAvailableFlights();
            	ArrayList<AvailableFlightsData> deletedAvailableFlights = mydb.selectDeletedAvailableFlights();
            	session.setAttribute("availableFlights", availableFlights);                         
                request.setAttribute("deletedAvailableFlights", deletedAvailableFlights);  

                ArrayList<AirportsData> AirportsData = mydb.selectAirports();
            	ArrayList<AirportsData> deletedAirports = mydb.selectDeletedAirports();
                request.setAttribute("AirportsData", AirportsData);      
                request.setAttribute("deletedAirports", deletedAirports);  
                
                ArrayList<String> fromFilters = mydb.getFromFilters(availableFlights);
            	ArrayList<String> toFilters = mydb.getToFilters(availableFlights);
            	
            	request.setAttribute("fromFilters", fromFilters);
            	request.setAttribute("toFilters", toFilters);
                
                getServletContext().getRequestDispatcher("/adminPanel.jsp").forward(request, response);            
                //response.sendRedirect("/adminPanel.jsp");
        	}
            else {
            	response.sendRedirect("auth.html");
            }
    	}    
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {       
    	    	   	
    	HttpSession session = request.getSession();
    	String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
    	if (username == null) {
    		response.sendRedirect("auth.html");    		
    	}    	    
    	else {
    		MyDB mydb = new MyDB(username, password);
    		boolean accountExists = mydb.checkAccount(username, password);    	              
            if (accountExists) {        		
            	ArrayList<AvailableFlightsData> availableFlights = mydb.selectAvailableFlights();
            	ArrayList<AvailableFlightsData> deletedAvailableFlights = mydb.selectDeletedAvailableFlights();
            	session.setAttribute("availableFlights", availableFlights);                         
                request.setAttribute("deletedAvailableFlights", deletedAvailableFlights);  
                
                ArrayList<AirportsData> AirportsData = mydb.selectAirports();
            	ArrayList<AirportsData> deletedAirports = mydb.selectDeletedAirports();
                request.setAttribute("AirportsData", AirportsData);      
                request.setAttribute("deletedAirports", deletedAirports);
                
                ArrayList<String> fromFilters = mydb.getFromFilters(availableFlights);
            	ArrayList<String> toFilters = mydb.getToFilters(availableFlights);
            	
            	request.setAttribute("fromFilters", fromFilters);
            	request.setAttribute("toFilters", toFilters);
                
                getServletContext().getRequestDispatcher("/adminPanel.jsp").forward(request, response);            
                //response.sendRedirect("/adminPanel.jsp");
        	}
            else {
            	response.sendRedirect("auth.html");
            }
    	}    
    }
}