package it.univaq.disim.oop.unify.business.impl.ram;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.unify.business.AccountNotFoundException;
import it.univaq.disim.oop.unify.business.AccountService;
import it.univaq.disim.oop.unify.business.BusinessException;
import it.univaq.disim.oop.unify.domain.Account;
import it.univaq.disim.oop.unify.domain.Amministratore;
import it.univaq.disim.oop.unify.domain.Utente;

public class RAMAccountServiceImpl implements AccountService {
	
	private static List<Account> utentiRegistrati = new ArrayList<>();
	
	private static Integer idCounter = 3;
	
@Override
	public void registrazioneUtente(Account account) throws BusinessException {
		account.setId(++idCounter);
		utentiRegistrati.add(account);
	}
	
	
	@Override
	public Account authenticate(String username, String password) throws AccountNotFoundException {
		Account account = new Account();
		
		if ("am".equalsIgnoreCase(username) && "am".equalsIgnoreCase(password)) {// credenziali di prova admin
			account = new Amministratore();
			account.setUsername("amministratore");
			account.setPassword("pass");
			account.setNome("amministratore");
			account.setCognome("amministratore");
			return account;
		}
		if ("ut".equalsIgnoreCase(username) && "ut".equalsIgnoreCase(password)) {// credenziali di prova user
			account = new Utente();
			account.setUsername("utente");
			account.setPassword("pass");
			account.setNome("utente");
			account.setCognome("utente");
			return account;
		}
		
		for (Account utente : utentiRegistrati) {
			if (utente.getUsername().equalsIgnoreCase(username) || utente.getPassword().equalsIgnoreCase(password)) {
				return utente;
			}
		}
		throw new AccountNotFoundException();
	}

	@Override
	public List<Amministratore> findAmministratori() throws BusinessException {
		return findAmministratori();
	}

	@Override
	public List<Utente> findUtenti() throws BusinessException {
		return findUtenti();
	}

	@Override
	public Account findAccountById(int id) throws BusinessException {
		for (Account amministratore : utentiRegistrati)
			if (amministratore.getId() == id)
				return amministratore;
		for (Account utente : utentiRegistrati)
			if (utente.getId() == id)
				return utente;
		return null;
	}

	@Override
	public void addAccount(Account account) throws BusinessException {
		account.setId(this.ultimoId() + 1);
		if (account instanceof Account) {
			utentiRegistrati.add((Amministratore) account);
		} else if (account instanceof Utente) {
			utentiRegistrati.add((Utente) account);
		}
	}

	@Override
	public void updateAccount(Account account) throws BusinessException {
		if (account instanceof Account) {
			utentiRegistrati.remove(account.getId());
			utentiRegistrati.add(account.getId(), (Amministratore) account);
		} else if (account instanceof Utente) {
			utentiRegistrati.remove(account.getId());
			utentiRegistrati.add(account.getId(), (Utente) account);
		}
	}

	@Override
	public void removeAccount(Account account) throws BusinessException {
		int x;
		if (account instanceof Amministratore) {
			x = utentiRegistrati.indexOf(account);
			utentiRegistrati.remove(x);
		} else if (account instanceof Utente) {
			x = utentiRegistrati.indexOf(account);
			utentiRegistrati.remove(x);
		}
	}

	public int ultimoId() {
		int x = 0;

		for (Account amministratore : utentiRegistrati)
			if (amministratore.getId() > x)
				x = amministratore.getId();

		for (Account utente : utentiRegistrati)
			if (utente.getId() > x)
				x = utente.getId();
		return x;
	}
}































/*
public RAMAccountServiceImpl() throws BusinessException {
	Amministratore amministratore = new Amministratore();
	amministratore.setId(1);
	amministratore.setUsername("amministratore");
	amministratore.setPassword("pass");
	amministratore.setNome("amministratore");
	amministratore.setCognome("amministratore");
	amministratori.add(amministratore);
	Utente utente = new Utente();
	utente.setId(3);
	utente.setUsername("utente");
	utente.setPassword("pass");
	utente.setNome("utente");
	utente.setCognome("utente");
	utenti.add(utente);

}
*/
