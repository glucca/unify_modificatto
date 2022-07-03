package it.univaq.disim.oop.unify.business.impl.ram;

import it.univaq.disim.oop.unify.business.AccountService;
import it.univaq.disim.oop.unify.business.ArtistaService;
import it.univaq.disim.oop.unify.business.CanzoneService;
import it.univaq.disim.oop.unify.business.UnifyBusinessFactory;

public class RamUnifyBusinessFactoryImpl extends UnifyBusinessFactory {

	private AccountService accountService;
	private ArtistaService artistaService;
	private CanzoneService canzoneService;

	public RamUnifyBusinessFactoryImpl() {
		accountService = new RAMAccountServiceImpl();
		artistaService = new RAMArtistaServiceImpl();
		canzoneService = new RAMCanzoneServiceImpl();
	}

	@Override
	public AccountService getAccountService() {
		return accountService;
	}

	@Override
	public ArtistaService getArtistaService() {
		return artistaService;
	}

	@Override
	public CanzoneService getCanzoneService() {
		return canzoneService;
	}

}
