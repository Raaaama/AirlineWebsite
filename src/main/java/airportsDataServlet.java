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
 
import mysql.business.AirportsData;
import mysql.business.MyDB;
 
@WebServlet("/airports")
public class airportsDataServlet extends HttpServlet {     
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {       
    	    	   
    	PrintWriter writer = response.getWriter();
    	HttpSession session = request.getSession();
    	String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		MyDB mydb = new MyDB(username, password);
		boolean accountExists = mydb.checkAccount(username, password);    	              
        if (accountExists) {    		
        	ArrayList<AirportsData> AirportsData = MyDB.selectAirports();
        	ArrayList<AirportsData> deletedAirports = MyDB.selectDeletedAirports();
            request.setAttribute("AirportsData", AirportsData);      
            request.setAttribute("deletedAirports", deletedAirports);  
            getServletContext().getRequestDispatcher("/airports.jsp").forward(request, response);            
            //response.sendRedirect("/airports.jsp");
            //writer.println(AirportsData);
    	}
        else {
        	response.sendRedirect("auth.html");
        }                   
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {       
    	    	   
    	PrintWriter writer = response.getWriter();
    	HttpSession session = request.getSession();
    	String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		MyDB mydb = new MyDB(username, password);
		boolean accountExists = mydb.checkAccount(username, password);    	              
        if (accountExists) {    		
        	ArrayList<AirportsData> AirportsData = MyDB.selectAirports();
        	ArrayList<AirportsData> deletedAirports = MyDB.selectDeletedAirports();
            request.setAttribute("AirportsData", AirportsData);      
            request.setAttribute("deletedAirports", deletedAirports);  
            getServletContext().getRequestDispatcher("/airports.jsp").forward(request, response);            
            //response.sendRedirect("/airports.jsp");
            //writer.println(AirportsData);
    	}
        else {
        	response.sendRedirect("auth.html");
        }                   
    }
}