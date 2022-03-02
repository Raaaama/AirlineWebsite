package mysql.business;

import java.io.Serializable;

public class FlightData implements Serializable {
	private static final long serialVersionUID = 1L;
     
    private String apCode;
    private String city;
    private String country;
    private String time;
    private String fTime;    
	
    public FlightData(){ }
    public FlightData(String apCode, String city, String country, String time, String fTime){
    	this.apCode = apCode;
        this.city = city;
        this.country = country;
        this.time = time;
        this.fTime = time;
    }
	public String getApCode() {
		return apCode;
	}
	public void setApCode(String apCode) {
		this.apCode = apCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getfTime() {
		return fTime;
	}
	public void setfTime(String fTime) {
		this.fTime = fTime;
	}
}
