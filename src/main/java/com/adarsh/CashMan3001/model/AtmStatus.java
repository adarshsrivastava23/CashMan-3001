package com.adarsh.CashMan3001.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="find_all_atm_status", query="select a from AtmStatus a")
public class AtmStatus {

	@Id
	@GeneratedValue
	private int statusid;

	private String status;
	private String Description;
	
	public AtmStatus() {
		super();
	}
	public AtmStatus(String status, String description) {
		super();
		this.status = status;
		Description = description;
	}
	public int getStatusid() {
		return statusid;
	}
	public void setStatusid(int statusid) {
		this.statusid = statusid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	
	

}
