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
 
 
@WebServlet("/deleteCompletelyAP")
public class deleteCompletelyAP extends HttpServlet {    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {         
    	PrintWriter writer = response.getWriter();
        try {
            int id = Integer.parseInt(request.getParameter("id"));          
            MyDB.deleteCompletelyAP(id);
            request.getRequestDispatcher("/airports").forward(request, response);
        }
        catch(Exception ex) {
            writer.println(ex);
        }
    }
}