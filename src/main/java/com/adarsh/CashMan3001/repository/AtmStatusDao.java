package com.adarsh.CashMan3001.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.adarsh.CashMan3001.model.AtmStatus;

/*This Interface is defining basic CRUD operation of ATM status entity to be implemented*/

@Repository
@Transactional
public interface AtmStatusDao {
	
	public AtmStatus createAtmStatus(AtmStatus atmStatus);
		
	public AtmStatus getAtmStatus(String status);
	
	public List<AtmStatus> getAllAtmStatus();
	
	public void removeAtmStatus(AtmStatus atmStatus);
	

}
