package domain;

import javax.persistence.Entity;


@Entity
public class ValueAttributes extends DomainEntity   {
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
