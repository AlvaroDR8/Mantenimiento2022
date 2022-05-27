package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Access(AccessType.PROPERTY)
@Entity
public class Audit extends DomainEntity {

	public Audit() {
		super();
	}
	
	private Date doneMoment;
	private int AttachmentNumber;
	private boolean finalVersion;
	private String initialText;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
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

	@NotNull
	public boolean isFinalVersion() {
		return finalVersion;
	}

	public void setFinalVersion(boolean finalVersion) {
		this.finalVersion = finalVersion;
	}
	
	@NotBlank
	public String getInitialText() {
		return initialText;
	}

	public void setInitialText(String initialText) {
		this.initialText = initialText;
	}
	// RELATIONSHIPS

	private Property property;
	private Auditor auditor;

	@ManyToOne(optional = false)
	public Auditor getAuditor() {
		return auditor;
	}

	public void setAuditor(Auditor auditor) {
		this.auditor = auditor;
	}

	@ManyToOne(optional = false)
	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

}
