package com.adarsh.CashMan3001.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adarsh.CashMan3001.responseBeans.AtmBalance;
import com.adarsh.CashMan3001.responseBeans.AtmTransactionStatus;
import com.adarsh.CashMan3001.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	AdminService adminService;
	
	
	@GetMapping("/atmBalance/{atmId}")
	public AtmBalance getAtmBalance(@PathVariable("atmId") int atmId){
		LOGGER.info("Admin check ATM Balance of ATM Id " + atmId);
		return adminService.getATMBalance(atmId);
	}
	
	@GetMapping("/atmSlotSpace/{atmId}")
	public AtmTransactionStatus getAtmAvailableSlots(@PathVariable("atmId") int atmId){
		LOGGER.info("Admin checks for available space in ATM slots for refill  of ATM Id " + atmId);
		return adminService.getATMavailableSlotsCount(atmId);
	}
	
	
	
	
}
