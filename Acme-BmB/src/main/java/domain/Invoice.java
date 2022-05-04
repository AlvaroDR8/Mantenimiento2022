package domain;

import java.sql.Date;

import javax.persistence.Entity;


@Entity
public class Invoice extends DomainEntity   {
	
	private Date momentIssued;
	private Long vatNumber;
	private String details;
	private double amount;
	private CreditCard creditCard;

	public Date getMomentIssued() {
		return momentIssued;
	}

	public void setMomentIssued(Date momentIssued) {
		this.momentIssued = momentIssued;
	}

	public Long getVatNumber() {
		return vatNumber;
	}

	public void setVatNumber(Long vatNumber) {
		this.vatNumber = vatNumber;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
}
