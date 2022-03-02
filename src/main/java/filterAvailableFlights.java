import java.io.FilterReader;
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
 
import mysql.business.AvailableFlightsData;
import mysql.business.AirportsData;
import mysql.business.MyDB;
import java.util.ArrayList;
 
@WebServlet("/filterAF")
public class filterAvailableFlights extends HttpServlet {     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {           
    	    	   	
    	HttpSession session = request.getSession();
    	String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		
		ArrayList<AvailableFlightsData> availableFlights = MyDB.selectAvailableFlights();
		
    	ArrayList<AvailableFlightsData> deletedAvailableFlights = MyDB.selectDeletedAvailableFlights();     
        request.setAttribute("deletedAvailableFlights", deletedAvailableFlights);  
        
        ArrayList<AirportsData> AirportsData = MyDB.selectAirports();
    	ArrayList<AirportsData> deletedAirports = MyDB.selectDeletedAirports();
        request.setAttribute("AirportsData", AirportsData);      
        request.setAttribute("deletedAirports", deletedAirports);
                
		
		PrintWriter writer = response.getWriter();
    	if (username == null) {
    		response.sendRedirect("auth.html");    		
    	}    	    
    	else {
    		MyDB mydb = new MyDB(username, password);
    		boolean accountExists = mydb.checkAccount(username, password);    	              
            if (accountExists) {
            	//String filterRequest = "SELECT * FROM availableFlights where deleted = 0";
            	String filterRequest = "select a.id, b.apName, c.apName, a.ticketsNum, a.period, a.depTime, a.arrTime, a.deleted from availableFlights a inner join airports b on a.fromCity = b.apCode inner join airports c on a.toCity = c.apCode where a.deleted = 0";
            	
            	String fromOptionsRequest = "select distinct a.apName from airports a inner join availableFlights b on a.apCode = b.fromCity and b.toCity in (select apCode from airports b where deleted = 0";
            	//String fromOptionsRequest = "SELECT DISTINCT fromCity FROM availableFlights where deleted = 0";
            	
            	String toOptionsRequest = "select distinct a.apName from airports a inner join availableFlights b on a.apCode = b.toCity and b.fromCity in (select apCode from airports b where deleted = 0";
            	//String toOptionsRequest = "SELECT DISTINCT toCity FROM availableFlights where deleted = 0";
            	
            	String fromCheckedFilters[] = request.getParameterValues("fromCheckBox");
            	if (fromCheckedFilters != null) {            		            		
            		if (fromCheckedFilters.length > 0) {
            			filterRequest = filterRequest + " and (";
            			
            			toOptionsRequest = toOptionsRequest + " and (" ;            			
            			
                		String temp;
                		for (int i = 0; i < fromCheckedFilters.length; i++) {
                			temp = "";
                			if (i > 0) {
                				temp = temp + " OR ";
                			}
                    		temp = temp + "b.apName = '" + fromCheckedFilters[i] + "'";
                    		filterRequest = filterRequest + temp;
                    		
                    		toOptionsRequest = toOptionsRequest + temp ;                			
                    	}
                		filterRequest = filterRequest + ")";         
                		
                		toOptionsRequest = toOptionsRequest + ")";
                	}  
            	}     
            	
            	String toCheckedFilters[] = request.getParameterValues("toCheckBox");
            	if (toCheckedFilters != null) {
            		if (toCheckedFilters.length > 0) {
            			filterRequest = filterRequest + " and (";
            			
            			fromOptionsRequest = fromOptionsRequest + " and (";
            			
                		String temp;
                		for (int i = 0; i < toCheckedFilters.length; i++) {
                			temp = "";
                			if (i > 0) {
                				temp = temp + " OR ";
                			}
                    		temp = temp + "b.apName = '" + toCheckedFilters[i] + "'";
                    		filterRequest = filterRequest + temp;
                    		
                    		fromOptionsRequest = fromOptionsRequest + temp;
                    	}
                		filterRequest = filterRequest + ")";
                		
                		fromOptionsRequest = fromOptionsRequest + ")";
                	}  
            	}                      
            	
                filterRequest = filterRequest + ";";
                
                fromOptionsRequest = fromOptionsRequest + ");";
                toOptionsRequest = toOptionsRequest + ");";
                
                System.out.println(filterRequest);
                System.out.println(fromOptionsRequest);
                System.out.println(toOptionsRequest);
                
            	
            	//writer.println(filterRequest);
            	availableFlights = MyDB.applyFilter(filterRequest);
            	session.setAttribute("availableFlights", availableFlights);
            	request.setAttribute("fromCheckedFilters", fromCheckedFilters);
            	request.setAttribute("toCheckedFilters", toCheckedFilters);
            	
            	/*
            	ArrayList<String> fromFilters = MyDB.getFromFilters(availableFlights);
            	ArrayList<String> toFilters = MyDB.getToFilters(availableFlights);
            	*/
            	
            	ArrayList<String> fromFilters = MyDB.applyFilterOptions(fromOptionsRequest);
            	ArrayList<String> toFilters = MyDB.applyFilterOptions(toOptionsRequest);
            	
            	request.setAttribute("fromFilters", fromFilters);
            	request.setAttribute("toFilters", toFilters);
            	
            	getServletContext().getRequestDispatcher("/adminPanel.jsp").forward(request, response);            
                response.sendRedirect("/adminPanel.jsp");  
        	}
            else {
            	response.sendRedirect("auth.html");
            }
    	}    
    }
}