import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mysql.business.AirportsData;
import mysql.business.AvailableFlightsData;
import mysql.business.MyDB;
 
@WebServlet("/login")
public class getDataServlet extends HttpServlet {     
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {       
    	    	   	
    	HttpSession session = request.getSession();
    	String username = request.getParameter("username");
        String password = request.getParameter("password");
        session.setAttribute("username", username);
        session.setAttribute("password", password);
        MyDB mydb = new MyDB(username, password);
    	boolean accountExists = mydb.checkAccount(username, password);    	              
        if (accountExists) {
        	mydb.deleteTransactions();
    		//request.setAttribute("name",username);
        	ArrayList<AvailableFlightsData> availableFlights = MyDB.selectAvailableFlights();
        	ArrayList<AvailableFlightsData> deletedAvailableFlights = MyDB.selectDeletedAvailableFlights();
        	session.setAttribute("availableFlights", availableFlights);      
            request.setAttribute("deletedAvailableFlights", deletedAvailableFlights);  
            
            ArrayList<AirportsData> AirportsData = MyDB.selectAirports();
        	ArrayList<AirportsData> deletedAirports = MyDB.selectDeletedAirports();
            request.setAttribute("AirportsData", AirportsData);      
            request.setAttribute("deletedAirports", deletedAirports);
            
            ArrayList<String> fromFilters = MyDB.getFromFilters(availableFlights);
        	ArrayList<String> toFilters = MyDB.getToFilters(availableFlights);
        	
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