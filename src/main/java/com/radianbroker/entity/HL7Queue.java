package com.radianbroker.entity;

import com.radianbroker.audit.Auditable;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "hl7_queue", uniqueConstraints = { @UniqueConstraint(columnNames = "message_control_id") })
@EntityListeners(AuditingEntityListener.class)
public class HL7Queue extends Auditable<String> {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "message_control_id", nullable = false)
	private String messageControlId;

	@Column(name = "ris_id", nullable = false)
	private Long risId;

	@Column(name = "report_id", nullable = false)
	private Long reportId;

	@Column(name = "version_id", nullable = true)
	private String versionId;

	@Column(name = "type", nullable = true)
	private String type;

	@Column(name = "directory_path", nullable = true)
	private String directoryPath;

	@Column(name = "ack_code", nullable = true, columnDefinition = "ENUM('AA', 'AE', 'AR', 'CA', 'CE', 'CR') default NULL")
	@Enumerated(EnumType.STRING)
	private AcknowledgmentCode ackCode;

	@Column(name = "ack_response", nullable = true)
	private String ackResponse;

	@Column(name = "error_comments", nullable = true)
	private String errorComments;

	public enum AcknowledgmentCode {
//		AA	Original mode: Application Accept - Enhanced mode: Application acknowledgment: Accept	
//		AE	Original mode: Application Error - Enhanced mode: Application acknowledgment: Error	
//		AR	Original mode: Application Reject - Enhanced mode: Application acknowledgment: Reject	
//		CA	Enhanced mode: Accept acknowledgment: Commit Accept	
//		CE	Enhanced mode: Accept acknowledgment: Commit Error	
//		CR	Enhanced mode: Accept acknowledgment: Commit Reject
		AA, AE, AR, CA, CE, CR
	}
	
	@Column(name = "retry_queue_count", nullable = true)
	private int retryQueueCount;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessageControlId() {
		return messageControlId;
	}

	public void setMessageControlId(String messageControlId) {
		this.messageControlId = messageControlId;
	}

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

	public String getDirectoryPath() {
		return directoryPath;
	}

	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}

	public AcknowledgmentCode getAckCode() {
		return ackCode;
	}

	public void setAckCode(AcknowledgmentCode ackCode) {
		this.ackCode = ackCode;
	}

	public String getAckResponse() {
		return ackResponse;
	}

	public void setAckResponse(String ackResponse) {
		this.ackResponse = ackResponse;
	}

	public String getErrorComments() {
		return errorComments;
	}

	public void setErrorComments(String errorComments) {
		this.errorComments = errorComments;
	}

	public int getRetryQueueCount() {
		return retryQueueCount;
	}

	public void setRetryQueueCount(int retryQueueCount) {
		this.retryQueueCount = retryQueueCount;
	}

}
