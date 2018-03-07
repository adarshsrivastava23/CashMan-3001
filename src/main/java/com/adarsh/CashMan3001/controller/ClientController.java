package com.adarsh.CashMan3001.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adarsh.CashMan3001.model.AtmStatus;
import com.adarsh.CashMan3001.responseBeans.AtmTransactionStatus;
import com.adarsh.CashMan3001.service.ClientService;

/* This is a controller class for Bank Client
 * 
 * When security would be implemented that time a request filter could be added 
 * to validate clients ATM Card Details and provided PIN Number for URL starting from "/client.
 * 
 * */

@RestController
@RequestMapping("/client")
public class ClientController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);
	
	@Autowired
	ClientService clientService;
	
	/* Bank Client wanted to Know the status of ATM
	 * ATM can have 4 status.
	 * 1. Working
	 * 2. OutOfOrder
	 * 3. OutOfCash
	 * 4. LowBalance 
	 * */
	@GetMapping("/atmStatus/{atmId}")
	public AtmStatus getAtmStatus(@PathVariable("atmId") int atmId){
		LOGGER.info("client checks ATM status of ATM Id " + atmId);
		return clientService.getATMStatusFromATM(atmId);
	}
	
	/* Bank Client requested to withdraw Money from ATM 
	 * He will get response with success and number of note withdrawn for $20 and $50
	 * or failure due to requested money could not be provided from ATM 
	 * In case of failure it will not reduce money from ATM balance*/
	@GetMapping("/withdraw/{atmId}/{amount}")
	public AtmTransactionStatus withdrawMoney(@PathVariable("atmId") int atmId, @PathVariable("amount") int amount){
		LOGGER.info("client trying to withdraw from ATM ID " + atmId + " and amount = " + amount);
		return clientService.withdrawMoney(atmId,amount);
	}

}
