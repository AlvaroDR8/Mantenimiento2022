package domain;

import java.sql.Date;

import javax.persistence.Entity;


@Entity
public class Audit extends DomainEntity   {
	
	private Date doneMoment;
	private int AttachmentNumber;

	public Date getDoneMoment() {
		return doneMoment;
	}

	public void setDoneMoment(Date doneMoment) {
		this.doneMoment = doneMoment;
	}

	public int getAttachmentNumber() {
		return AttachmentNumber;
	}

	public void setAttachmentNumber(int attachmentNumber) {
		AttachmentNumber = attachmentNumber;
	}
	
}
