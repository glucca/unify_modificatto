package it.univaq.disim.oop.unify.business.impl.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.unify.business.AccountNotFoundException;
import it.univaq.disim.oop.unify.business.AccountService;
import it.univaq.disim.oop.unify.business.BusinessException;
import it.univaq.disim.oop.unify.domain.Account;
import it.univaq.disim.oop.unify.domain.Amministratore;
import it.univaq.disim.oop.unify.domain.Utente;



public class FileAccountServiceImpl  implements AccountService {
	private String accountFilename;
	
	public FileAccountServiceImpl (String accountFilename) {
		this.accountFilename = accountFilename;
	}
	
	@Override
	public Account authenticate(String username, String password) throws AccountNotFoundException, BusinessException{
		try {
			FileData fileData = Utility.readAllRighe(accountFilename);
			for (String[] colonne : fileData.getRighe()) {
				if (colonne[1].equals(username) && colonne[2].equals(password)) {
					Account account = null;
					switch(colonne[3]) {
					case "amministratore":
						account = new Amministratore();
						break;
					case "utente":
						account = new Utente();
				}
				account = this.findAccountById(Integer.parseInt(colonne[0]));
				return account;
			}
		}
		throw new AccountNotFoundException();
	} catch (IOException e) {
		throw new BusinessException(e);
		}
	}
	
	@Override
	public List<Amministratore> findAmministratori() throws BusinessException{
		List<Amministratore> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRighe(accountFilename);
			for (String[] colonne : fileData.getRighe()) {
				if(colonne[3].equals("amministratore")) {
					Amministratore amministratore = new Amministratore();
					amministratore.setId(Integer.parseInt(colonne[0]));
					amministratore.setUsername(colonne[1]);
					amministratore.setPassword(colonne[2]);
					amministratore.setNome(colonne[4]);
					amministratore.setCognome(colonne[5]);
					result.add(amministratore);
				}
			}
		} catch (IOException e) {
			throw new BusinessException();
		}
		return result;
	}
	
	public List<Utente> findUtenti() throws BusinessException{
		List<Utente> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRighe(accountFilename);
			for (String[] colonne : fileData.getRighe()) {
				if(colonne[3].equals("utente")) {
					Utente utente = new Utente();
					utente.setId(Integer.parseInt(colonne[0]));
					utente.setUsername(colonne[1]);
					utente.setPassword(colonne[2]);
					utente.setNome(colonne[4]);
					utente.setCognome(colonne[5]);
					result.add(utente);
				}
			}
		} catch (IOException e) {
			throw new BusinessException();
		}
		return result;
	}
	
	@Override
	public Account findAccountById(int id) throws BusinessException{
		Account account = new Account();
		try {
			FileData fileData = Utility.readAllRighe(accountFilename);
			for (String[] colonne : fileData.getRighe()) {
				if(Integer.parseInt(colonne[0]) == id) {
					switch(colonne[3]) {
					case "amministratore":
						account = new Amministratore();
						((Amministratore) account).setId(id);
						((Amministratore) account).setUsername(colonne[1]);
						((Amministratore) account).setPassword(colonne[2]);
						((Amministratore) account).setNome(colonne[4]);
						((Amministratore) account).setCognome(colonne[5]);
						return account;
					case "utente":
						account = new Utente();
						((Utente) account).setId(id);
						((Utente) account).setUsername(colonne[1]);
						((Utente) account).setPassword(colonne[2]);
						((Utente) account).setNome(colonne[4]);
						((Utente) account).setCognome(colonne[5]);
						return account;
					default:
						break;
					}
				}
			}
		} catch (IOException e) {
			throw new BusinessException();
		}
		return account = null;
	}
	
	@Override
	public void addAccount(Account account) throws BusinessException{
		try {
			FileData fileData = Utility.readAllRighe(accountFilename);
			try (PrintWriter writer = new PrintWriter(new File(accountFilename))){
				long contatore = fileData.getContatore();
				writer.println(contatore + 1);
				int ultimoId = 0;
				for (String[] righe : fileData.getRighe()) {
					writer.println(String.join(Utility.SEPARATORE_COLONNA, righe));
					ultimoId = Integer.parseInt(righe[0]);
				}
				StringBuilder row = new StringBuilder();
				row.append(ultimoId + 1);
				row.append(Utility.SEPARATORE_COLONNA);
				row.append(account.getUsername());
				row.append(Utility.SEPARATORE_COLONNA);
				row.append(account.getPassword());
				row.append(Utility.SEPARATORE_COLONNA);
				if (account instanceof Utente) {
					row.append("utente");
				}
				row.append(Utility.SEPARATORE_COLONNA);
				row.append(account.getNome());
				row.append(Utility.SEPARATORE_COLONNA);
				row.append(account.getCognome());
				row.append(Utility.SEPARATORE_COLONNA);
				writer.println(row.toString());
			}
		} catch (IOException e) {
			throw new BusinessException();
		}
	}
	
	@Override
	public void updateAccount(Account account) throws BusinessException{
		try {
			FileData fileData = Utility.readAllRighe(accountFilename);
			try (PrintWriter writer = new PrintWriter(new File(accountFilename))){
				writer.println(fileData.getContatore());
				for (String[] righe : fileData.getRighe()) {
					if (Long.parseLong(righe[0]) != account.getId()) {
						writer.println(String.join(Utility.SEPARATORE_COLONNA, righe));
					}
					if(Long.parseLong(righe[0]) == account.getId()) {
						StringBuilder row = new StringBuilder();
						row.append(account.getId());
						row.append(Utility.SEPARATORE_COLONNA);
						row.append(account.getUsername());
						row.append(Utility.SEPARATORE_COLONNA);
						row.append(account.getPassword());
						row.append(Utility.SEPARATORE_COLONNA);
						if(account instanceof Amministratore) {
							row.append("amministratore");
						}
						if(account instanceof Utente) {
							row.append("utente");
						}
						row.append(Utility.SEPARATORE_COLONNA);
						row.append(account.getNome());
						row.append(Utility.SEPARATORE_COLONNA);
						row.append(account.getCognome());
						row.append(Utility.SEPARATORE_COLONNA);
						writer.println(row);
					}
				}
			}
		} catch(IOException e) {
			throw new BusinessException();
		}
	}
	
	@Override
	public void removeAccount(Account account) throws BusinessException{
		try {
			FileData fileData = Utility.readAllRighe(accountFilename);
			try(PrintWriter writer = new PrintWriter(new File(accountFilename))){
				writer.println(fileData.getContatore() - 1);
				forEnd: for (String[] righe : fileData.getRighe()) {
					if (Long.parseLong(righe[0]) < account.getId()) {
						writer.println(String.join(Utility.SEPARATORE_COLONNA, righe));
					} else if(Long.parseLong(righe[0]) > account.getId()) {
						writer.println(String.join(Utility.SEPARATORE_COLONNA, righe));
					} else if(Long.parseLong(righe[0]) == fileData.getContatore() - 1) {
						break forEnd;
					}
				}
			}
		} catch (IOException e) {
			throw new BusinessException();
		}
	}

	@Override
	public void registrazioneUtente(Account account) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
	
}

