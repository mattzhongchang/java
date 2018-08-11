package redis.pubsub.model;

import java.util.Date;

public class VisitorAndJob {

	private String visitorId;
	
	private String jobId;
	
	private Date createDate;
	
	private CssMessage cssMessage;

	public String getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(String visitorId) {
		this.visitorId = visitorId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public CssMessage getCssMessage() {
		return cssMessage;
	}

	public void setCssMessage(CssMessage cssMessage) {
		this.cssMessage = cssMessage;
	}
	
	
	
}
