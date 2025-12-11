package com.radianbroker.service.impl;

import com.radianbroker.dto.MipDTO;
import com.radianbroker.entity.Mip;
import com.radianbroker.entity.Ris;
import com.radianbroker.exceptions.ResourceNotFoundException;
import com.radianbroker.payload.response.RisWithMips;
import com.radianbroker.repository.MipRepository;
import com.radianbroker.repository.RisRepository;
import com.radianbroker.service.RisService;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RisServiceImpl implements RisService {

	@Autowired
	RisRepository risRepository;

	@Autowired
	MipRepository mipRepository;

	@Override
	public Ris getRisById(Long risId) {
		// TODO Auto-generated method stub
		return risRepository.findById(risId).orElseThrow(
				() -> new ResourceNotFoundException("Ris not found for ID: " + risId));
	}
	
	@Override
	public List<Ris> getAllRis() {
		// TODO Auto-generated method stub
		return risRepository.findAll();
	}

	@Override
	public List<RisWithMips> getAllRisWithMips() {
		// TODO Auto-generated method stub
		List<Ris> risList = risRepository.findAll();
		List<RisWithMips> risWithMipsList = new ArrayList<RisWithMips>();
		for (Ris ris : risList) {

			RisWithMips risWithMips = new RisWithMips();
			risWithMips.setRisId(ris.getRisId());
			risWithMips.setName(ris.getName());
			risWithMips.setRisCode(ris.getRisCode());

			List<MipDTO> mipDtoList = new ArrayList<MipDTO>();
			List<Mip> mips = mipRepository.findAllByRisId(ris.getRisId());
			for (Mip mip : mips) {

				MipDTO mipDto = new MipDTO();
				mipDto.setMipId(mip.getMipId());
				mipDto.setGroupId(mip.getGroupId());
				mipDto.setName(mip.getName());
				mipDto.setSiteCode(mip.getSiteCode());
				mipDtoList.add(mipDto);
			}
			risWithMips.setMips(mipDtoList);
			risWithMipsList.add(risWithMips);
		}
		return risWithMipsList;
	}

	@Override
	public Ris getByRisCode(String risCode) {
		// TODO Auto-generated method stub
		return null;
	}

}
