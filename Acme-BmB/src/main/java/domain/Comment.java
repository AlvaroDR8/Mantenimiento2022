package domain;

import java.sql.Date;

import javax.persistence.Entity;

@Entity
public class Comment extends DomainEntity {

	private String title;
	private String text;
	private int star;
	private Date momentPosted;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public Date getMomentPosted() {
		return momentPosted;
	}

	public void setMomentPosted(Date momentPosted) {
		this.momentPosted = momentPosted;
	}
}
