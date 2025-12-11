package com.radianbroker.specification;


import com.radianbroker.entity.HL7Sent;
import com.radianbroker.payload.request.HL7QueueRequest;
import com.radianbroker.payload.request.HL7SentRequest;
import com.radianbroker.projections.HL7QueueProjection;
import com.radianbroker.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HL7SentSpecification {

	@Autowired
	RisUserRepository risUserRepository;

	@Autowired
	HL7SentRepository hl7SentRepository;

	@Autowired
	MipRepository mipRepository;
	@Autowired
	HL7QueueRepository hl7QueueRepository;

	@Autowired
	VisitRepository visitRepository;

	public Page<HL7Sent> getHL7Sent(HL7SentRequest request, Pageable pagingSort) {
		String startDate = request.getStartDate();
		String endDate = request.getEndDate();
		List<Long> mipIds = request.getMipIds();
	
		if (mipIds.isEmpty()) {
			mipIds = mipRepository.getMipIdsByRisId(request.getRisId());
		}
		return hl7SentRepository.getHl7SentList(startDate, endDate, request.getRisId(), mipIds, pagingSort);
	}
	
//	public Page<HL7Sent> getHL7Queued(HL7SentRequest request, Pageable pagingSort) {
//		String startDate = request.getStartDate();
//		String endDate = request.getEndDate();
//		List<Long> mipIds = request.getMipIds();
//	
//		if (mipIds.isEmpty()) {
//			mipIds = mipRepository.getMipIdsByRisId(request.getRisId());
//		}
//		return hl7SentRepository.getHl7QueuedList(startDate, endDate, request.getRisId(), mipIds, pagingSort);
//	}

	public Page<HL7QueueProjection> getHL7QueueMessages(HL7QueueRequest hl7QueueRequest, Pageable pagingSort) {
		String startDate = hl7QueueRequest.getStartDate();
		String endDate = hl7QueueRequest.getEndDate();
		List<Long> mipIds = hl7QueueRequest.getMipIds();
		String visitNo=hl7QueueRequest.getVisitNo();
		
	    if (mipIds.isEmpty()) {
			mipIds = mipRepository.getMipIdsByRisId(hl7QueueRequest.getRisId());
		}
		return hl7QueueRepository.getHl7QueueMessagesList(startDate, endDate, hl7QueueRequest.getRisId(), mipIds, visitNo, pagingSort);
	}
}
