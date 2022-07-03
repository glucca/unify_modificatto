package it.univaq.disim.oop.unify.business;

import it.univaq.disim.oop.unify.business.impl.ram.RamUnifyBusinessFactoryImpl;

public abstract class UnifyBusinessFactory {
	
	private static UnifyBusinessFactory factory = new RamUnifyBusinessFactoryImpl(); 
	
	// private static UnifyBusinessFactory factory = new FileUnifyBusinessFactoryImpl();
	
	public static UnifyBusinessFactory getIstance() {
		return factory;
	}
	
	public abstract AccountService getAccountService();
	public abstract ArtistaService getArtistaService();
	public abstract CanzoneService getCanzoneService();
}


