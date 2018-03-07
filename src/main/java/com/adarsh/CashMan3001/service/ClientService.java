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
import com.adarsh.CashMan3001.responseBeans.AtmTransactionStatus;

/*This is service class for Bank Client
 * It is having all business logic implementation*/

@Service
public class ClientService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

	@Autowired
	AtmStatusDao atmStatusService;

	@Autowired
	ATMDao atmDao;

	@Autowired
	EmailService emailService;

	public AtmStatus getATMStatusFromATM(int atmId) {
		return atmDao.getATM(atmId).getAtmStatus();
	}

	@Transactional
	public AtmTransactionStatus withdrawMoney(int atmId, int amount) {
		int count20 = atmDao.getATM(atmId).getSlot20().getDenominationAvailableCount();
		int count50 = atmDao.getATM(atmId).getSlot50().getDenominationAvailableCount();

		// calculation of $20 and $50 combination for requested amount
		boolean success = false;
		int i = 0;
		int j = 0;
		//check if requested amount is 200 then at least 8 note of $20 and 3 notes of $50 available in ATM
		if (amount == 200 && (count20 < 8 || count50 < 3)) {
			success = false;
		} else {
			Loop1: for (i = 0; i <= count20; i++) {
				j=0;
				int temp = amount - 20 * i;
				if (temp < 0) {
					break Loop1;
				} else if (temp == 0) {
					success = true;
					break Loop1;
				} else {
					Loop2: for (j = 1; j <= count50; j++) {
						temp = temp - 50;
						if (temp == 0) {
							success = true;
							break Loop1;
						} else if (temp < 0) {
							j = 0;
							break Loop2;
						}
					}
				}
			}
		}

		AtmTransactionStatus atmTransactionStatus = new AtmTransactionStatus();
		// If success is true then combination of $20 and $50 is found for requested amount
		if (success) {
			ATM atm = atmDao.getATM(atmId);
			atm.getSlot20().setDenominationAvailableCount(count20 - i);
			atm.getSlot50().setDenominationAvailableCount(count50 - j);
			LOGGER.info("On withdraw reducing corrosponding currency from ATM");
			LOGGER.info("**************************************************************************");
			LOGGER.info("Total " + i + " Notes of $20 and " + j + " notes of $50 taken from ATM");
			LOGGER.info("**************************************************************************");
			atm = atmDao.updateATM(atm);
			
			LOGGER.info("Check ATM minimum balance after withdrawal of money");
			String atmCashStatus = checkForMinimumBalance(atmId);
			if (atmCashStatus.equals("OutOfCash")) {
				//In case no cash available, update ATM status
				AtmStatus atmStatus = atmStatusService.getAtmStatus("OutOfCash");
				atm.setAtmStatus(atmStatus);
				atmDao.updateATM(atm);
			} else if (atmCashStatus.equals("LowBalance")) {
				//In case low cash available, update ATM status
				AtmStatus atmStatus = atmStatusService.getAtmStatus("LowBalance");
				atm.setAtmStatus(atmStatus);
				atmDao.updateATM(atm);
			}
			
			atmTransactionStatus.setAtmId(atmId);
			atmTransactionStatus.setDenomination20(i);
			atmTransactionStatus.setDenomination50(j);
			atmTransactionStatus.setInfo("Congratulation, withdrawal of amount " + amount + " is successfull, "
											+ "Please collect your cash");

			LOGGER.info("Withdrawal of amount " + amount + " is successfull");
			return atmTransactionStatus;
		} else {
			atmTransactionStatus.setAtmId(atmId);
			atmTransactionStatus.setDenomination20(0);
			atmTransactionStatus.setDenomination50(0);
			atmTransactionStatus.setInfo("Sorry, withdrawal of amount " + amount + " is failed, due to insufficient balance.");
			LOGGER.info("Withdrawal of amount " + amount + " is failed");
			return atmTransactionStatus;
		}

	}

	//	Check ATM minimum balance after withdrawal of money
	private String checkForMinimumBalance(int atmId) {
		int count20 = atmDao.getATM(atmId).getSlot20().getDenominationAvailableCount();
		int count50 = atmDao.getATM(atmId).getSlot50().getDenominationAvailableCount();

		int count20Min = atmDao.getATM(atmId).getSlot20().getDenominationMinLimitForAlert();
		int count50Min = atmDao.getATM(atmId).getSlot50().getDenominationMinLimitForAlert();

		String Subject = "Refill ATM id " + atmId;
		String Body = null;

		//check for out of cash
		if (count20 == 0 && count50 == 0) {
			try {
				Body = "Atm Id " + atmId + " is running out of cash.";
				//Notify Bank admin in case of ATM out of cash
				emailService.sendMail(Subject, Body);
			} catch (MailException e) {
				LOGGER.error("Mail Sending is Failed...");
				LOGGER.error(e.getMessage());

			}
			return "OutOfCash";
			
			//check low cash for minimum threshold defined in ATM slot
		} else if ((count20 - count20Min) < 0 || (count50 - count50Min) < 0) {
			if ((count20 - count20Min) < 0 && (count50 - count50Min) < 0) {
				Body = "Atm Id " + atmId + " is having less than minimum limit."
						+ " Both $20 and $50 currency is less than threshold.";
			} else if ((count20 - count20Min) < 0) {
				Body = "Atm Id " + atmId + " is having less than minimum limit." + " $20 is less than threshold.";
			} else if ((count50 - count50Min) < 0) {
				Body = "Atm Id " + atmId + " is having less than minimum limit." + " $50 is less than threshold.";
			}
			try {
				//Notify Bank admin in case of ATM low balance
				emailService.sendMail(Subject, Body);
			} catch (MailException e) {
				LOGGER.error("Mail Sending is Failed...");
				LOGGER.error(e.getMessage());

			}
			return "LowBalance";

		} else {
			return "NoProblem";
		}

	}

}
