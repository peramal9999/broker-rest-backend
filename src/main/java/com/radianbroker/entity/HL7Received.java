package com.radianbroker.entity;


import com.radianbroker.audit.Auditable;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "hl7_received")
@EntityListeners(AuditingEntityListener.class)
public class HL7Received extends Auditable<String> {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ris_id")
	private Long risId;

	@Column(name = "exchange_id")
	private String exchangeId;

	@Column(name = "from_route_id")
	private String fromRouteId;

	@Column(name = "from_endpoint")
	private String fromEndpoint;

	@Column(name = "sending_application")
	private String sendingApplication;

	@Column(name = "sending_facility")
	private String sendingFacility;

	@Column(name = "receiving_application")
	private String receivingApplication;

	@Column(name = "receiving_facility")
	private String receivingFacility;

	@Column(name = "message_control_id")
	private String messageControlId;

	@Column(name = "version_id")
	private String versionId;

	@Column(name = "type")
	private String type;

	@Column(name = "mip")
	private String mip;

	@Column(name = "order_no", nullable = true)
	private String orderNumber;

	@Column(name = "directory_path", nullable = true)
	private String directoryPath;

	@Column(name = "status", nullable = false, columnDefinition = "ENUM('NEW','PROCESSED','REJECTED','FAILED','IGNORED') default 'NEW'")
	@Enumerated(EnumType.STRING)
	Status status;

	public enum Status {
		NEW, PROCESSED, REJECTED, FAILED, IGNORED
	}

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

	public String getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(String exchangeId) {
		this.exchangeId = exchangeId;
	}

	public String getFromRouteId() {
		return fromRouteId;
	}

	public void setFromRouteId(String fromRouteId) {
		this.fromRouteId = fromRouteId;
	}

	public String getFromEndpoint() {
		return fromEndpoint;
	}

	public void setFromEndpoint(String fromEndpoint) {
		this.fromEndpoint = fromEndpoint;
	}

	public String getSendingApplication() {
		return sendingApplication;
	}

	public void setSendingApplication(String sendingApplication) {
		this.sendingApplication = sendingApplication;
	}

	public String getSendingFacility() {
		return sendingFacility;
	}

	public void setSendingFacility(String sendingFacility) {
		this.sendingFacility = sendingFacility;
	}

	public String getReceivingApplication() {
		return receivingApplication;
	}

	public void setReceivingApplication(String receivingApplication) {
		this.receivingApplication = receivingApplication;
	}

	public String getReceivingFacility() {
		return receivingFacility;
	}

	public void setReceivingFacility(String receivingFacility) {
		this.receivingFacility = receivingFacility;
	}

	public String getMessageControlId() {
		return messageControlId;
	}

	public void setMessageControlId(String messageControlId) {
		this.messageControlId = messageControlId;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMip() {
		return mip;
	}

	public void setMip(String mip) {
		this.mip = mip;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getDirectoryPath() {
		return directoryPath;
	}

	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}