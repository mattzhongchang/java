package com.matt.business.model;

import java.util.Date;

public class BDVisitorInfo {

	private Integer id;
	
	private String visitorId;
	
	private String insuredIdentity;
	
	private String policyNo;
	
	private String splanCode;
	
	private Date operateTime;
	
	private Date startTime;
	
	private Date endTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(String visitorId) {
		this.visitorId = visitorId;
	}

	public String getInsuredIdentity() {
		return insuredIdentity;
	}

	public void setInsuredIdentity(String insuredIdentity) {
		this.insuredIdentity = insuredIdentity;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getSplanCode() {
		return splanCode;
	}

	public void setSplanCode(String splanCode) {
		this.splanCode = splanCode;
	}
	
	
}
