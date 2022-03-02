package mysql.business;

import java.io.Serializable;

public class AvailableFlightsData implements Serializable {
	private static final long serialVersionUID = 1L;
	 
	public int id;
    public String fromCity;
    public String toCity;
    public int ticketsNum;
    public int period;
    public String depTime;
    public String arrTime;
     
    public AvailableFlightsData(){ }
    public AvailableFlightsData(int id, String fromCity, String toCity, int ticketsNum, int period, String depTime, String arrTime){
    	this.id = id;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.ticketsNum = ticketsNum;
        this.period = period;
        this.depTime = depTime;
        this.arrTime = arrTime;
    }
     
    public int getId() {
        return id;
    }
     
    public String getFromCity() {
        return fromCity;
    }
 
    public String getToCity() {
        return toCity;
    }
    
    public int getTicketsNum() {
        return ticketsNum;
    }
    
    public int getPeriod() {
        return period;
    }
    
    public String getDepTime() {
        return depTime;
    }
    
    public String getArrTime() {
        return arrTime;
    }
    
    public void setFromCity(String fromCity) {
    	this.fromCity = fromCity;
    }
    
    public void setToCity(String toCity) {
    	this.toCity = toCity;
    }
    
    public void setTicketsNum(int ticketsNum) {
    	this.ticketsNum = ticketsNum;
    }
    
    public void setPeriod(int period) {
    	this.period = period;
    }
    
    public void setDepTime(String depTime) {
    	this.depTime = depTime;
    }

    public void setArrTime(String arrTime) {
    	this.arrTime = arrTime;
    }    
}
