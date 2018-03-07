package com.adarsh.CashMan3001.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adarsh.CashMan3001.model.ATM;
import com.adarsh.CashMan3001.repository.ATMDao;
import com.adarsh.CashMan3001.responseBeans.AtmBalance;
import com.adarsh.CashMan3001.responseBeans.AtmTransactionStatus;

/*This is service class for Bank Admin
 * It is having all business logic implementation*/


@Service
public class AdminService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminService.class);
			
	@Autowired
	ATMDao atmDao;
	
	public AtmBalance getATMBalance(int atmId){
		ATM atm = atmDao.getATM(atmId);
		int count20 =  atm.getSlot20().getDenominationAvailableCount();
		int count50 =  atm.getSlot50().getDenominationAvailableCount();
		AtmBalance atmBalance = new AtmBalance();
		atmBalance.setCount20(count20);
		atmBalance.setCount50(count50);
		//calculating ATM Balance
		atmBalance.setTotal(count20*20 + count50*50);
		return atmBalance;
	}
	
	public AtmTransactionStatus getATMavailableSlotsCount(int atmId){
		ATM atm = atmDao.getATM(atmId);
		int count20 =  atm.getSlot20().getDenominationAvailableCount();
		int count20Max =  atm.getSlot20().getDenominationMaxLimitInAtm();
		int count50 =  atm.getSlot50().getDenominationAvailableCount();
		int count50Max =  atm.getSlot50().getDenominationMaxLimitInAtm();
		AtmTransactionStatus atmTransactionStatus = new AtmTransactionStatus();
		atmTransactionStatus.setAtmId(atmId);
		//calculating available space in slots
		atmTransactionStatus.setDenomination20(count20Max - count20);
		atmTransactionStatus.setDenomination50(count50Max - count50);
		atmTransactionStatus.setInfo("Available space for refill");
		return atmTransactionStatus;
	}
	
	public AtmTransactionStatus refillAtm(AtmTransactionStatus refillAmount){
		ATM atm = atmDao.getATM(refillAmount.getAtmId());
		int count20 =  atm.getSlot20().getDenominationAvailableCount();
		int count20Max =  atm.getSlot20().getDenominationMaxLimitInAtm();
		int count50 =  atm.getSlot50().getDenominationAvailableCount();
		int count50Max =  atm.getSlot50().getDenominationMaxLimitInAtm();
		
		int count20New = count20 + refillAmount.getDenomination20();
		int count50New = count50 + refillAmount.getDenomination50();
		
		AtmTransactionStatus atmTransactionStatus = new AtmTransactionStatus();
		atmTransactionStatus.setAtmId(refillAmount.getAtmId());
		
		// Check the request refill amount should not exceed available space
		if(count20New > count20Max || count50New > count50Max){
			LOGGER.error("Refill failed due to value is more than available space");
			LOGGER.error(" please try maximum note of $20 <= " +
						(count20Max - count20) + "  and $50 <= " + (count50Max - count50));
			
			atmTransactionStatus.setDenomination20(count20);
			atmTransactionStatus.setDenomination50(count50);
			atmTransactionStatus.setInfo("ATM Balance does not updated. Refill failed due to insufficient space in ATM slot, please try maximum note of $20 <= " +
					(count20Max - count20) + "  and $50 <= " + (count50Max - count50));
		}else{
			LOGGER.info("ATM Refilled successfully, This is new ATM balance");
			atmTransactionStatus.setDenomination20(count20New);
			atmTransactionStatus.setDenomination50(count50New);
			atmTransactionStatus.setInfo("ATM Refilled successfully, This is new ATM balance");
			
			// updating refill values to database
			atm.getSlot20().setDenominationAvailableCount(count20New);
			atm.getSlot50().setDenominationAvailableCount(count50New);
			atmDao.updateATM(atm);
			
		}
		return atmTransactionStatus;
	}

	

}
