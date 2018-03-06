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

@RestController
@RequestMapping("/client")
public class ClientController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);
	
	@Autowired
	ClientService clientService;
	
	
	@GetMapping("/atmStatus/{atmId}")
	public AtmStatus getAtmStatus(@PathVariable("atmId") int atmId){
		LOGGER.info("client checks ATM status of ATM Id " + atmId);
		return clientService.getATMStatusFromATM(atmId);
	}
	
	@GetMapping("/withdraw/{atmId}/{amount}")
	public AtmTransactionStatus withdrawMoney(@PathVariable("atmId") int atmId, @PathVariable("amount") int amount){
		LOGGER.info("client trying to withdraw from ATM ID " + atmId + " and amount = " + amount);
		return clientService.withdrawMoney(atmId,amount);
	}

}
