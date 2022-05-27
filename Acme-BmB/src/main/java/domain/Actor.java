package domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import security.UserAccount;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;

@Access(AccessType.PROPERTY)
@Entity
public abstract class Actor extends DomainEntity {

	private String name;
	private String surname;
	private String email;
	private String photo;
	private String phone;

	public Actor() {
		super();
	}

	@NotBlank
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Email
	@NotBlank
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotBlank
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Relationships

	private Collection<SocialIdentity> socialIdentities;
	private Collection<Comment> comments;
	private UserAccount userAccount;

	@OneToMany(mappedBy = "actor", cascade = CascadeType.ALL)
	public Collection<SocialIdentity> getSocialIdentities() {
		return socialIdentities;
	}

	public void setSocialIdentities(Collection<SocialIdentity> socialIdentities) {
		this.socialIdentities = socialIdentities;
	}

	@OneToMany(mappedBy = "actor", cascade = CascadeType.ALL)
	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}



	
	
	

}
