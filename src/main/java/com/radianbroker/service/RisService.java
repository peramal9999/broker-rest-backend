package com.radianbroker.service;

import com.radianbroker.entity.Ris;
import com.radianbroker.payload.response.RisWithMips;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface RisService {
	List<Ris> getAllRis();

	 List<RisWithMips> getAllRisWithMips();

	Ris getRisById(Long risId);

	Ris getByRisCode(String risCode);

}
