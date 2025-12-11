package com.radianbroker.projections;


import com.radianbroker.entity.HL7Queue;
import com.radianbroker.entity.HL7Queue.AcknowledgmentCode;

public interface HL7QueueProjection {
	 
	public Long getId();

	public String getMessageControlId();

	public Long getRisId(); 

	public Long getReportId(); 

	public String getVersionId();

	public String getType();

	public String getDirectoryPath();

	public AcknowledgmentCode getAckCode();

	public String getAckResponse();

	public String getErrorComments();
	
	public String getVisitNo();
	
	public String getLastModifiedDate();
		
}
