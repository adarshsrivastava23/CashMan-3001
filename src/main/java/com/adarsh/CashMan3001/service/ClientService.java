package com.adarsh.CashMan3001.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.adarsh.CashMan3001.model.ATM;
import com.adarsh.CashMan3001.model.AtmStatus;
import com.adarsh.CashMan3001.repository.ATMDao;
import com.adarsh.CashMan3001.repository.AtmStatusDao;
import com.adarsh.CashMan3001.repository.DenominationDao;
import com.adarsh.CashMan3001.responseBeans.AtmTransactionStatus;

@Service
public class ClientService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);
	
	@Autowired
	DenominationDao denominationService;
	
	@Autowired
	AtmStatusDao AtmStatusService;
	
	@Autowired
	ATMDao atmDao;
	
	@Autowired
	EmailService emailService;
	
	public AtmStatus getATMStatusFromATM(int atmId){
		return atmDao.getATM(atmId).getAtmStatus();	
	}
	
	@Transactional
	public AtmTransactionStatus withdrawMoney(int atmId, int amount){
		int count20 =  atmDao.getATM(atmId).getSlot20().getDenominationAvailableCount();
		int count50 =  atmDao.getATM(atmId).getSlot50().getDenominationAvailableCount();
		
		
		boolean success = false;
		int i=0;
		int j=0;
		if(amount == 200 && (count20 < 8 || count50 < 3)){
			success = false;
		}else{
			Loop1: for(i=0; i<=count20; i++){
				int temp = amount-20*i;
				if(temp < 0){
					break Loop1;
				}else if(temp == 0){
					success = true;
					break Loop1;
				}else{
					Loop2: for(j=1; j<=count50; j++){
						temp = temp - 50;
						if(temp == 0){
							success = true;
							break Loop1;
						}else if(temp < 0){
							j=0;
							break Loop2;
						}
						
					}
				}
			}
		}
		
		
		AtmTransactionStatus atmTransactionStatus = new AtmTransactionStatus();
		if(success){
			ATM atm = atmDao.getATM(atmId);
			atm.getSlot20().setDenominationAvailableCount(count20 - i);
			atm.getSlot50().setDenominationAvailableCount(count50 - j);
			LOGGER.info("On withdraw reducing corrosponding currency from ATM");
			LOGGER.info("Total " + i + " Notes of $20 and " +j  +" notes of $50 taken from ATM");
			atmDao.updateATM(atm);
			
			// if count20 - i < min limit then alert
			LOGGER.info("Check ATM minimum balance after withdrawal of money");
			checkForMinimumBalance(atmId);
			
			atmTransactionStatus.setDenomination20(i);
			atmTransactionStatus.setDenomination50(j);
			atmTransactionStatus.setInfo("Congratulation, withdrawal of amount "+ amount +" is successfull");
			
			LOGGER.info("Withdrawal of amount "+ amount +" is successfull");
			return atmTransactionStatus;
		}else{
			atmTransactionStatus.setDenomination20(0);
			atmTransactionStatus.setDenomination50(0);
			atmTransactionStatus.setInfo("Sorry, withdrawal of amount "+ amount +" is failed");
			LOGGER.info("Withdrawal of amount "+ amount +" is failed");
			return atmTransactionStatus;
		}
		
		
	}
	
	private void checkForMinimumBalance(int atmId){
		int count20 =  atmDao.getATM(atmId).getSlot20().getDenominationAvailableCount();
		int count50 =  atmDao.getATM(atmId).getSlot50().getDenominationAvailableCount();
		
		int count20Min =  atmDao.getATM(atmId).getSlot20().getDenominationMinLimitForAlert();
		int count50Min =  atmDao.getATM(atmId).getSlot50().getDenominationMinLimitForAlert();
		
		String Subject = "Refill ATM id " + atmId;;
		String Body = null;
		
		
		if((count20 - count20Min) < 0 && (count50 - count50Min) < 0){
			Body = "Atm Id " + atmId + " is having less than minimum limit."
					+ " Both $20 and $50 currency is less than threshold.";
		}else if((count20 - count20Min) < 0 ){
			Body = "Atm Id " + atmId + " is having less than minimum limit."
					+ " $20 is less than threshold.";
		}else if((count50 - count50Min) < 0 ){
			Body = "Atm Id " + atmId + " is having less than minimum limit."
					+ " $50 is less than threshold.";
		}
		
		if((count20 - count20Min) < 0 || (count50 - count50Min) < 0){
			try{
				emailService.sendMail(Subject, Body);
			}catch(MailException e){
				LOGGER.error("Mail Sending is Failed...");
				LOGGER.error(e.getMessage());
				
			}
			
		}
		
	}

}
