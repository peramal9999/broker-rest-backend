package com.radianbroker.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "mips", uniqueConstraints = {
		@UniqueConstraint(name = "siteCodeAndRisId", columnNames = { "site_code", "ris_id" }), })
public class Mip {

	@Id
	@Column(name = "mip_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mipId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", columnDefinition = "TEXT", nullable = true)
	private String description;

	@Column(name = "web_description", columnDefinition = "TEXT", nullable = true)
	private String webDescription;

	@Column(name = "logo_file_name", nullable = true)
	private String logoFileName;

	@Column(name = "address_line", nullable = true)
	private String addressLine;

	@Column(name = "website", nullable = true)
	private String website;

	@Column(name = "latitude", nullable = true)
	private String latitude;

	@Column(name = "longitude", nullable = true)
	private String longitude;

	@Column(name = "state", nullable = true)
	private String state;

	@Column(name = "post_code", nullable = true)
	private String postCode;

	@Column(name = "suburb", nullable = true)
	private String suburb;

	@Column(name = "phone", nullable = true)
	private String phone;

	@Column(name = "fax", nullable = true)
	private String fax;

	@Column(name = "email", nullable = true)
	private String email;

	@Column(name = "status", columnDefinition = "boolean default false", nullable = false)
	private boolean status;

	@Column(name = "status_change_date", columnDefinition = "TIMESTAMP", nullable = true)
	LocalDateTime statusChangeDate;

	@Column(name = "group_id", nullable = true)
	private Long groupId;

	@Column(name = "ris_id", nullable = true)
	private Long risId;

	@Column(name = "site_code", nullable = false)
	private String siteCode;

	@Column(name = "company_name", nullable = true)
	private String companyName;

	@Column(name = "abn", nullable = true)
	private String abn;

	public Long getMipId() {
		return mipId;
	}

	public void setMipId(Long mipId) {
		this.mipId = mipId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWebDescription() {
		return webDescription;
	}

	public void setWebDescription(String webDescription) {
		this.webDescription = webDescription;
	}

	public String getLogoFileName() {
		return logoFileName;
	}

	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getRisId() {
		return risId;
	}

	public void setRisId(Long risId) {
		this.risId = risId;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAbn() {
		return abn;
	}

	public void setAbn(String abn) {
		this.abn = abn;
	}

}
