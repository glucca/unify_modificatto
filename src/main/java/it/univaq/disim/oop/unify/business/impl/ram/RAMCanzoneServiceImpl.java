package it.univaq.disim.oop.unify.business.impl.ram;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.unify.business.BusinessException;
import it.univaq.disim.oop.unify.business.CanzoneService;
import it.univaq.disim.oop.unify.domain.Canzone;

public class RAMCanzoneServiceImpl implements CanzoneService {

	private static List<Canzone> canzoniAggiunte = new ArrayList<>();


	@Override
	public List<Canzone> findAllCanzoni() throws BusinessException {
		return canzoniAggiunte;
	}
	
	@Override
	public Canzone findCanzoneById(int id) throws BusinessException{
		for (Canzone canzone : canzoniAggiunte)
			if (canzone.getId() == id)
				return canzone;
		return null;
	}
	

	@Override
	public void addCanzone(Canzone canzone) throws BusinessException {
		canzoniAggiunte.add(canzone);
	}

	@Override
	public void updateCanzone(Canzone canzone) throws BusinessException {
		int i;
		Canzone canzoneVecchia = this.findCanzoneById(canzone.getId());
		i = canzoniAggiunte.indexOf(canzoneVecchia);
		canzoniAggiunte.remove(i);
		canzoniAggiunte.add(i, canzone);
	}
	
	@Override
	public void removeCanzone(Canzone canzone) throws BusinessException{
		canzoniAggiunte.remove(canzone);
	}

}