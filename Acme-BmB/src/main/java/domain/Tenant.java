package domain;

import javax.persistence.Entity;


@Entity
public class Tenant extends DomainEntity   {
	
	CreditCard creditCard;
	
}
