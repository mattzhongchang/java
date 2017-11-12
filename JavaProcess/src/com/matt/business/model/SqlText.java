package com.matt.business.model;

import java.util.Date;

public class SqlText {

	private String sqlId = null;
	
	private String qryId = null;
	
	private String text = null;
	
	private Integer sn = null;
	
	private String exectype = null;
	
	private Date createdate = null;

	public String getSqlId() {
		return sqlId;
	}

	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}

	public String getQryId() {
		return qryId;
	}

	public void setQryId(String qryId) {
		this.qryId = qryId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getSn() {
		return sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	public String getExectype() {
		return exectype;
	}

	public void setExectype(String exectype) {
		this.exectype = exectype;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	
	
}
