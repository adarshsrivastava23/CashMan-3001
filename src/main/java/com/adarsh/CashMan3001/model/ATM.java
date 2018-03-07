package com.adarsh.CashMan3001.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/*This is Basic ATM entity which will be persistent in the database*/

@Entity
@NamedQuery(name="find_all_atm", query="select a from ATM a")
public class ATM {
	
	@Id
	private int atmId;
	
	@OneToOne
	private Denomination slot20;
	
	@OneToOne
	private Denomination slot50;
	
	@OneToOne
	private AtmStatus atmStatus;
	
	public ATM() {
		super();
	}


	public ATM(int atmId, Denomination slot20, Denomination slot50, AtmStatus atmStatus) {
		super();
		this.atmId = atmId;
		this.slot20 = slot20;
		this.slot50 = slot50;
		this.atmStatus = atmStatus;
	}


	public int getAtmId() {
		return atmId;
	}


	public Denomination getSlot20() {
		return slot20;
	}


	public void setSlot20(Denomination slot20) {
		this.slot20 = slot20;
	}


	public Denomination getSlot50() {
		return slot50;
	}


	public void setSlot50(Denomination slot50) {
		this.slot50 = slot50;
	}


	public AtmStatus getAtmStatus() {
		return atmStatus;
	}


	public void setAtmStatus(AtmStatus atmStatus) {
		this.atmStatus = atmStatus;
	}
		
}
