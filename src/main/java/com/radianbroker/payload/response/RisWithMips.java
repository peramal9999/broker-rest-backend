package com.radianbroker.payload.response;

import java.util.List;

import com.radianbroker.dto.MipDTO;


public class RisWithMips {

	private Long risId;

	private String name;

	private String risCode;

	private List<MipDTO> mips;

	public Long getRisId() {
		return risId;
	}

	public void setRisId(Long risId) {
		this.risId = risId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRisCode() {
		return risCode;
	}

	public void setRisCode(String risCode) {
		this.risCode = risCode;
	}

	public List<MipDTO> getMips() {
		return mips;
	}

	public void setMips(List<MipDTO> mips) {
		this.mips = mips;
	}

}
