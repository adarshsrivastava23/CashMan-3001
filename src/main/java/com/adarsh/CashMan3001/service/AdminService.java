package com.adarsh.CashMan3001.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adarsh.CashMan3001.repository.ATMDao;
import com.adarsh.CashMan3001.repository.AtmStatusDao;
import com.adarsh.CashMan3001.repository.DenominationDao;
import com.adarsh.CashMan3001.responseBeans.AtmBalance;
import com.adarsh.CashMan3001.responseBeans.AtmTransactionStatus;

@Service
public class AdminService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminService.class);
		
	@Autowired
	DenominationDao denominationService;
	
	@Autowired
	AtmStatusDao AtmStatusService;
	
	@Autowired
	ATMDao atmDao;
	
	public AtmBalance getATMBalance(int atmId){
		int count20 =  atmDao.getATM(atmId).getSlot20().getDenominationAvailableCount();
		int count50 =  atmDao.getATM(atmId).getSlot50().getDenominationAvailableCount();
		AtmBalance atmBalance = new AtmBalance();
		atmBalance.setCount20(count20);
		atmBalance.setCount50(count50);
		atmBalance.setTotal(count20*20 + count50*50);
		return atmBalance;
	}
	
	public AtmTransactionStatus getATMavailableSlotsCount(int atmId){
		int count20 =  atmDao.getATM(atmId).getSlot20().getDenominationAvailableCount();
		int count20Max =  atmDao.getATM(atmId).getSlot20().getDenominationMaxLimitInAtm();
		int count50 =  atmDao.getATM(atmId).getSlot50().getDenominationAvailableCount();
		int count50Max =  atmDao.getATM(atmId).getSlot50().getDenominationMaxLimitInAtm();
		AtmTransactionStatus atmTransactionStatus = new AtmTransactionStatus();
		atmTransactionStatus.setDenomination20(count20Max - count20);
		atmTransactionStatus.setDenomination50(count50Max - count50);
		atmTransactionStatus.setInfo("Available space for refill ATM No. " + atmId);
		return atmTransactionStatus;
	}

	

}
