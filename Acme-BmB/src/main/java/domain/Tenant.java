package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Access(AccessType.PROPERTY)
@Entity
public class Tenant extends Actor {

	public Tenant() {
		super();
	}

	private CreditCard creditCard;

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	// Relationships

	private Collection<Request> requests;

	@OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL)
	public Collection<Request> getRequests() {
		return requests;
	}

	public void setRequests(Collection<Request> requests) {
		this.requests = requests;
	}

}
