package domain;

import javax.persistence.Entity;


@Entity
public class Auditor extends DomainEntity {

	private String companyName;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
