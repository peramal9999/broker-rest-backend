package com.radianbroker.service.impl;

import com.radianbroker.entity.HL7Received;
import com.radianbroker.entity.Ris;
import com.radianbroker.payload.request.HL7ReceivedSearchRequest;
import com.radianbroker.repository.HL7ReceivedRepository;
import com.radianbroker.service.HL7ReceivedService;
import com.radianbroker.service.RisService;
import com.radianbroker.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.*;

@Service
public class HL7ReceivedServiceImpl implements HL7ReceivedService {

    @Value("${app.server.zoneOffset}")
    private String timeZone;

    @Value("${broker.ris.code}")
    private String risCode;

    @Autowired
    HL7ReceivedRepository hl7ReceivedRepository;

    @Autowired
    RisService risService;

    @Override
    public Map<String, Object> getHL7ReceivedMessages(HL7ReceivedSearchRequest request) {

        List<HL7Received> hl7ReceivedList = new ArrayList<HL7Received>();

        Ris ris = risService.getByRisCode(risCode);
        request.setRisId(ris.getRisId());
        Pageable paging = PageRequest.of(request.getPageNo(), request.getSize());
        ZoneOffset zoneOffset = DateUtils.getZoneOffset(timeZone);

        Date startDate = null;
        Date endDate = null;

        if (request.getStartDate() != null && !request.getStartDate().isEmpty() &&
                request.getEndDate() != null && !request.getEndDate().isEmpty()) {

            LocalDate startLocalDate = LocalDate.parse(request.getStartDate());
            LocalDate endLocalDate = LocalDate.parse(request.getEndDate());

            startDate = Date.from(
                    startLocalDate.atStartOfDay().toInstant(zoneOffset)
            );

            endDate = Date.from(
                    endLocalDate.atTime(LocalTime.MAX).toInstant(zoneOffset)
            );
        }

        Page<HL7Received> pages = hl7ReceivedRepository
                .findByFilterAll(startDate,endDate,request.getMessageTypes(),request.getMips(),request.getOrderNo(),request.getRisId(),request.getStatuses(), paging);

        if (pages != null && pages.getContent() != null) {
            hl7ReceivedList = pages.getContent();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("hl7ReceivedList", hl7ReceivedList);
        response.put("currentPage", pages.getNumber());
        response.put("totalItems", pages.getTotalElements());
        response.put("totalPages", pages.getTotalPages());
        return response;
    }
    @Override
    public Resource getHL7ReceivedMessage(long id) {
        return null;
    }

}
