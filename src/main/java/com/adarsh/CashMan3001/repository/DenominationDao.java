package com.adarsh.CashMan3001.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.adarsh.CashMan3001.model.Denomination;

/*This Interface is defining basic CRUD operation of ATM Denomination entity to be implemented*/

@Repository
@Transactional
public interface DenominationDao {
	
	public Denomination createDenomination(Denomination denomination);
	
	public Denomination updateDenominationCount(int denominationId, int denominationCount);
	
	public Denomination getDenomination(int denominationId);
	
	public List<Denomination> getAllDenominations();
	
	public void removeDenomination(Denomination denomination);
	
}
