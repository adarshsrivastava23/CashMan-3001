package com.adarsh.CashMan3001.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.adarsh.CashMan3001.model.ATM;

@Repository
@Transactional
public interface ATMDao {

	public ATM createATM(ATM atm);
	
	public ATM updateATM(ATM atm);
	
	public ATM getATM(int ATMId);
	
	public List<ATM> getAllATM();
	
	public void removeATM(ATM atm);
	
}
