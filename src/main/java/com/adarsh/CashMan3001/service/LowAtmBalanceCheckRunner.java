package com.adarsh.CashMan3001.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adarsh.CashMan3001.model.ATM;
import com.adarsh.CashMan3001.repository.ATMDao;

@Service
public class LowAtmBalanceCheckRunner {
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	ATMDao atmDao;

	int timeInterval = 1000*60*60; // 1 hour interval
	
	// It is a scheduler to check ATM low balance in every 1 hour.
	// In case of low balance it will sent mail to bank Admin
	public void checkLowBalance() {
	        try {
	        	List<ATM> atms = atmDao.getAllATM();
	        	for(ATM atm: atms){
	        		// checks for every ATM
	        		clientService.checkForMinimumBalance(atm.getAtmId());
	        	}
	        	
	            Thread.sleep(timeInterval);
	            checkLowBalance();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	 }
	        

}
