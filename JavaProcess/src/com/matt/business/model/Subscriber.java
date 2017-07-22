package com.matt.business.model;

public class Subscriber {

	private Long oid;
	
	private Integer region;
	
	private Long subsid;
	
	private Long custid;
	
	private Long accountid;
	
	private String subsType;
	
	private Integer active;
	
	private String status;
	
	private String createDate;
	
	private String updateDate;
	
	private String servNumber;

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public Integer getRegion() {
		return region;
	}

	public void setRegion(Integer region) {
		this.region = region;
	}

	public Long getSubsid() {
		return subsid;
	}

	public void setSubsid(Long subsid) {
		this.subsid = subsid;
	}

	public Long getCustid() {
		return custid;
	}

	public void setCustid(Long custid) {
		this.custid = custid;
	}

	public Long getAccountid() {
		return accountid;
	}

	public void setAccountid(Long accountid) {
		this.accountid = accountid;
	}

	public String getSubsType() {
		return subsType;
	}

	public void setSubsType(String subsType) {
		this.subsType = subsType;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getServNumber() {
		return servNumber;
	}

	public void setServNumber(String servNumber) {
		this.servNumber = servNumber;
	}
	
}
