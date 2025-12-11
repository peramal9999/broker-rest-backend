package com.radianbroker.entity;


import com.radianbroker.enums.Mode;
import com.radianbroker.enums.OrderStatus;
import com.radianbroker.enums.State;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "reports", uniqueConstraints = { @UniqueConstraint(columnNames = { "report_id", "ris_id" }) })
public class Report {

	@Id
	@Column(name = "report_id")
	private Long reportId;

	@Column(name = "patient_id", nullable = false)
	private Long patientId;

	@Column(name = "ris_id", nullable = false)
	private Long risId;

	@Column(name = "mip_id", nullable = false)
	private Long mipId;

	@Column(name = "order_no", nullable = false)
	private Long orderNo;

	@Column(name = "visit_no", nullable = false)
	private String visitNo;

	@Column(name = "description", nullable = true)
	private String description;

	@Column(name = "referrer", nullable = true)
	private String referrer;

	@Column(name = "ris_code", nullable = true)
	private String risCode;
	
	@Column(name = "mip", nullable = true)
	private String mip;

	@Column(name = "machine", nullable = true)
	private String machine;

	@Column(name = "priority", nullable = true, columnDefinition = "ENUM('R','S')")
	@Enumerated(EnumType.STRING)
	private Priority priority;

	@Column(name = "exam_code", nullable = true)
	private String examCode;

	@Column(name = "exam_description", nullable = true)
	private String examDescription;

	@Column(name = "visit_start", nullable = true)
	private Timestamp visitStart;

	@Column(name = "visit_end", nullable = true)
	private Timestamp visitEnd;

	@Column(name = "report_text", columnDefinition = "TEXT", nullable = true)
	private String reportText;

	@Column(name = "notes", length = 500, nullable = true)
	private String notes;
	
	@Column(name = "technician", nullable = true)
	private String technician;

	@Column(name = "radiologist", nullable = true)
	private String radiologist;

	@Column(name = "order_status", nullable = false, columnDefinition = "ENUM('A', 'CA', 'CM', 'DC', 'ER', 'HD', 'IP', 'RP', 'SC', 'ZZ','ZA','ZW','ZY')")
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	@Column(name = "prime_study", nullable = false, columnDefinition = "boolean default false")
	private boolean primeStudy = false;

	@Column(name = "state", nullable = false, columnDefinition = "ENUM('FR', 'P', 'SFV', 'H', 'V') default 'FR'")
	@Enumerated(EnumType.STRING)
	private State state;

	@Column(name = "mrt", nullable = true)
	private Long mrt;

	@Column(name = "smrt", nullable = true)
	private Long smrt;

	@Column(name = "mis1", nullable = true)
	private Long mis1;

	@Column(name = "mis2", nullable = true)
	private Long mis2;

	@Column(name = "typ", nullable = true)
	private Long typ;

	@Column(name = "verify1", nullable = false, columnDefinition = "tinyint(1) default 0")
	private Boolean verify1 = false;

	@Column(name = "verify2", nullable = false, columnDefinition = "tinyint(1) default 0")
	private Boolean verify2 = false;

	@Column(name = "for_typing", nullable = true)
	private Boolean forTyping;

	@Column(name = "locked_by", nullable = true)
	private Long lockedBy;

	@Column(name = "locked_date", nullable = true)
	private Timestamp lockedDate;

	@Column(name = "updated_by_msg_id", nullable = false)
	private Long updatedByMsgId;
	
	@Column(name = "follow_up", nullable = false, columnDefinition = "tinyint(1) default 0")
	private Boolean followUp = false;
	
	@Column(name = "base_rate", nullable = true)
	private Double baseRate;

	@Column(name = "report_fee", nullable = true)
	private Double reportFee;

	@Column(name = "mode", nullable = false, columnDefinition = "ENUM('Compliant', 'NonCompliant', 'LocalReport') default 'Compliant'")
	@Enumerated(EnumType.STRING)
	private Mode mode;

	@Column(name = "created_by", nullable = true)
	private String createdBy;

	@Column(name = "created_date", nullable = true)
	private Date createdDate;

	@Column(name = "last_modified_by", nullable = true)
	private String lastModifiedBy;

	@Column(name = "last_modified_date", nullable = true)
	private Date lastModifiedDate;
	
	@Column(name = "voice_recognition_used", nullable = false, columnDefinition = "boolean default false")
	private boolean voiceRecognitionUsed = false;
	
	public enum Priority {
		S, R
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public Double getBaseRate() {
		return baseRate;
	}

	public void setBaseRate(Double baseRate) {
		this.baseRate = baseRate;
	}

	public Double getReportFee() {
		return reportFee;
	}

	public void setReportFee(Double reportFee) {
		this.reportFee = reportFee;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getRisId() {
		return risId;
	}

	public void setRisId(Long risId) {
		this.risId = risId;
	}

	public Long getMipId() {
		return mipId;
	}

	public void setMipId(Long mipId) {
		this.mipId = mipId;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
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

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public String getExamCode() {
		return examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public String getExamDescription() {
		return examDescription;
	}

	public void setExamDescription(String examDescription) {
		this.examDescription = examDescription;
	}

	public Timestamp getVisitStart() {
		return visitStart;
	}

	public void setVisitStart(Timestamp visitStart) {
		this.visitStart = visitStart;
	}

	public Timestamp getVisitEnd() {
		return visitEnd;
	}

	public void setVisitEnd(Timestamp visitEnd) {
		this.visitEnd = visitEnd;
	}

	public String getReportText() {
		return reportText;
	}

	public void setReportText(String reportText) {
		this.reportText = reportText;
	}
	
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getTechnician() {
		return technician;
	}

	public void setTechnician(String technician) {
		this.technician = technician;
	}

	public String getRadiologist() {
		return radiologist;
	}

	public void setRadiologist(String radiologist) {
		this.radiologist = radiologist;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public boolean isPrimeStudy() {
		return primeStudy;
	}

	public void setPrimeStudy(boolean primeStudy) {
		this.primeStudy = primeStudy;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Long getMrt() {
		return mrt;
	}

	public void setMrt(Long mrt) {
		this.mrt = mrt;
	}

	public Long getSmrt() {
		return smrt;
	}

	public void setSmrt(Long smrt) {
		this.smrt = smrt;
	}

	public Long getMis1() {
		return mis1;
	}

	public void setMis1(Long mis1) {
		this.mis1 = mis1;
	}

	public Long getMis2() {
		return mis2;
	}

	public void setMis2(Long mis2) {
		this.mis2 = mis2;
	}

	public Long getTyp() {
		return typ;
	}

	public void setTyp(Long typ) {
		this.typ = typ;
	}

	public Boolean getVerify1() {
		return verify1;
	}

	public void setVerify1(Boolean verify1) {
		this.verify1 = verify1;
	}

	public Boolean getVerify2() {
		return verify2;
	}

	public void setVerify2(Boolean verify2) {
		this.verify2 = verify2;
	}

	public Boolean getForTyping() {
		return forTyping;
	}

	public void setForTyping(Boolean forTyping) {
		this.forTyping = forTyping;
	}

	public Long getLockedBy() {
		return lockedBy;
	}

	public void setLockedBy(Long lockedBy) {
		this.lockedBy = lockedBy;
	}

	public Timestamp getLockedDate() {
		return lockedDate;
	}

	public void setLockedDate(Timestamp lockedDate) {
		this.lockedDate = lockedDate;
	}

	public Long getUpdatedByMsgId() {
		return updatedByMsgId;
	}

	public void setUpdatedByMsgId(Long updatedByMsgId) {
		this.updatedByMsgId = updatedByMsgId;
	}

	public Boolean getFollowUp() {
		return followUp;
	}

	public void setFollowUp(Boolean followUp) {
		this.followUp = followUp;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public boolean isVoiceRecognitionUsed() {
		return voiceRecognitionUsed;
	}

	public void setVoiceRecognitionUsed(boolean voiceRecognitionUsed) {
		this.voiceRecognitionUsed = voiceRecognitionUsed;
	}

	
}
