package mysql.business;

import java.io.Serializable;
import mysql.business.FlightData;
import mysql.business.AvailableFlightsData;
import java.util.ArrayList;

public class AvailableTicket implements Serializable {
	private static final long serialVersionUID = 1L;
     
    public int price;
    public ArrayList<FoundFlight> flData;
    
    public AvailableTicket() {}
    
    public AvailableTicket(int price, ArrayList<FoundFlight> flData) {
		this.price = price;
		ArrayList<FoundFlight> fld = new ArrayList<FoundFlight>();
		for (FoundFlight f : flData) {
			fld.add(f);
		}
		this.flData = fld;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public ArrayList<FoundFlight> getFlData() {
		return flData;
	}
	public void setFlData(ArrayList<FoundFlight> flData) {
		this.flData = flData;
	}
}
