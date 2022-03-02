package mysql.business;

public class CustomerData {
	public int passportNum;
	public String name;
	public String surname;
	public String from;
	public String to;
	public String dep;
	public int price;
	public String classType;
	public int type;
	
	public int getPassportNum() {
		return passportNum;
	}

	public void setPassportNum(int passportNum) {
		this.passportNum = passportNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public CustomerData(int passportNum, String name, String surname, String from, String to, String dep, int price,
			String classType, int type) {
		super();
		this.passportNum = passportNum;
		this.name = name;
		this.surname = surname;
		this.from = from;
		this.to = to;
		this.dep = dep;
		this.price = price;
		this.classType = classType;
		this.type = type;
	}
}
