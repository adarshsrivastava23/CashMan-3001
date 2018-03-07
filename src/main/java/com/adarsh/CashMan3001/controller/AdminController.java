package com.adarsh.CashMan3001.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adarsh.CashMan3001.responseBeans.AtmBalance;
import com.adarsh.CashMan3001.responseBeans.AtmTransactionStatus;
import com.adarsh.CashMan3001.service.AdminService;

/* This is controller class for Bank Admin
 * 
 * When Security would be implemented that time a request filter can be added which will validate
 * all request starting from URL "/admin" for only Bank Admin
 * 
 * */
		
@RestController
@RequestMapping("/admin")
public class AdminController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	AdminService adminService;
	
	/*Bank Admin request for ATM balance for a ATM ID
	 * They will get response as total money along with count of $20 and $50 */
	@GetMapping("/atmBalance/{atmId}")
	public AtmBalance getAtmBalance(@PathVariable("atmId") int atmId){
		LOGGER.info("Admin checks ATM Balance of ATM Id " + atmId);
		return adminService.getATMBalance(atmId);
	}
	
	
	/*Bank Admin request for available ATM slot for refill
	 * They will get response as empty space for $20 and $50 to be refilled*/
	@GetMapping("/atmSlotSpace/{atmId}")
	public AtmTransactionStatus getAtmAvailableSlots(@PathVariable("atmId") int atmId){
		LOGGER.info("Admin checks for available space in ATM slots for refill  of ATM Id " + atmId);
		return adminService.getATMavailableSlotsCount(atmId);
	}
	
	/* Bank Admin request for refill ATM with count of $20 and $50
	 * They will get response as either success with updated  $20 and $50 note count or 
	 * failure because of refill amount is more than available space. They will also 
	 * get massage that maximum possible value for refill of $20 and $50 */
	@PutMapping("/refillAtm")
	public AtmTransactionStatus refillAtm(@RequestBody AtmTransactionStatus refillAmount){
		LOGGER.info("Bank admin refill ATM id " + refillAmount.getAtmId() + " and with " + refillAmount.getDenomination20() +
				" of $20 and " + refillAmount.getDenomination50() + "of $50");
		return adminService.refillAtm(refillAmount);
	}
	
	
	
	
	
}
