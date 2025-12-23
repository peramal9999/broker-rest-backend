package com.radianbroker.service.impl;

import com.radianbroker.entity.Mip;
import com.radianbroker.entity.Report;
import com.radianbroker.entity.Ris;
import com.radianbroker.entity.Visit;
import com.radianbroker.payload.request.PaymentRequest;
import com.radianbroker.projections.MipProjection;
import com.radianbroker.repository.MipRepository;
import com.radianbroker.repository.ReportRepository;
import com.radianbroker.repository.RisRepository;
import com.radianbroker.repository.VisitRepository;
import com.radianbroker.service.VisitService;
import com.radianbroker.utils.RabbitMQQueueUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    VisitRepository visitRepository;

    @Autowired
    ReportRepository reportRepository;
    @Autowired
    RisRepository risRepository;

    @Autowired
    MipRepository mipRepository;

    @Autowired
    RabbitMQQueueUtil rabbitMQQueueUtil;

    @Override
    public Boolean sendReportPaymentToAccounts(Long risId, Long orderNo) {

        List<Visit> primeVisits = visitRepository.findByOrderNoAndRisIdAndPrimeStudy(orderNo, risId, true);
        String examCodes = null;
        if (primeVisits.size() > 0) {
            examCodes = visitRepository.getExamCodesByRisIdAndOrderNo(primeVisits.get(0).getRisId(),
                    primeVisits.get(0).getOrderNo());
            return sendReportPaymentToAccounts(primeVisits.get(0), examCodes);
        } else {

            List<Report> reports = reportRepository.getfindByOrderNoAndRisIdAndPrimeStudy(orderNo, risId, true);
            ModelMapper modelMapper = new ModelMapper();
            Visit visit = modelMapper.map(reports.get(0), Visit.class);
            examCodes = reportRepository.getExamCodesByRisIdAndOrderNo(visit.getRisId(), visit.getOrderNo());
            return sendReportPaymentToAccounts(visit, examCodes);
        }
    }

    @Override
    public Boolean sendReportPaymentToAccounts(Visit visit, String examCodes) {

//		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		headers.setContentType(MediaType.APPLICATION_JSON);

        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setReportId(visit.getReportId());
        paymentRequest.setRisId(visit.getRisId());
        paymentRequest.setMipId(visit.getMipId());
        paymentRequest.setOrderNo(visit.getOrderNo());
        paymentRequest.setExamCode(examCodes);
        paymentRequest.setDescription(visit.getDescription());

        paymentRequest.setMode(visit.getMode());
        paymentRequest.setBaseRate(visit.getBaseRate());
        paymentRequest.setReportFee(visit.getReportFee());
        paymentRequest.setMis1(visit.getMis1());
        paymentRequest.setMis2(visit.getMis2());

        paymentRequest.setVerificationDate(new Date());
        paymentRequest.setVoiceRecognitionUsed(visit.isVoiceRecognitionUsed());

        Mip mip=mipRepository.findById(visit.getMipId()).get();
        Ris ris=risRepository.findById(mip.getRisId()).get();
        MipProjection mipProjection=new MipProjection(){


            @Override
            public Long getMipId() {
                // TODO Auto-generated method stub
                return mip.getMipId();
            }

            @Override
            public String getSiteCode() {
                // TODO Auto-generated method stub
                return mip.getSiteCode();
            }

            @Override
            public Long getRisId() {
                // TODO Auto-generated method stub
                return mip.getRisId();
            }

            @Override
            public String getRisCode() {
                // TODO Auto-generated method stub
                return ris.getRisCode();
            }

        };
        String exachaneName= "VerifiedReportsQueue-"+ris.getRisId() + "-" +ris.getRisCode() + ".broker.exchange";
        rabbitMQQueueUtil.verifiedReportsQueueSend(mipProjection, exachaneName,paymentRequest);
//		artemisProducer.send(paymentRequest);
//		HttpEntity<PaymentRequest> entity = new HttpEntity<PaymentRequest>(paymentRequest, headers);
//		RestTemplate template = new RestTemplate();
//		ResponseEntity<?> response = template
//				.postForEntity("http://" + host + ":" + port + "/api/v1/reporting/add-payment", entity, Boolean.class);
//		return response;
        return true;
    }

}
