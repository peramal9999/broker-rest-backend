package com.radianbroker.dto;

public class MipDTO {

	private Long mipId;
	private Long risId;
	private Long groupId;
	private String name;
	private String siteCode;

	public Long getMipId() {
		return mipId;
	}

	public void setMipId(Long mipId) {
		this.mipId = mipId;
	}

	public Long getRisId() {
		return risId;
	}

	public void setRisId(Long risId) {
		this.risId = risId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

}
