package com.radianbroker.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "email"), })
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "user_name", nullable = true)
	private String userName;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "email_hide", nullable = false, columnDefinition = "boolean default false")
	private boolean emailHide;

	@Column(name = "password_hash", nullable = false)
	private String passwordHash;

	@Column(name = "phone_number", nullable = true)
	private String phoneNumber;

	@Column(name = "phone_number_hide", nullable = false, columnDefinition = "boolean default false")
	private boolean phoneNumberHide;

	@Column(name = "registration_date", columnDefinition = "TIMESTAMP", nullable = true)
	private LocalDateTime registrationDate;

	@Column(name = "last_login_date", columnDefinition = "TIMESTAMP", nullable = true)
	private LocalDateTime lastLoginDate;

	@Column(name = "status", columnDefinition = "boolean default false", nullable = false)
	private boolean status;

	@Column(name = "status_change_date", columnDefinition = "TIMESTAMP", nullable = true)
	LocalDateTime statusChangeDate;

	@Column(name = "group_id_for_admin")
	private Long groupIdForAdmin;

	@Column(name = "company_name", nullable = true)
	private String companyName;

	@Column(name = "company_name_hide", nullable = false, columnDefinition = "boolean default false")
	private boolean companyNameHide;

	@Column(name = "abn", nullable = true)
	private String abn;

	@Column(name = "account_name", nullable = true)
	private String accountName;

	@Column(name = "bsb", nullable = true)
	private String bsb;

	@Column(name = "account_number", nullable = true)
	private String accountNumber;

	@Column(name = "photo_file_path", nullable = true)
	private String photoFilePath;

	@Column(name = "secret", nullable = true)
	private String secret;

	@Column(name = "biography", columnDefinition = "TEXT", nullable = true)
	private String biography;
	
	@Column(name = "address_line", nullable = true)
	private String addressLine;
	
	@Column(name = "post_code", nullable = true)
	private String postCode;
	
	@Column(name = "state", nullable = true)
	private String state;
	
	@Column(name = "suburb", nullable = true)
	private String suburb;

	@Column(name = "two_factor_auth_type",length = 32, columnDefinition = "ENUM('EMAIL', 'AUTHENTICATORAPP') default 'EMAIL'")
	@Enumerated(value = EnumType.STRING)
	private TwoFactorAuthType twoFactorAuthType = TwoFactorAuthType.EMAIL;

	public enum TwoFactorAuthType {
		EMAIL, AUTHENTICATORAPP
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEmailHide() {
		return emailHide;
	}

	public void setEmailHide(boolean emailHide) {
		this.emailHide = emailHide;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isPhoneNumberHide() {
		return phoneNumberHide;
	}

	public void setPhoneNumberHide(boolean phoneNumberHide) {
		this.phoneNumberHide = phoneNumberHide;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public LocalDateTime getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(LocalDateTime lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
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

	public Long getGroupIdForAdmin() {
		return groupIdForAdmin;
	}

	public void setGroupIdForAdmin(Long groupIdForAdmin) {
		this.groupIdForAdmin = groupIdForAdmin;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public boolean isCompanyNameHide() {
		return companyNameHide;
	}

	public void setCompanyNameHide(boolean companyNameHide) {
		this.companyNameHide = companyNameHide;
	}

	public String getAbn() {
		return abn;
	}

	public void setAbn(String abn) {
		this.abn = abn;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBsb() {
		return bsb;
	}

	public void setBsb(String bsb) {
		this.bsb = bsb;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getPhotoFilePath() {
		return photoFilePath;
	}

	public void setPhotoFilePath(String photoFilePath) {
		this.photoFilePath = photoFilePath;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public TwoFactorAuthType getTwoFactorAuthType() {
		return twoFactorAuthType;
	}

	public void setTwoFactorAuthType(TwoFactorAuthType twoFactorAuthType) {
		this.twoFactorAuthType = twoFactorAuthType;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}
	
}
