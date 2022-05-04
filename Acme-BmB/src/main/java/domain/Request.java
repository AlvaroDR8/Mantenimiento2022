package domain;

import java.sql.Date;

import javax.persistence.Entity;

@Entity
public class Request extends DomainEntity {

	private Date checkIn;
	private Date checkOut;
	private boolean smoker;
	private CreditCard creditCard;
	private RequestStatus status;
	private double lessorFee;

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public boolean isSmoker() {
		return smoker;
	}

	public void setSmoker(boolean smoker) {
		this.smoker = smoker;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public double getLessorFee() {
		return lessorFee;
	}

	public void setLessorFee(double lessorFee) {
		this.lessorFee = lessorFee;
	}
}
