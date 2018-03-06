package com.adarsh.CashMan3001.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.adarsh.CashMan3001.model.Denomination;

@Repository
@Transactional
public class DenominationDaoImpl implements DenominationDao {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Denomination createDenomination(Denomination denomination) {
		return entityManager.merge(denomination);
	}

	@Override
	public Denomination updateDenominationCount(int denominationId, int denominationCount) {
		Denomination denomination = entityManager.find(Denomination.class, denominationId);
		denomination.setDenominationAvailableCount(denominationCount);
		return entityManager.merge(denomination);
	}

	@Override
	public Denomination getDenomination(int denominationId) {
		return entityManager.find(Denomination.class, denominationId);
	}

	@Override
	public List<Denomination> getAllDenominations() {
		TypedQuery<Denomination> namedQuery = entityManager.createNamedQuery("find_all_denomination", Denomination.class);
		return namedQuery.getResultList();
	}

	@Override
	public void removeDenomination(Denomination denomination) {
		entityManager.remove(denomination);
		
	}


}
