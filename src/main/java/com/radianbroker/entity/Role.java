package com.radianbroker.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "roles", uniqueConstraints = { @UniqueConstraint(columnNames = "name")})
public class Role {

	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "status", columnDefinition = "boolean default false", nullable = false)
	private boolean status;

	@Column(name = "status_change_date", columnDefinition = "TIMESTAMP", nullable = true)
	LocalDateTime statusChangeDate;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
