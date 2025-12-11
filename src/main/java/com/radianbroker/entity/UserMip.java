package com.radianbroker.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "user_mips")
public class UserMip {

	@Id
	@Column(name = "user_mip_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userMipId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mip_id")
	 @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	Mip mip;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	 @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	User user;

	@Column(name = "provider_number", nullable = true)
	private String providerNumber;

	@Column(name = "is_allowed", nullable = false, columnDefinition = "boolean default true")
	private boolean isAllowed;

	public Long getUserMipId() {
		return userMipId;
	}

	public void setUserMipId(Long userMipId) {
		this.userMipId = userMipId;
	}

	public Mip getMip() {
		return mip;
	}

	public void setMip(Mip mip) {
		this.mip = mip;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getProviderNumber() {
		return providerNumber;
	}

	public void setProviderNumber(String providerNumber) {
		this.providerNumber = providerNumber;
	}

	public boolean isAllowed() {
		return isAllowed;
	}

	public void setAllowed(boolean isAllowed) {
		this.isAllowed = isAllowed;
	}

}
