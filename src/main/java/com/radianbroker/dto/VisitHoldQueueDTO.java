package com.radianbroker.dto;

import java.util.Date;

public class VisitHoldQueueDTO {

	public Long risId;

	public Long reportId;
	
	public Long orderNo;

	public String visitNo;

	public String visitStart;

	public String risCode;

	public String mip;

	public String machine;

	public String pid;

	public String patientName;

	public String visitStatus;
	
	public Date verificationDateTime;

	public Long getRisId() {
		return risId;
	}

	public void setRisId(Long risId) {
		this.risId = risId;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}
	
	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public String getVisitNo() {
		return visitNo;
	}

	public void setVisitNo(String visitNo) {
		this.visitNo = visitNo;
	}

	public String getVisitStart() {
		return visitStart;
	}

	public void setVisitStart(String visitStart) {
		this.visitStart = visitStart;
	}

	public String getRisCode() {
		return risCode;
	}

	public void setRisCode(String risCode) {
		this.risCode = risCode;
	}

	public String getMip() {
		return mip;
	}

	public void setMip(String mip) {
		this.mip = mip;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getVisitStatus() {
		return visitStatus;
	}

	public void setVisitStatus(String visitStatus) {
		this.visitStatus = visitStatus;
	}

	public Date getVerificationDateTime() {
		return verificationDateTime;
	}

	public void setVerificationDateTime(Date verificationDateTime) {
		this.verificationDateTime = verificationDateTime;
	}

}
