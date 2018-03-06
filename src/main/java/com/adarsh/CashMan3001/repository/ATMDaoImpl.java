package com.adarsh.CashMan3001.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.adarsh.CashMan3001.model.ATM;

@Repository
@Transactional
public class ATMDaoImpl implements ATMDao {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public ATM createATM(ATM atm) {
		return entityManager.merge(atm);
	}

	@Override
	public ATM updateATM(ATM atm) {
		return entityManager.merge(atm);
	}

	@Override
	public ATM getATM(int ATMId) {
		return entityManager.find(ATM.class, ATMId);
	}

	@Override
	public List<ATM> getAllATM() {
		TypedQuery<ATM> namedQuery = entityManager.createNamedQuery("find_all_atm", ATM.class);
		return namedQuery.getResultList();
	}

	@Override
	public void removeATM(ATM atm) {
		entityManager.remove(atm);
		
	}
	



}
