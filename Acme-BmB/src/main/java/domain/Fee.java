package domain;

import java.sql.Date;

import javax.persistence.Entity;

@Entity
public class Fee extends DomainEntity {

	private Date lessorFeeDate;
	private double valueFee;

	public Date getLessorFeeDate() {
		return lessorFeeDate;
	}

	public void setLessorFeeDate(Date lessorFeeDate) {
		this.lessorFeeDate = lessorFeeDate;
	}

	public double getValueFee() {
		return valueFee;
	}

	public void setValueFee(double valueFee) {
		this.valueFee = valueFee;
	}

}
