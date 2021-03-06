package com.adarsh.CashMan3001.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.adarsh.CashMan3001.model.AtmStatus;

/*This class has basic implementation of AtmStatusDao Interface*/

@Repository
@Transactional
public class AtmStatusDaoImpl implements AtmStatusDao {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public AtmStatus createAtmStatus(AtmStatus atmStatus) {
		return entityManager.merge(atmStatus);
	}

	@Override
	public AtmStatus getAtmStatus(String status) {
		return entityManager.find(AtmStatus.class, status);
	}

	@Override
	public List<AtmStatus> getAllAtmStatus() {
		TypedQuery<AtmStatus> namedQuery = entityManager.createNamedQuery("find_all_atm_status", AtmStatus.class);
		return namedQuery.getResultList();
	}

	@Override
	public void removeAtmStatus(AtmStatus atmStatus) {
		entityManager.remove(atmStatus);
		
	}


}
