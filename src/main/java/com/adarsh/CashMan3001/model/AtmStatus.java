package com.adarsh.CashMan3001.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/*This is Basic ATMStatus entity which will be persistent in the database*/

@Entity
@NamedQuery(name="find_all_atm_status", query="select a from AtmStatus a")
public class AtmStatus {

	@Id
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
