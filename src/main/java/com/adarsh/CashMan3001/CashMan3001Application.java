package com.adarsh.CashMan3001;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.adarsh.CashMan3001.model.ATM;
import com.adarsh.CashMan3001.model.AtmStatus;
import com.adarsh.CashMan3001.model.Denomination;
import com.adarsh.CashMan3001.repository.ATMDao;
import com.adarsh.CashMan3001.repository.AtmStatusDao;
import com.adarsh.CashMan3001.repository.DenominationDao;

@SpringBootApplication
public class CashMan3001Application implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(CashMan3001Application.class);
	
	@Autowired
	DenominationDao denominationService;
	
	@Autowired
	AtmStatusDao AtmStatusService;
	
	@Autowired
	ATMDao atmDao;

	
	public static void main(String[] args) {
		SpringApplication.run(CashMan3001Application.class, args);
		LOGGER.info("Application stated...");
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Denomination denomination20 = new Denomination(20, 24, 100, 10);	
		Denomination denomination50 = new Denomination(50, 15, 100, 10);	
		
		LOGGER.info("created 20 denomination slot for ATM...");
		denomination20 = denominationService.createDenomination(denomination20);
		LOGGER.info("created 20 denomination slot for ATM...");
		denomination50 = denominationService.createDenomination(denomination50);
		
		AtmStatus WorkingAtmStatus = new AtmStatus("Working", "ATM is working and sufficient currency");
		AtmStatus OutOfOrderAtmStatus = new AtmStatus("OutOfOrder", "ATM is not working due to technical reason");
		AtmStatus OutOfCashAtmStatus = new AtmStatus("OutOfCash", "ATM is working but out of cash. Please Refill immediately");
		AtmStatus LowCashAtmStatus = new AtmStatus("LowCash", "ATM is working and running with low cash. Please refill ASAP.");
		
		LOGGER.info("created 4 ATM status. Working, OutOfOrder, OutOfCash and LowCash. Working is default status...");
		WorkingAtmStatus = AtmStatusService.createAtmStatus(WorkingAtmStatus);
		OutOfOrderAtmStatus = AtmStatusService.createAtmStatus(OutOfOrderAtmStatus);
		OutOfCashAtmStatus = AtmStatusService.createAtmStatus(OutOfCashAtmStatus);
		LowCashAtmStatus = AtmStatusService.createAtmStatus(LowCashAtmStatus);
		
		
		ATM atm = new ATM(1, denomination20, denomination50, WorkingAtmStatus);
		LOGGER.info("created one ATM instance with ID 1...");
		atmDao.createATM(atm);
		
		

		
	}
}
