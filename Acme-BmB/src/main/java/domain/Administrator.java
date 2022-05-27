package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;


@Access(AccessType.PROPERTY)
@Entity
public class Administrator extends Actor{
	
	public Administrator() {
		super();
	}
	

}
