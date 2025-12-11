package com.radianbroker.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ris", uniqueConstraints = { @UniqueConstraint(columnNames = "ris_code")})
public class Ris {

	@Id
	@Column(name = "ris_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long risId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "ris_code", nullable = false)
	private String risCode;

	@Column(name = "description", nullable = true)
	private String description;

	@Column(name = "status", columnDefinition = "boolean default false", nullable = false)
	private boolean status;

	@Column(name = "status_change_date", columnDefinition = "TIMESTAMP", nullable = true)
	LocalDateTime statusChangeDate;

	public Long getRisId() {
		return risId;
	}

	public void setRisId(Long risId) {
		this.risId = risId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRisCode() {
		return risCode;
	}

	public void setRisCode(String risCode) {
		this.risCode = risCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public LocalDateTime getStatusChangeDate() {
		return statusChangeDate;
	}

	public void setStatusChangeDate(LocalDateTime statusChangeDate) {
		this.statusChangeDate = statusChangeDate;
	}

}
