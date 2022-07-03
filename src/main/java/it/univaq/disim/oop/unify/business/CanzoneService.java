package it.univaq.disim.oop.unify.business;

import java.util.List;

import it.univaq.disim.oop.unify.domain.Canzone;

public interface CanzoneService {
	List<Canzone> findAllCanzoni() throws BusinessException;

	void addCanzone(Canzone canzone) throws BusinessException;

	void updateCanzone(Canzone canzone) throws BusinessException;

	void removeCanzone(Canzone canzone) throws BusinessException;

	Canzone findCanzoneById(int id) throws BusinessException;
	
}
