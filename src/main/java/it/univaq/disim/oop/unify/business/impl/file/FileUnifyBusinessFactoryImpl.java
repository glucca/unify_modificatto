package it.univaq.disim.oop.unify.business.impl.file;

import java.io.File;

import it.univaq.disim.oop.unify.business.AccountService;
import it.univaq.disim.oop.unify.business.ArtistaService;
import it.univaq.disim.oop.unify.business.CanzoneService;
import it.univaq.disim.oop.unify.business.UnifyBusinessFactory;



public class FileUnifyBusinessFactoryImpl extends UnifyBusinessFactory{
	
	private AccountService accountService;
	private ArtistaService artistaService;
	private CanzoneService canzoneService;
	
	
	private static final String REPOSITORY_BASE = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "dati";
	private static final String ACCOUNT_FILE_NAME = REPOSITORY_BASE + File.separator + "persone.txt";
	private static final String ARTISTA_FILE_NAME = REPOSITORY_BASE + File.separator + "artista.txt";
	private static final String CANZONE_FILE_NAME = REPOSITORY_BASE + File.separator + "canzone.txt";
	
	
	public FileUnifyBusinessFactoryImpl() {
			accountService = new FileAccountServiceImpl(ACCOUNT_FILE_NAME);
			artistaService = new FileArtistaServiceImpl(ARTISTA_FILE_NAME, accountService);
			canzoneService = new FileCanzoneServiceImpl(CANZONE_FILE_NAME, artistaService);
			
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
