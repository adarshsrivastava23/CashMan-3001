package com.adarsh.CashMan3001.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.adarsh.CashMan3001.model.AtmStatus;
import com.adarsh.CashMan3001.repository.ATMDao;

public class ClientServiceTest {

	@Autowired
	ATMDao atmDao;
	
	@Autowired
	ClientService clientService;
	
		
	@Test
	public void testGetATMStatusFromATM() {
	/*	AtmStatus WorkingAtmStatus = new AtmStatus("Working", "ATM is working and sufficient currency");
		assertEquals(WorkingAtmStatus, clientService.getATMStatusFromATM(1));*/
		
	}

	@Test
	public void testWithdrawMoney() {
		
	}

}
