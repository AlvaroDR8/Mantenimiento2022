package domain;

import javax.persistence.Access;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.AccessType;

@Embeddable
@Access(AccessType.PROPERTY)
public class CreditCard{

	private String ownerName;
	private String cardNumber;
	private int cvv;
	private int caducityMonth;
	private int caducityYear;
	private String company;

	@NotBlank
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@NotNull
	public int getCaducityYear() {
		return caducityYear;
	}

	public void setCaducityYear(int caducityYear) {
		this.caducityYear = caducityYear;
	}

	@NotNull
	public int getCaducityMonth() {
		return caducityMonth;
	}

	public void setCaducityMonth(int caducityMonth) {
		this.caducityMonth = caducityMonth;
	}

	@NotNull
	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	@NotBlank
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@NotBlank
	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
}
