package com.adarsh.CashMan3001.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.adarsh.CashMan3001.model.AtmStatus;



@Repository
@Transactional
public interface AtmStatusDao {
	
	public AtmStatus createAtmStatus(AtmStatus atmStatus);
		
	public AtmStatus getAtmStatus(int atmStatusId);
	
	public List<AtmStatus> getAllAtmStatus();
	
	public void removeAtmStatus(AtmStatus atmStatus);
	

}
