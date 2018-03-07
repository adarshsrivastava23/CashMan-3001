package com.adarsh.CashMan3001.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/*This is Basic ATM Denomination entity which will be persistent in the database*/

@Entity
@NamedQuery(name="find_all_denomination", query="select d from Denomination d")
public class Denomination {
	
	@Id
	private int denominationValue;
	private int denominationAvailableCount;
	private int denominationMaxLimitInAtm;
	private int denominationMinLimitForAlert;
	
	public Denomination() {
		
	}
	
	public Denomination(int denominationValue, int denominationAvailableCount, int denominationMaxLimitInAtm,
			int denominationMinLimitForAlert) {
		super();
		this.denominationValue = denominationValue;
		this.denominationAvailableCount = denominationAvailableCount;
		this.denominationMaxLimitInAtm = denominationMaxLimitInAtm;
		this.denominationMinLimitForAlert = denominationMinLimitForAlert;
	}

	public int getDenominationValue() {
		return denominationValue;
	}

	public void setDenominationValue(int denominationValue) {
		this.denominationValue = denominationValue;
	}

	public int getDenominationAvailableCount() {
		return denominationAvailableCount;
	}

	public void setDenominationAvailableCount(int denominationAvailableCount) {
		this.denominationAvailableCount = denominationAvailableCount;
	}

	public int getDenominationMaxLimitInAtm() {
		return denominationMaxLimitInAtm;
	}

	public void setDenominationMaxLimitInAtm(int denominationMaxLimitInAtm) {
		this.denominationMaxLimitInAtm = denominationMaxLimitInAtm;
	}

	public int getDenominationMinLimitForAlert() {
		return denominationMinLimitForAlert;
	}

	public void setDenominationMinLimitForAlert(int denominationMinLimitForAlert) {
		this.denominationMinLimitForAlert = denominationMinLimitForAlert;
	}
	
	
}
