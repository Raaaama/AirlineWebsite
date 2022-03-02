package mysql.business;

import java.io.Serializable;

public class AirportsData implements Serializable {
	public static final long serialVersionUID = 1L;
    
    private int id;
    public String country;
    public String city;
    public String apName;
    public String apCode;
     
    public AirportsData(){ }
    public AirportsData(int id, String country, String city, String apName, String apCode){
    	this.id = id;
        this.country = country;
        this.city = city;
        this.apName = apName;
        this.apCode = apCode;
    }
     
    public int getId() {
        return id;
    }
     
    public String getCountry() {
        return country;
    }
 
    public String getcity() {
        return city;
    }
    
    public String getApName() {
        return apName;
    }
    
    public String getApCode() {
        return apCode;
    }
    
    
    public void setCountry(String country) {
    	this.country = country;
    }
    
    public void setCity(String city) {
    	this.city = city;
    }
    
    public void setApName(String apName) {
    	this.apName = apName;
    }
    
    public void setApCode(String apCode) {
    	this.apCode = apCode;
    }     
}
