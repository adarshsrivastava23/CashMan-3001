package com.adarsh.CashMan3001.responseBeans;

/* This class is used for REST communication for serializing and deserializing
 * JSON message to Java Bean
 * 
 *  This class is not eligible to persistence in database.*/

public class AtmBalance {
	
	private int total;
	private int count20;
	private int count50;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCount20() {
		return count20;
	}
	public void setCount20(int count20) {
		this.count20 = count20;
	}
	public int getCount50() {
		return count50;
	}
	public void setCount50(int count50) {
		this.count50 = count50;
	}
	
	
}
