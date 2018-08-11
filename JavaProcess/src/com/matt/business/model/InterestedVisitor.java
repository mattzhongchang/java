package com.matt.business.model;

import java.util.Date;

public class InterestedVisitor {

	private Integer id;
	
	private String visitorId;
	
	private String visitorName;
	
	private Date markingTime;
	
	private String jobId;
	
	private String lastServiceJobId;
	
	private Integer sessions;
	
	private String jobName;
	
	private String lastServiceJobName;
	
	private int cnt;

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

	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getLastServiceJobId() {
		return lastServiceJobId;
	}

	public void setLastServiceJobId(String lastServiceJobId) {
		this.lastServiceJobId = lastServiceJobId;
	}

	public Integer getSessions() {
		return sessions;
	}

	public void setSessions(Integer sessions) {
		this.sessions = sessions;
	}

	public Date getMarkingTime() {
		return markingTime;
	}

	public void setMarkingTime(Date markingTime) {
		this.markingTime = markingTime;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getLastServiceJobName() {
		return lastServiceJobName;
	}

	public void setLastServiceJobName(String lastServiceJobName) {
		this.lastServiceJobName = lastServiceJobName;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	
	
}
