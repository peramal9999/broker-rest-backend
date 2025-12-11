package com.radianbroker.service;

import com.radianbroker.payload.request.HL7ReceivedSearchRequest;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public interface HL7ReceivedService {
    Object getHL7ReceivedMessages(HL7ReceivedSearchRequest hl7ReceivedSearchRequest);

    Resource getHL7ReceivedMessage(Long id) throws Exception;
}
