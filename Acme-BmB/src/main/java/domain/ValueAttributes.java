package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Access(AccessType.PROPERTY)
@Entity
public class ValueAttributes extends DomainEntity   {
	
	public ValueAttributes() {
		super();
	}
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	// RELATIONSHIPS

	private Property property;
	private Attributes attribute;

	@ManyToOne(optional = false)
	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	@ManyToOne(optional = false)
	public Attributes getAttribute() {
		return attribute;
	}

	public void setAttribute(Attributes attribute) {
		this.attribute = attribute;
	}
}
