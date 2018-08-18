package com.samlic.emulator.entity;

import java.util.Date;

public class InterfaceCase {
	private Integer id;
	private String name;
	private String url;	
	private String matchRule;
	private String response;	
	private String contentType;
	private Integer status;	
	private Date createTime;
	private Date updateTIme;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMatchRule() {
		return matchRule;
	}
	public void setMatchRule(String matchRule) {
		this.matchRule = matchRule;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTIme() {
		return updateTIme;
	}
	public void setUpdateTIme(Date updateTIme) {
		this.updateTIme = updateTIme;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
	
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InterfaceCase other = (InterfaceCase) obj;
	
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		return true;
	}
	
	@Override
	public String toString() {
		return "InterfaceCase [id=" + id + ", name=" + name + ", url=" + url 
				+ ", contentType=" + contentType + ", status=" + status + ", createTime=" + createTime
				+ ", updateTIme=" + updateTIme + "]";
	}
}
