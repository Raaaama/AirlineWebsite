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
 
import mysql.business.TransactionData;
import mysql.business.MyDB;
 
@WebServlet("/myTransactions")
public class myTransactions extends HttpServlet {     
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {       
    	    	   
    	
    	HttpSession session = request.getSession();
    	String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		MyDB mydb = new MyDB(username, password);
		boolean accountExists = mydb.checkAccount(username, password);    	              
        if (accountExists) {    		
        	ArrayList<TransactionData> transactionsData = MyDB.selectTransactions(); 
        	session.setAttribute("transactionsData", transactionsData);      
            getServletContext().getRequestDispatcher("/transactions.jsp").forward(request, response);            
    	}
        else {
        	response.sendRedirect("auth.html");
        }                   
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {       
    	    	   
    	
    	HttpSession session = request.getSession();
    	String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		MyDB mydb = new MyDB(username, password);
		boolean accountExists = mydb.checkAccount(username, password);    	              
        if (accountExists) {    		
        	ArrayList<TransactionData> transactionsData = MyDB.selectTransactions();
        	session.setAttribute("transactionsData", transactionsData);      
            getServletContext().getRequestDispatcher("/transactions.jsp").forward(request, response);         
    	}
        else {
        	response.sendRedirect("auth.html");
        }                   
    }
}