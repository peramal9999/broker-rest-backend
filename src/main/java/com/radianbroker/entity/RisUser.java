package com.radianbroker.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ris_users")
public class RisUser {

	@Id
	@Column(name = "ris_user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long risUserId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ris_id")
	Ris ris;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	User user;

	@Column(name = "user_name", nullable = false)
	private String userName;

	@Column(name = "first_name_hl7", nullable = true)
	private String firstNameHl7;

	@Column(name = "last_name_hl7", nullable = true)
	private String lastNameHl7;

	@Column(name = "pacs_username", nullable = false)
	private String pacsUsername;

	public Long getRisUserId() {
		return risUserId;
	}

	public void setRisUserId(Long risUserId) {
		this.risUserId = risUserId;
	}

	public Ris getRis() {
		return ris;
	}

	public void setRis(Ris ris) {
		this.ris = ris;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstNameHl7() {
		return firstNameHl7;
	}

	public void setFirstNameHl7(String firstNameHl7) {
		this.firstNameHl7 = firstNameHl7;
	}

	public String getLastNameHl7() {
		return lastNameHl7;
	}

	public void setLastNameHl7(String lastNameHl7) {
		this.lastNameHl7 = lastNameHl7;
	}

	public String getPacsUsername() {
		return pacsUsername;
	}

	public void setPacsUsername(String pacsUsername) {
		this.pacsUsername = pacsUsername;
	}

}
