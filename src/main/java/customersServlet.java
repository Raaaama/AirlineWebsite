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
 
import mysql.business.CustomerData;
import mysql.business.MyDB;
 
@WebServlet("/customers")
public class customersServlet extends HttpServlet {     
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {       
    	    	   
    	PrintWriter writer = response.getWriter();
    	HttpSession session = request.getSession();
    	String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		MyDB mydb = new MyDB(username, password);
		boolean accountExists = mydb.checkAccount(username, password);    	              
        if (accountExists) {    		
        	ArrayList<CustomerData> customersData = MyDB.getCustomersData();
            request.setAttribute("customersData", customersData);       
            getServletContext().getRequestDispatcher("/customers.jsp").forward(request, response);            
    	}
        else {
        	response.sendRedirect("auth.html");
        }                   
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {       
    	    	   
    	PrintWriter writer = response.getWriter();
    	HttpSession session = request.getSession();
    	String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		MyDB mydb = new MyDB(username, password);
		boolean accountExists = mydb.checkAccount(username, password);    	              
        if (accountExists) {    		
        	ArrayList<CustomerData> customersData = MyDB.getCustomersData();
            request.setAttribute("customersData", customersData);       
            getServletContext().getRequestDispatcher("/customers.jsp").forward(request, response);            
    	}
        else {
        	response.sendRedirect("auth.html");
        }                   
    }
}