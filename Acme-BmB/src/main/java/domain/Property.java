package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Access(AccessType.PROPERTY)
@Entity
public class Property extends DomainEntity {
	
	public Property() {
		super();
	}
	
	private String name;
	private String description;
	private String address;
	private Integer rent;

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotBlank
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@NotNull
	public Integer getRent() {
		return rent;
	}

	public void setRent(Integer rent) {
		this.rent = rent;
	}
	
	// Relationships
	
	private Collection<Request> requests;
	private Collection<Audit> audits;
	private Collection<ValueAttributes> valueAttributes;
	private Lessor lessor;
	
	@OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
	public Collection<Request> getRequests() {
		return requests;
	}

	public void setRequests(Collection<Request> requests) {
		this.requests = requests;
	}

	@OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
	public Collection<Audit> getAudits() {
		return audits;
	}

	public void setAudits(Collection<Audit> audits) {
		this.audits = audits;
	}

	@OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
	public Collection<ValueAttributes> getValueAttributes() {
		return valueAttributes;
	}

	public void setValueAttributes(Collection<ValueAttributes> valueAttributes) {
		this.valueAttributes = valueAttributes;
	}

	@ManyToOne(optional = false)
	public Lessor getLessor() {
		return lessor;
	}

	public void setLessor(Lessor lessor) {
		this.lessor = lessor;
	}
}
