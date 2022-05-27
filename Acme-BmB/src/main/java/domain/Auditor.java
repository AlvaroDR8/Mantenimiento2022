package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

@Access(AccessType.PROPERTY)
@Entity
public class Auditor extends Actor {

	public Auditor() {
		super();
	}

	private String companyName;

	@NotBlank
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	// Relationships

	private Collection<Audit> audits;

	@OneToMany(mappedBy = "auditor", cascade = CascadeType.ALL)
	public Collection<Audit> getAudits() {
		return audits;
	}

	public void setAudits(Collection<Audit> audits) {
		this.audits = audits;
	}

}
