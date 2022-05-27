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
public class Attributes extends DomainEntity {

	public Attributes() {
		super();
	}

	private String name;

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Relationships
	private Collection<ValueAttributes> valueAttributes;
	
	@OneToMany(mappedBy = "attribute", cascade = CascadeType.ALL)
	public Collection<ValueAttributes> getValueAttributes() {
		return valueAttributes;
	}

	public void setValueAttributes(Collection<ValueAttributes> valueAttributes) {
		this.valueAttributes = valueAttributes;
	}


}
