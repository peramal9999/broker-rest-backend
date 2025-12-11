package com.radianbroker.service;

import com.radianbroker.payload.request.HL7SentRequest;
import org.springframework.stereotype.Service;

@Service
public interface HL7SentService {
    Object getAllHL7QueuedMessages(HL7SentRequest hl7QueuedRequest) throws Exception;

    Object getAllHL7Sent(HL7SentRequest hl7SentRequest);
}
