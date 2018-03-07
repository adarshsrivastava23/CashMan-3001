package com.adarsh.CashMan3001.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.adarsh.CashMan3001.model.ATM;
import com.adarsh.CashMan3001.repository.ATMDao;
import com.adarsh.CashMan3001.responseBeans.AtmTransactionStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {

	@Autowired
	ATMDao atmDao;
	
	@Autowired
	AdminService adminService;
	
	@Before
	public void testSetup() {
		ATM atm = atmDao.getATM(1);
		atm.getSlot20().setDenominationAvailableCount(24);
		atm.getSlot50().setDenominationAvailableCount(15);
		atm = atmDao.updateATM(atm);
	}
	
		
	@Test
	public void testgetATMBalance() {
		assertEquals(1230, adminService.getATMBalance(1).getTotal());
		assertEquals(24, adminService.getATMBalance(1).getCount20());
		assertEquals(15, adminService.getATMBalance(1).getCount50());
	}

	@Test
	public void testgetATMavailableSlotsCount() {
		assertEquals(76, adminService.getATMavailableSlotsCount(1).getDenomination20());
		assertEquals(85, adminService.getATMavailableSlotsCount(1).getDenomination50());
	}

	@Test
	public void testrefillAtmPassCase() {
		AtmTransactionStatus refillAmount = new AtmTransactionStatus();
		refillAmount.setAtmId(1);
		refillAmount.setDenomination20(5);
		refillAmount.setDenomination50(10);
		assertEquals(29, adminService.refillAtm(refillAmount).getDenomination20());
		assertEquals(25, adminService.refillAtm(refillAmount).getDenomination50());
	}
	
	@Test
	public void testrefillAtmFailCase() {
		AtmTransactionStatus refillAmount = new AtmTransactionStatus();
		refillAmount.setAtmId(1);
		refillAmount.setDenomination20(200);
		refillAmount.setDenomination50(10);
		assertEquals(24, adminService.refillAtm(refillAmount).getDenomination20());
		assertEquals(15, adminService.refillAtm(refillAmount).getDenomination50());
	}

}
