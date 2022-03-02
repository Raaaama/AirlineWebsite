package mysql.business;

public class FoundFlight {
	public int id;
	public String fromAp;
	public String fromCity;
	public String fromCountry;
	public String toAp;
	public String toCity;
	public String toCountry;
	public String depTime;
    public String arrTime;
    public int period;
	
	public FoundFlight(int id, String fromAp, String fromCity, String fromCountry, String toAp, String toCity,
			String toCountry, String depTime, String arrTime, int period) {
		super();
		this.id = id;
		this.fromAp = fromAp;
		this.fromCity = fromCity;
		this.fromCountry = fromCountry;
		this.toAp = toAp;
		this.toCity = toCity;
		this.toCountry = toCountry;
		this.depTime = depTime;
		this.arrTime = arrTime;
		this.period = period;
	}

	public String getFromAp() {
		return fromAp;
	}

	public void setFromAp(String fromAp) {
		this.fromAp = fromAp;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getFromCountry() {
		return fromCountry;
	}

	public void setFromCountry(String fromCountry) {
		this.fromCountry = fromCountry;
	}

	public String getToAp() {
		return toAp;
	}

	public void setToAp(String toAp) {
		this.toAp = toAp;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public String getToCountry() {
		return toCountry;
	}

	public void setToCountry(String toCountry) {
		this.toCountry = toCountry;
	}

	public String getDepTime() {
		return depTime;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}

	public String getArrTime() {
		return arrTime;
	}

	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}
    
    
}
