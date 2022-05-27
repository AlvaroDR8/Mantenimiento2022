package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Access(AccessType.PROPERTY)
@Entity
public class Lessor extends Actor {

	public Lessor() {
		super();
	}

	private CreditCard creditCard;

	
	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	// Relationship

	private Collection<Property> properties;

	@OneToMany(mappedBy = "lessor", cascade = CascadeType.ALL)
	public Collection<Property> getProperties() {
		return properties;
	}

	public void setProperties(Collection<Property> properties) {
		this.properties = properties;
	}

}
