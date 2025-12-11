package com.radianbroker.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "patients")
public class Patient {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ris_id", nullable = false)
	private Long risId;

	@Column(name = "pid", nullable = false)
	private String pid;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = true)
	private String lastName;

	@Column(name = "dob", columnDefinition = "DATE")
	private LocalDate dob;

	@Column(name = "sex", nullable = false)
	private String sex;
	
	@Column(name = "contact_numbers", nullable = true)
	private String contactNumbers;

	@Column(name = "updated_by_msg_id", nullable = false)
	private Long updatedByMsgId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRisId() {
		return risId;
	}

	public void setRisId(Long risId) {
		this.risId = risId;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getContactNumbers() {
		return contactNumbers;
	}

	public void setContactNumbers(String contactNumbers) {
		this.contactNumbers = contactNumbers;
	}

	public Long getUpdatedByMsgId() {
		return updatedByMsgId;
	}

	public void setUpdatedByMsgId(Long updatedByMsgId) {
		this.updatedByMsgId = updatedByMsgId;
	}

}
