package com.radianbroker.payload.request;

import com.radianbroker.entity.User.TwoFactorAuthType;

public class UpdateUserProfile {

	private String firstName;
	private String lastName;
	private String userName;
	private boolean emailHide;
	private String accountName;
	private String accountNumber;
	private String companyName;
	private boolean companyNameHide;
	private String phoneNumber;
	private boolean phoneNumberHide;
	private String abn;
	private String bsb;
	private String biography;
	private TwoFactorAuthType twoFactorAuthType;

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

	public boolean isEmailHide() {
		return emailHide;
	}

	public void setEmailHide(boolean emailHide) {
		this.emailHide = emailHide;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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

	public String getAbn() {
		return abn;
	}

	public void setAbn(String abn) {
		this.abn = abn;
	}

	public String getBsb() {
		return bsb;
	}

	public void setBsb(String bsb) {
		this.bsb = bsb;
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

}
