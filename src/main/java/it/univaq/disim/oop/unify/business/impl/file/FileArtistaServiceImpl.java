package it.univaq.disim.oop.unify.business.impl.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.unify.business.AccountService;
import it.univaq.disim.oop.unify.business.ArtistaService;
import it.univaq.disim.oop.unify.business.BusinessException;
import it.univaq.disim.oop.unify.domain.Account;
import it.univaq.disim.oop.unify.domain.Artista;
import it.univaq.disim.oop.unify.domain.Utente;

public class FileArtistaServiceImpl implements ArtistaService{
	private String artistiFilename;
	private AccountService accountService;
	
	public FileArtistaServiceImpl(String artistiFilename, AccountService accountService) {
		this.accountService = accountService;
	}
	
	@Override
	public List<Artista> findAllArtisti() throws BusinessException{
		List<Artista> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRighe(artistiFilename);
			for (String[] colonne : fileData.getRighe()) {
				Artista artista = new Artista();
				artista.setId(Integer.parseInt(colonne[0]));
				artista.setNome((colonne[1]));//*
				artista.setBiografia((colonne[2]));//*
				//artista.setDiscografia((colonne[3]));//*
				//artista.setFoto((colonne[4]));//*
				Account utente = accountService.findAccountById(Integer.parseInt(colonne[7]));
				if (utente == null) {
					utente = new Utente();
					utente.setId(Integer.parseInt(colonne[7]));
					utente.setNome(colonne[8]);
					utente.setCognome(colonne[9]);
				}
				result.add(artista);
			}
		} catch(IOException e) {
			throw new BusinessException();
		}
		return result;
	}
	
	@Override
	public Artista findArtistaById(int id) throws BusinessException{
		Artista artista = new Artista();
		try {
			FileData fileData = Utility.readAllRighe(artistiFilename);
			for (String[] colonne : fileData.getRighe()){
				if (Integer.parseInt(colonne[0]) == id) {
					artista.setId(Integer.parseInt(colonne[0]));
					artista.setNome((colonne[1]));//*
					artista.setBiografia((colonne[2]));//*
					//artista.setDiscografia((colonne[3]));//*
					//artista.setFoto((colonne[4]));//*
					Account utente = accountService.findAccountById(Integer.parseInt(colonne[5]));
					if (utente == null) {
						utente = new Utente();
						utente.setId(Integer.parseInt(colonne[6]));
						utente.setNome(colonne[7]);
						utente.setCognome(colonne[8]);
					}
				}
			}
		} catch(IOException e) {
			throw new BusinessException();
		}
		return artista;
	}
	
	@Override
	public void addArtista(Artista artista) throws BusinessException{
		try {
			FileData fileData = Utility.readAllRighe(artistiFilename);
			try(PrintWriter writer = new PrintWriter(new File(artistiFilename))){
				long contatore = fileData.getContatore();
				writer.println((contatore + 1));
				int ultimoId = 0;
				for (String[] righe : fileData.getRighe()) {
					writer.println(String.join(Utility.SEPARATORE_COLONNA, righe));
					ultimoId = Integer.parseInt(righe[0]);
				}
				StringBuilder row = new StringBuilder();
				row.append(ultimoId + 1);
				row.append(Utility.SEPARATORE_COLONNA);
				row.append(artista.getNome().toString());
				row.append(Utility.SEPARATORE_COLONNA);
				row.append(artista.getBiografia().toString());
				row.append(Utility.SEPARATORE_COLONNA);
				row.append(artista.getDiscografia().toString());
				row.append(Utility.SEPARATORE_COLONNA);
				row.append(artista.getFoto().toString());
				row.append(Utility.SEPARATORE_COLONNA);
				writer.println(row.toString());
			}
		} catch(IOException e) {
			throw new BusinessException();
		}
	}
	
	@Override
	public void updateArtista(Artista artista) throws BusinessException{
		try {
			FileData fileData = Utility.readAllRighe(artistiFilename);
			try (PrintWriter writer = new PrintWriter(new File(artistiFilename))){
				writer.println(fileData.getContatore());
				for(String[] righe : fileData.getRighe()) {
					if (Long.parseLong(righe[0]) != artista.getId()) {
						writer.println(String.join(Utility.SEPARATORE_COLONNA, righe));
					} else if(Long.parseLong(righe[0]) == artista.getId()) {
						StringBuilder row = new StringBuilder();
						row.append(artista.getId());
						row.append(Utility.SEPARATORE_COLONNA);
						row.append(artista.getNome().toString());
						row.append(Utility.SEPARATORE_COLONNA);
						row.append(artista.getBiografia().toString());
						row.append(Utility.SEPARATORE_COLONNA);
						row.append(artista.getDiscografia().toString());
						row.append(Utility.SEPARATORE_COLONNA);
						row.append(artista.getFoto().toString());
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
	public void removeArtista(Artista artista) throws BusinessException{
		try {
			FileData fileData = Utility.readAllRighe(artistiFilename);
			try (PrintWriter writer = new PrintWriter(new File(artistiFilename))){
				writer.println(fileData.getContatore() - 1);
				forEnd: for (String[] righe : fileData.getRighe()) {
					if(Long.parseLong(righe[0]) < artista.getId()) {
						writer.println(String.join(Utility.SEPARATORE_COLONNA, righe));
					} else if(Long.parseLong(righe[0]) > artista.getId()) {
						writer.println(String.join(Utility.SEPARATORE_COLONNA, righe));
					} else if(Long.parseLong(righe[0]) == fileData.getContatore() - 1) {
						break forEnd;
					}
				}
			}
		} catch(IOException e) {
			throw new BusinessException();
		}
	}
}
