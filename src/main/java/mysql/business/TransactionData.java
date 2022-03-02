package mysql.business;

public class TransactionData {
    public int id;
    public String trn;
	
    public TransactionData(int id, String trn) {
		this.id = id;
		this.trn = trn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTrn() {
		return trn;
	}

	public void setTrn(String trn) {
		this.trn = trn;
	}
    
}
