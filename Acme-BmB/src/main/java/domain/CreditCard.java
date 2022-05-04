package domain;

public class CreditCard {

	private String ownerName;
	private Long cardNumber;
	private int cvv;
	private int caducityMonth;
	private int caducityDay;
	private String company;

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getCaducityDay() {
		return caducityDay;
	}

	public void setCaducityDay(int caducityDay) {
		this.caducityDay = caducityDay;
	}

	public int getCaducityMonth() {
		return caducityMonth;
	}

	public void setCaducityMonth(int caducityMonth) {
		this.caducityMonth = caducityMonth;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public Long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
}
