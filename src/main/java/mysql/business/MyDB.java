package mysql.business;

import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
 
public class MyDB { 
    private static String url = "jdbc:mysql://localhost:3306/mydb";

    private static String username = "root";
    private static String password = "Nazamari123@";
    
    public MyDB(String username, String password){
    	MyDB.username = username;
    	MyDB.password = password;
    }
    
    public boolean checkAccount(String name, String pw) {	
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
	        try (Connection conn = DriverManager.getConnection(url, username, password)){	              	                   
	            String sql_request = "SELECT * FROM users WHERE username= '" + name + "' AND password = '" + pw + "'";
	            Statement statement = conn.createStatement();
	            ResultSet resultSet = statement.executeQuery(sql_request);	            
	            if (resultSet.next()) {
	            	return true;   	
	            }	
	        }
	    }
	    catch(Exception ex){
	        System.out.println(ex);
	    }
	    return false;
    }
      
    public static ArrayList<AvailableFlightsData> selectAvailableFlights() {
	    ArrayList<AvailableFlightsData> availableFlights = new ArrayList<AvailableFlightsData>();
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
	        try (Connection conn = DriverManager.getConnection(url, username, password)){	              
	            Statement statement = conn.createStatement();
	            ResultSet resultSet = statement.executeQuery("select a.id, b.apName, c.apName, a.ticketsNum, a.period, a.depTime, a.arrTime, a.deleted from availableFlights a inner join airports b on a.fromCity = b.apCode inner join airports c on a.toCity = c.apCode where a.deleted = 0;");
	            //ResultSet resultSet = statement.executeQuery("SELECT * FROM availableFlights WHERE deleted = 0");
	            while(resultSet.next()){                  
	                int id = resultSet.getInt(1);
	                String fromCity = resultSet.getString(2);
	                String toCity = resultSet.getString(3);                
	                int ticketsNum = resultSet.getInt(4);
	                int period = resultSet.getInt(5);
	                String depTime = resultSet.getString(6);
	                String arrTime = resultSet.getString(7);	                
	                AvailableFlightsData availableFlight = new AvailableFlightsData(id, fromCity, toCity, ticketsNum, period, depTime, arrTime);
	                availableFlights.add(availableFlight);
	            }
	        }
	    }
	    catch(Exception ex){
	        System.out.println(ex);
	    }
	    return availableFlights;
    }
    
    public static ArrayList<AvailableFlightsData> selectDeletedAvailableFlights() {
	    ArrayList<AvailableFlightsData> availableFlights = new ArrayList<AvailableFlightsData>();
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
	        try (Connection conn = DriverManager.getConnection(url, username, password)){	              
	            Statement statement = conn.createStatement();
	            ResultSet resultSet = statement.executeQuery("SELECT * FROM availableFlights WHERE deleted = 1");
	            while(resultSet.next()){                  
	                int id = resultSet.getInt(1);
	                String fromCity = resultSet.getString(2);
	                String toCity = resultSet.getString(3);                
	                int ticketsNum = resultSet.getInt(4);
	                int period = resultSet.getInt(5);
	                String depTime = resultSet.getString(6);
	                String arrTime = resultSet.getString(7); 
	                AvailableFlightsData availableFlight = new AvailableFlightsData(id, fromCity, toCity, ticketsNum, period, depTime, arrTime);
	                availableFlights.add(availableFlight);
	            }
	        }
	    }
	    catch(Exception ex){
	        System.out.println(ex);
	    }
	    return availableFlights;
    }
    
    public static ArrayList<AvailableFlightsData> applyFilter(String req) {
    	ArrayList<AvailableFlightsData> availableFlights = new ArrayList<AvailableFlightsData>();
    	try {
	        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();	        
	        try (Connection conn = DriverManager.getConnection(url, username, password)){	              
	            Statement statement = conn.createStatement();
	            ResultSet resultSet = statement.executeQuery(req);
	            while(resultSet.next()){                  
	                int id = resultSet.getInt(1);
	                String fromCity = resultSet.getString(2);
	                String toCity = resultSet.getString(3);                
	                int ticketsNum = resultSet.getInt(4);
	                int period = resultSet.getInt(5);
	                String depTime = resultSet.getString(6);
	                String arrTime = resultSet.getString(7); 
	                AvailableFlightsData availableFlight = new AvailableFlightsData(id, fromCity, toCity, ticketsNum, period, depTime, arrTime);
	                availableFlights.add(availableFlight);
	            }
	        }
	    }
	    catch(Exception ex){
	        System.out.println(ex);
	    }
    	return availableFlights;
    }
    
    public static AvailableFlightsData selectOne(int id) {        
    	AvailableFlightsData availableFlight = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){                  
                String sql = "SELECT * FROM availableFlights WHERE id=?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){ 
                    	int idAF = resultSet.getInt(1);
    	                String fromCity = resultSet.getString(2);
    	                String toCity = resultSet.getString(3);                
    	                int ticketsNum = resultSet.getInt(4);
    	                int period = resultSet.getInt(5);
    	                String depTime = resultSet.getString(6);
    	                String arrTime = resultSet.getString(7); 
    	                availableFlight = new AvailableFlightsData(idAF, fromCity, toCity, ticketsNum, period, depTime, arrTime);
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return availableFlight;
    }
    
    public static int insert(AvailableFlightsData availableFlight) {        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){     
            	Statement statement = conn.createStatement();
                String sql = "INSERT INTO availableflights (fromCity, toCity, ticketsNum, period, depTime, arrTime) values('"+availableFlight.getFromCity()+"','"+availableFlight.getToCity()+"',"+availableFlight.getTicketsNum()+","+availableFlight.getPeriod()+",'"+availableFlight.getDepTime()+"','"+availableFlight.getArrTime()+"')";
                statement.executeUpdate(sql);
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }
    
    public static int update(AvailableFlightsData availableFlight) {        
    	try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){                  
                String sql = "UPDATE availableflights SET fromCity = ?, toCity = ?, ticketsNum = ?, period = ?, depTime = ?, arrTime = ? WHERE id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, availableFlight.getFromCity());
                    preparedStatement.setString(2, availableFlight.getToCity());
                    preparedStatement.setInt(3, availableFlight.getTicketsNum());
                    preparedStatement.setInt(4, availableFlight.getPeriod());
                    preparedStatement.setString(5, availableFlight.getDepTime());    
                    preparedStatement.setString(6, availableFlight.getArrTime());      
                    preparedStatement.setInt(7, availableFlight.getId());
                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }
    
    public static int delete(int id) {
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){                                  
                String sql = "UPDATE availableFlights SET deleted = 1 WHERE id = ?";
                //addTransaction("available flight deleted");
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);                      
                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }
    
    public static int deleteCompletely(int id) {
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
            	
            	String sql = "SELECT * FROM availableFlights WHERE id = " + id + ";";	        	
	        	Statement statement = conn.createStatement();
	            ResultSet resultSet = statement.executeQuery(sql);				
	            while(resultSet.next()){       
	                int idf = resultSet.getInt(1);
	                String fromCity = resultSet.getString(2);
	                String toCity = resultSet.getString(3);                
	                int ticketsNum = resultSet.getInt(4);
	                int period = resultSet.getInt(5);
	                String depTime = resultSet.getString(6);
	                String arrTime = resultSet.getString(7);
	                
	                String newQuery = "INSERT INTO availableflights (fromCity, toCity, ticketsNum, period, depTime, arrTime, deleted) values('"+fromCity+"','"+toCity+"',"+ticketsNum+","+period+",'"+depTime+"','"+arrTime+"',0);";
	                
	                addTransaction(newQuery);
	            }
	            
                sql = "DELETE FROM availableFlights WHERE id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);                      
                    preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }
    
    public static int restoreAF(int id) {
    	try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){            
            	String sql = "update airports set deleted = 0 where apCode in (select fromCity from availableFlights where id = ?) or apCode in (select toCity from availableFlights where id = ?);";
            	try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    preparedStatement.setInt(2, id);  
                    preparedStatement.executeUpdate();
                }	
                sql = "UPDATE availableFlights SET deleted = 0 WHERE id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);                      
                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }
    
    public static ArrayList<AirportsData> selectAirports() {
	    ArrayList<AirportsData> airports = new ArrayList<AirportsData>();
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
	        try (Connection conn = DriverManager.getConnection(url, username, password)){	              
	            Statement statement = conn.createStatement();
	            ResultSet resultSet = statement.executeQuery("select a.id, b.CountryName, a.city, a.apName, a.apCode, a.deleted from airports a, countries b where a.idCountry = b.idCountry and a.deleted = 0 order by b.CountryName, a.city;");
	            while(resultSet.next()){         
	                int id = resultSet.getInt(1);
	                String country = resultSet.getString(2);
	                String city = resultSet.getString(3);                
	                String apName = resultSet.getString(4);
	                String apCode = resultSet.getString(5);	                	               
	                AirportsData airport = new AirportsData(id, country, city, apName, apCode);
	                airports.add(airport);
	            }
	        }
	    }
	    catch(Exception ex){
	        System.out.println(ex);
	    }
	    return airports;
    }
    
    public static ArrayList<AirportsData> selectDeletedAirports() {
	    ArrayList<AirportsData> airports = new ArrayList<AirportsData>();
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
	        try (Connection conn = DriverManager.getConnection(url, username, password)){	              
	            Statement statement = conn.createStatement();
	            ResultSet resultSet = statement.executeQuery("select a.id, b.CountryName, a.city, a.apName, a.apCode, a.deleted from airports a, countries b where a.idCountry = b.idCountry and a.deleted = 1;");
	            while(resultSet.next()){         
	                int id = resultSet.getInt(1);
	                String country = resultSet.getString(2);
	                String city = resultSet.getString(3);                
	                String apName = resultSet.getString(4);
	                String apCode = resultSet.getString(5);	                	               
	                AirportsData airport = new AirportsData(id, country, city, apName, apCode);
	                airports.add(airport);
	            }
	        }
	    }
	    catch(Exception ex){
	        System.out.println(ex);
	    }
	    return airports;
    }
      
    
    public static int deleteAirport(int id) {
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){                                  
                //System.out.println(id);
                String sql = "UPDATE availableflights SET deleted = 1 where fromCity in (select apCode from airports where id = ?) or toCity in (select apCode from airports where id = ?);";
                //addTransaction("availableFlights deleted");
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    preparedStatement.setInt(2, id);
                    preparedStatement.executeUpdate();
                }
                sql = "UPDATE airports SET deleted = 1 WHERE id = ?";
                //addTransaction("airport deleted");
                //System.out.println(id);
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);                      
                    return preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }
    
    public static int deleteCompletelyAP(int id) {        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){    
            	
            	String sql = "SELECT * FROM airports WHERE id = " + id + ";";	        	
	        	Statement statement = conn.createStatement();
	            ResultSet resultSet = statement.executeQuery(sql);				
	            while(resultSet.next()){       
	            	int temppppppp = resultSet.getInt(1);
	                int idCountry = resultSet.getInt(2);
	                String city = resultSet.getString(3);
	                String apName = resultSet.getString(4);
	                String apCode = resultSet.getString(5);
	                int deleted = resultSet.getInt(6);
	                
	                String newQuery = "INSERT INTO airports (idCountry, city, apName, apCode, deleted) values("+idCountry+",'"+city+"','"+apName+"','"+apCode+"',"+deleted+");";
	                
	                addTransaction(newQuery);
	            }
	            
            	sql = "select b.id from airports a, availableFlights b where (a.apCode = b.fromCity or a.apCode = b.toCity) and a.apCode = "+ id +";";
            	statement = conn.createStatement();
            	resultSet = statement.executeQuery("select b.id from airports a, availableFlights b where (a.apCode = b.fromCity or a.apCode = b.toCity) and a.id = "+ id +";");
	            while(resultSet.next()){
	            	int idAF = resultSet.getInt(1);
	            	deleteCompletely(idAF);
	            }
            	
            	/*sql = "delete from availableflights where fromCity in (select apCode from airports where id = ?) or toCity in (select apCode from airports where id = ?);";                
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);   
                    preparedStatement.setInt(2, id); 
                    preparedStatement.executeUpdate();                    
                }*/
                
                sql = "DELETE FROM airports WHERE id = ?";
                //System.out.println(id);
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }
    
    public static int restoreAirport(int id) {
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){                                  
                String sql = "UPDATE airports SET deleted = 0 WHERE id = ?";
                //System.out.println(id);
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);                      
                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }
    
    public static ArrayList<String> getFromFilters(ArrayList<AvailableFlightsData> availableFlights) {
    	ArrayList<String> fromFilters = new ArrayList<String>();
    	for (AvailableFlightsData city : availableFlights) { 	
    		String frm = city.getFromCity();
            if (!(fromFilters.contains(frm))) {
            	fromFilters.add(frm);
            }
    	}
    	return fromFilters;
    }
    
    public static ArrayList<String> getToFilters(ArrayList<AvailableFlightsData> availableFlights) {
    	ArrayList<String> toFilters = new ArrayList<String>();
    	for (AvailableFlightsData city : availableFlights) { 	
    		String frm = city.getToCity();
            if (!(toFilters.contains(frm))) {
            	toFilters.add(frm);  
            }
    	}
    	return toFilters;
    }
    
    public static ArrayList<String> applyFilterOptions(String req) {
    	ArrayList<String> options = new ArrayList<String>();
    	try {
	        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();	        
	        try (Connection conn = DriverManager.getConnection(url, username, password)){	              
	            Statement statement = conn.createStatement();
	            ResultSet resultSet = statement.executeQuery(req);
	            while(resultSet.next()){                  
	                String option = resultSet.getString(1);
	                options.add(option);
	            }
	        }
	    }
	    catch(Exception ex){
	        System.out.println(ex);
	    }
    	return options;
    }
    
    public static ArrayList<AvailableFlightsData> searchAF(String from, String to, int day1, int day2) {
    	ArrayList<AvailableFlightsData> availableFlights = new ArrayList<AvailableFlightsData>();
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
	        try (Connection conn = DriverManager.getConnection(url, username, password)){	              

	        	String sql = "SELECT * FROM availableFlights WHERE deleted = 0 AND ((fromCity = '" + from + "' OR toCity = '" + to + "') OR (fromCity = '" + to + "' OR toCity = '" + from + "')) AND (period = " + day1 + " OR period = " + day2 + ");";
	        	
	        	Statement statement = conn.createStatement();
	            ResultSet resultSet = statement.executeQuery(sql);
				
	            while(resultSet.next()){       
	                int id = resultSet.getInt(1);
	                String fromCity = resultSet.getString(2);
	                String toCity = resultSet.getString(3);                
	                int ticketsNum = resultSet.getInt(4);
	                int period = resultSet.getInt(5);
	                String depTime = resultSet.getString(6);
	                depTime = depTime.substring(0,depTime.length() - 3);
	                String arrTime = resultSet.getString(7);
	                arrTime = arrTime.substring(0,arrTime.length() - 3);
	                AvailableFlightsData availableFlight = new AvailableFlightsData(id, fromCity, toCity, ticketsNum, period, depTime, arrTime);
	                availableFlights.add(availableFlight);
	            }
	        }
	    }
	    catch(Exception ex){
	        System.out.println(ex);
	    }
	    return availableFlights;
    }
    
    public static ArrayList<FoundFlight> searchFromFlights(String from, int day1) {
    	ArrayList<FoundFlight> foundFlights = new ArrayList<FoundFlight>();
    	String d1 = Integer.toString(day1);
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
	        try (Connection conn = DriverManager.getConnection(url, username, password)){	              
	        	
	        	String sql = "select a.id, a.fromCity, b.city, d.countryName, a.toCity, c.city, e.countryName, a.depTime, a.arrTime, a.period from availableFlights a, airports b, airports c, countries d, countries e where (a.fromCity = '" + from + "') and (period = " + d1 + ") and a.fromCity = b.apCode and b.idCountry = d.idCountry and a.toCity = c.apCode and c.idCountry = e.idCountry and b.deleted = 0 and c.deleted = 0;";
	        	
	        	Statement statement = conn.createStatement();
	            ResultSet resultSet = statement.executeQuery(sql);
				
	            while(resultSet.next()){       
	            	int id = resultSet.getInt(1);
	                String fromAp = resultSet.getString(2);
	                String fromCity = resultSet.getString(3);
	                String fromCountry = resultSet.getString(4);
	                
	                String toAp = resultSet.getString(5);
	                String toCity = resultSet.getString(6);
	                String toCountry = resultSet.getString(7);
	                
	                String depTime = resultSet.getString(8);
	                depTime = depTime.substring(0,depTime.length() - 3);
	                String arrTime = resultSet.getString(9);
	                arrTime = arrTime.substring(0,arrTime.length() - 3);
	                
	                int period = resultSet.getInt(10);
	                
	                FoundFlight foundFlight = new FoundFlight(id, fromAp, fromCity, fromCountry, toAp, toCity, toCountry, depTime, arrTime, period);
	                foundFlights.add(foundFlight);
	            }
	        }
	    }
	    catch(Exception ex){
	        System.out.println(ex);
	    }
	    return foundFlights;
    }
    
    public static ArrayList<FoundFlight> searchToFlights(String to, int day2) {
    	ArrayList<FoundFlight> foundFlights = new ArrayList<FoundFlight>();
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
	        try (Connection conn = DriverManager.getConnection(url, username, password)){	              
	        		
	        	String d2 = Integer.toString(day2);
	        	String d22 = Integer.toString(day2 + 1);
	        	
	        	if (d2 == "0") {
	        		d22 = "0";
	        	}
	        	
	        	String sql = "select a.id, a.fromCity, b.city, d.countryName, a.toCity, c.city, e.countryName, a.depTime, a.arrTime, a.period from availableFlights a, airports b, airports c, countries d, countries e where (a.toCity = '" + to + "') and (period = "+ d2 +" or period = " + d22 + ") and a.fromCity = b.apCode and b.idCountry = d.idCountry and a.toCity = c.apCode and c.idCountry = e.idCountry and b.deleted = 0 and c.deleted = 0;";
	        	
	        	Statement statement = conn.createStatement();
	            ResultSet resultSet = statement.executeQuery(sql);
				
	            while(resultSet.next()){       
	            	int id = resultSet.getInt(1);
	                String fromAp = resultSet.getString(2);
	                String fromCity = resultSet.getString(3);
	                String fromCountry = resultSet.getString(4);
	                
	                String toAp = resultSet.getString(5);
	                String toCity = resultSet.getString(6);
	                String toCountry = resultSet.getString(7);
	                
	                String depTime = resultSet.getString(8);
	                depTime = depTime.substring(0,depTime.length() - 3);
	                String arrTime = resultSet.getString(9);
	                arrTime = arrTime.substring(0,arrTime.length() - 3);
	                
	                int period = resultSet.getInt(10);
	                
	                FoundFlight foundFlight = new FoundFlight(id,fromAp, fromCity, fromCountry, toAp, toCity, toCountry, depTime, arrTime, period);
	                foundFlights.add(foundFlight);
	            }
	        }
	    }
	    catch(Exception ex){
	        System.out.println(ex);
	    }
	    return foundFlights;
    }
    
    public static String getAp(String city) {
    	String ap = "";
    	try {
	        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
	        try (Connection conn = DriverManager.getConnection(url, username, password)){
	        	String sql = "select apCode from airports where city = '" + city + "';";
	        	Statement statement = conn.createStatement();
	            ResultSet resultSet = statement.executeQuery(sql);
	            while(resultSet.next()){       
	                ap = resultSet.getString(1);
	            }
	        }
	    }
	    catch(Exception ex){
	        System.out.println(ex);
	    }
    	return ap;
    }
    
    public static String getCountry(String city) {
    	String c = "";
    	try {
	        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
	        try (Connection conn = DriverManager.getConnection(url, username, password)){
	        	String sql = "select countryName from countries a, airports b where b.city = '" + city + "'and a.idCountry = b.idCountry;";
	        	Statement statement = conn.createStatement();
	            ResultSet resultSet = statement.executeQuery(sql);
	            while(resultSet.next()){       
	                c = resultSet.getString(1);
	            }
	        }
	    }
	    catch(Exception ex){
	        System.out.println(ex);
	    }
    	return c;
    }
    
    public static int getTicketPrice(ArrayList<FoundFlight> ticket, String ticketClass) {
        //peresadka = -15%
        //chas poleta = 45$
    	int tariff = 50;
    	if (ticketClass.equals("comfort")) {
    		tariff = 75;
    	}
    	else if (ticketClass.equals("business")) {
    		tariff = 100;
    	} 
    	else if (ticketClass.equals("firstClass")) {
    		tariff = 150;
    	}
    	double totalMinutes = 0;
    	for (FoundFlight ff : ticket) {
    		String dep = ff.depTime;
    		String[] temp1 = dep.split(":");
    		int hours1 = Integer.parseInt(temp1[0]);
    		int minutes1 = Integer.parseInt(temp1[1]);
    		
    		String arr = ff.arrTime;
    		String[] temp2 = arr.split(":");
    		int hours2 = Integer.parseInt(temp2[0]);
    		int minutes2 = Integer.parseInt(temp2[1]);
    		if (hours1 > hours2) {
    			hours2 = hours2 + 24;
    		}
    		
    		double minutes = ((hours2 * 60) + minutes2) - ((hours1 * 60) + minutes1);
    		totalMinutes = totalMinutes + minutes;
    	}
    	double price = (totalMinutes / 60) * tariff;
    	if (ticket.size() > 1) {
    		price = price * 0.85;
    	}
    	int p = (int) price;
    	return p;
    }
    
    public static boolean isFlightAvailable(String whenDate, int ticketClass, int flightId) {
    	String[] tempWhen = whenDate.split("-");
    	String when = tempWhen[2] + "-" + tempWhen[1] + "-" + tempWhen[0];
    	int count = 0;
    	int ticketsNum = 0;
    	try {
	        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
	        try (Connection conn = DriverManager.getConnection(url, username, password)){
	        	String sql = "select count(*) from flights a inner join tickets b on a.inTicket = b.id where b.dep ='" + when + "' and ticketClass = "+ticketClass+" and a.flightId = "+ flightId +";";
	        	Statement statement = conn.createStatement();
	            ResultSet resultSet = statement.executeQuery(sql);
	            while(resultSet.next()){       
	                count = resultSet.getInt(1);
	            }
	            sql = "select ticketsNum from availableFlights where id = "+flightId+";";
	            statement = conn.createStatement();
	            resultSet = statement.executeQuery(sql);
	            while(resultSet.next()){       
	                ticketsNum = resultSet.getInt(1);
	            }
	            
	            //whenDate.out.println(count);
	            //System.out.println(ticketsNum);
	            if (ticketClass == 0) {
	            	double temp = 0.45 * ticketsNum;
	            	int classKol = (int) temp; 
	            	if (count >= classKol) {
	            		return false;
	            	}
	            }
	            else if (ticketClass == 1) {
	            	double temp = 0.40 * ticketsNum;
	            	int classKol = (int) temp; 
	            	if (count >= classKol) {
	            		return false;
	            	}
	            }
	            else if (ticketClass == 2) {
	            	double temp = 0.1 * ticketsNum;
	            	int classKol = (int) temp; 
	            	if (count >= classKol) {
	            		return false;
	            	}
	            }
	            else if (ticketClass == 3) {
	            	double temp = 0.05 * ticketsNum;
	            	int classKol = (int) temp; 
	            	if (count >= classKol) {
	            		return false;
	            	}
	            }
	        }
	    }
	    catch(Exception ex){
	        System.out.println(ex);
	    }
    	return true;
    }
    
    public static ArrayList<AvailableTicket> getAvTickets(String fromAp, String toAp, int dayOfWeek1, String ticketClass, String when) {
    	ArrayList<FoundFlight> availableFromFlights = MyDB.searchFromFlights(fromAp, dayOfWeek1);
		ArrayList<FoundFlight> availableToFlights = MyDB.searchToFlights(toAp, dayOfWeek1);
		ArrayList<AvailableTicket> avTickets = new ArrayList<AvailableTicket>();
		int tClass = 0;
    	if (ticketClass.equals("comfort")) {
    		tClass = 1;
    	}
    	else if (ticketClass.equals("business")) {
    		tClass = 2;
    	} 
    	else if (ticketClass.equals("firstClass")) {
    		tClass = 3;
    	}
		for (FoundFlight a : availableFromFlights) {
			if (isFlightAvailable(when, tClass, a.id) == false) {
				continue;
			}
			ArrayList<FoundFlight> tempp = new ArrayList<FoundFlight>(); 
			tempp.add(a);
			boolean flag = false;
			for (FoundFlight b : availableToFlights) {
				if (isFlightAvailable(when, tClass, b.id) == false) {
					continue;
				}
				int comp = (a.arrTime).compareTo(b.depTime);
				if ((comp < 0) | (comp > 0 && a.period < b.period)) {
					if (a.toCity.equals(b.fromCity) && flag == true) {
						ArrayList<FoundFlight> temppp = new ArrayList<FoundFlight>();
						temppp.add(a);
						temppp.add(b);
						int price = getTicketPrice(temppp, ticketClass);
						AvailableTicket ticket = new AvailableTicket(price, temppp);
						if (!avTickets.contains(ticket)) {
							avTickets.add(ticket);
						}								
					}
					else {
						if (a.toCity.equals(b.fromCity)) {
			    			tempp.add(b);
			    			int price = getTicketPrice(tempp, ticketClass);
			    			AvailableTicket ticket = new AvailableTicket(price, tempp);
							avTickets.add(ticket);
			    			flag = true;
			    		}
					}
				}
			}
		}
		
		for (FoundFlight a : availableFromFlights) {
			if (a.toAp.equals(toAp)) {
				ArrayList<FoundFlight> tempp = new ArrayList<FoundFlight>();
				tempp.add(a);
				int price = getTicketPrice(tempp, ticketClass);
				AvailableTicket ticket = new AvailableTicket(price, tempp);
				avTickets.add(ticket);
			}
		}
    	return avTickets;
    }
    
    public static ArrayList<TransactionData> selectTransactions() {
    	ArrayList<TransactionData> transactions = new ArrayList<TransactionData>();
    	try {
	        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
	        try (Connection conn = DriverManager.getConnection(url, username, password)){
	        	String sql = "select * from mytransactions;";
	        	Statement statement = conn.createStatement();
	            ResultSet resultSet = statement.executeQuery(sql);
	            while(resultSet.next()){       
	            	int id = resultSet.getInt(1);
	            	String myquery = resultSet.getString(2);
	            	TransactionData temp = new TransactionData(id,myquery);
	            	transactions.add(temp);
	            }
	        }
	    }
	    catch(Exception ex){
	        System.out.println(ex);
	    }
    	return transactions;
    }
    
    public void deleteTransactions() {	
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
	        try (Connection conn = DriverManager.getConnection(url, username, password)){	              	                   
	        	String sql_request = "DELETE FROM mytransactions WHERE id > 0";
	        	Statement statement = conn.createStatement();                 
                statement.executeUpdate(sql_request);
	        }
	    }
	    catch(Exception ex){
	        System.out.println(ex);
	    }
    }
    
    public static void addTransaction(String trn) {
    	try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){     
                String sql = "insert into mytransactions(myquery) values(?);";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, trn);                      
                preparedStatement.executeUpdate();
                
                
                /*
                String sql_request = "DELETE FROM mytransactions WHERE id >= ?;";
                PreparedStatement preparedStatement = conn.prepareStatement(sql_request);
                preparedStatement.setInt(1, id);                      
                preparedStatement.executeUpdate();
                */
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    public static void transactionRollback(int id) {
    	try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
            	//conn.setAutoCommit(false);
            	//conn.rollback();
                
            	Statement statement = conn.createStatement();
	            ResultSet resultSet = statement.executeQuery("select * from mytransactions;");
	            int idt = 0;
	            ArrayList<String> queries = new ArrayList<String>();
	            ArrayList<Integer> ids = new ArrayList<Integer>();
	            while(resultSet.next() && idt < id) {
	            	idt = resultSet.getInt(1);
	            	String query = resultSet.getString(2);
	            	queries.add(query);
	            	ids.add(idt);
	            }
	            
	            for (String q : queries) {
	            	statement = conn.createStatement();
	                statement.executeUpdate(q);
	            }
	            
	            for (int idq : ids) {
	            	String sql = "DELETE FROM mytransactions WHERE id = ?";
	                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
	                    preparedStatement.setInt(1, idq);                      
	                    preparedStatement.executeUpdate();
	                }
	            }
            }
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public static int addCustomer(String name, String surname, String dateOfBirth, String citizenship, String psNum) {
    	try {
    		int passportNum = Integer.parseInt(psNum);
	        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
	        try (Connection conn = DriverManager.getConnection(url, username, password)){     
            	Statement statement = conn.createStatement();
                String sql = "INSERT INTO customers(name,surname,dateOfBirth,citizenship,passportNum) values('" +name+ "','"+surname+"','" + dateOfBirth + "','" + citizenship +"'," + passportNum + ");";
                statement.executeUpdate(sql);
            }
	        try (Connection conn = DriverManager.getConnection(url, username, password)){	              	                   
	            String sql_request = "SELECT id FROM customers ORDER BY id DESC LIMIT 1;";
	            Statement statement = conn.createStatement();
	            ResultSet resultSet = statement.executeQuery(sql_request);	            
	            if (resultSet.next()) {
	            	int id = resultSet.getInt(1);
	            	return id;
	            }
	        }
	    }
	    catch(Exception ex){
	        System.out.println(ex);
	    }
    	return 0;
    }
    
    public static int addTicket(int boughtBy, String depDate, int price, int ticketClass, int type) {
    	String[] temp = depDate.split("-");
    	String dep = temp[2] + "-" + temp[1] + "-" + temp[0];
    	try {
	        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
	        try (Connection conn = DriverManager.getConnection(url, username, password)){     
            	Statement statement = conn.createStatement();
                String sql = "INSERT INTO tickets(boughtBy,dep,price,ticketClass,type) values(" +boughtBy+ ",'"+dep+"'," + price + "," + ticketClass +"," + type + ");";
                statement.executeUpdate(sql);
	        }
	        try (Connection conn = DriverManager.getConnection(url, username, password)){	              	                   
	            String sql_request = "SELECT id FROM tickets ORDER BY id DESC LIMIT 1;";
	            Statement statement = conn.createStatement();
	            ResultSet resultSet = statement.executeQuery(sql_request);	            
	            if (resultSet.next()) {
	            	int id = resultSet.getInt(1);
	            	return id;
	            }
	        }
	    }
	    catch(Exception ex){
	        System.out.println(ex);
	    }
    	return 0;
    }
    
    public static void addFlight(int ticketId, int flightId) {
    	try {
	        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
	        try (Connection conn = DriverManager.getConnection(url, username, password)){     
            	Statement statement = conn.createStatement();
                String sql = "INSERT INTO flights(inTicket, flightId) values(" +ticketId+ ","+flightId+");";
                statement.executeUpdate(sql);
	        }
	    }
	    catch(Exception ex){
	        System.out.println(ex);
	    }
    }
    
    public static ArrayList<CustomerData> getCustomersData() {
	    ArrayList<CustomerData> customersData = new ArrayList<CustomerData>();
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
	        try (Connection conn = DriverManager.getConnection(url, username, password)){	              
	            Statement statement = conn.createStatement();
	            ResultSet resultSet = statement.executeQuery("select a.passportnum, a.name, a.surname, f.apName, g.apName, b.dep, b.price, e.classType, b.type from customers a inner join tickets b on a.id = b.boughtBy inner join flights c on c.inTicket = b.id inner join availableFlights d on d.id = c.flightId inner join class e on e.id = b.ticketClass inner join airports f on f.apCode = d.fromCity inner join airports g on g.apCode = d.toCity;");
	            while(resultSet.next()){      
	            	int passportNum = resultSet.getInt(1);
	            	String name = resultSet.getString(2);
	            	String surname = resultSet.getString(3);
	            	String from = resultSet.getString(4);
	            	String to = resultSet.getString(5);
	            	String dep = resultSet.getString(6);
	            	int price = resultSet.getInt(7);
	            	String classType = resultSet.getString(8);
	            	int type = resultSet.getInt(9);	                
	            	CustomerData customerData = new CustomerData(passportNum, name, surname, from, to, dep, price, classType, type);
	                customersData.add(customerData);
	            }
	        }
	    }
	    catch(Exception ex){
	        System.out.println(ex);
	    }
	    return customersData;
    }
    
}