package com.adarsh.CashMan3001.responseBeans;


/* This class is used for REST communication for serializing and deserializing
 * JSON message to Java Bean
 * 
 *  This class is not eligible to persistence in database.*/

public class AtmTransactionStatus {
	
	private int atmId;
	private int denomination20;
	private int denomination50;
	private String info;
		
	public int getAtmId() {
		return atmId;
	}
	public void setAtmId(int atmId) {
		this.atmId = atmId;
	}
	public int getDenomination20() {
		return denomination20;
	}
	public void setDenomination20(int denomination20) {
		this.denomination20 = denomination20;
	}
	public int getDenomination50() {
		return denomination50;
	}
	public void setDenomination50(int denomination50) {
		this.denomination50 = denomination50;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	
	
	
	

}
