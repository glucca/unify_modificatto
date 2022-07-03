package it.univaq.disim.oop.unify.business;

import java.util.List;

import it.univaq.disim.oop.unify.domain.Account;
import it.univaq.disim.oop.unify.domain.Amministratore;
import it.univaq.disim.oop.unify.domain.Utente;

public interface AccountService {
	Account authenticate(String username, String password) throws AccountNotFoundException, BusinessException;
	
	List<Amministratore> findAmministratori() throws BusinessException;
	
	List<Utente> findUtenti() throws BusinessException;
	
	Account findAccountById (int id) throws BusinessException;
	
	void addAccount(Account account) throws BusinessException;
	
	void updateAccount(Account account) throws BusinessException;
	
	void removeAccount(Account account) throws BusinessException;
	
	public void registrazioneUtente(Account account) throws BusinessException;
	}

