package com.adarsh.CashMan3001.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.adarsh.CashMan3001.repository.ATMDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {

	@Autowired
	ATMDao atmDao;
	
	@Autowired
	ClientService clientService;
	
		
	@Test
	public void testGetATMStatusFromATM() {
		assertEquals("Working", clientService.getATMStatusFromATM(1).getStatus());
	}

	@Test
	public void testWithdrawMoney() {
		assertEquals(3, clientService.withdrawMoney(1, 110).getDenomination20());
		assertEquals(1, clientService.withdrawMoney(1, 110).getDenomination50());
	}

}
