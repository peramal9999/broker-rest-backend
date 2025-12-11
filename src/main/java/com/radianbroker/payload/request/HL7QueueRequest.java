package com.radianbroker.payload.request;

import java.util.List;

public class HL7QueueRequest {
	private Long risId;
	private List<Long> mipIds;
    private String startDate;
	private String endDate;
	private int page ;
    private int size ;
    private String visitNo;
    
	public Long getRisId() {
		return risId;
	}
	public void setRisId(Long risId) {
		this.risId = risId;
	}
	public List<Long> getMipIds() {
		return mipIds;
	}
	public void setMipIds(List<Long> mipIds) {
		this.mipIds = mipIds;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getVisitNo() {
		return visitNo;
	}
	public void setVisitNo(String visitNo) {
		this.visitNo = visitNo;
	}
	   
}
