package it.univaq.disim.oop.unify.business.impl.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.unify.business.ArtistaService;
import it.univaq.disim.oop.unify.business.BusinessException;
import it.univaq.disim.oop.unify.business.CanzoneService;
import it.univaq.disim.oop.unify.domain.Artista;
import it.univaq.disim.oop.unify.domain.Canzone;

public class FileCanzoneServiceImpl implements CanzoneService{
	private String canzoniFilename;
	private ArtistaService artistaService;
	
	
	public FileCanzoneServiceImpl(String canzoniFilename, ArtistaService artistaService) {
		this.canzoniFilename = canzoniFilename;
		this.artistaService = artistaService;
		
	}
	
	@Override
	public List<Canzone> findAllCanzoni() throws BusinessException{
		List<Canzone> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRighe(canzoniFilename);
			for(String[] colonne : fileData.getRighe()) {
				Canzone canzone = new Canzone();
				canzone.setId(Integer.parseInt(colonne[0]));
				canzone.setTitolo(colonne[1]);
				Artista artista = artistaService.findArtistaById(Integer.parseInt(colonne[2]));
				if (artista == null) {
					artista = new Artista();
					artista.setId(Integer.parseInt(colonne[2]));
					artista.setBiografia(colonne[3]);
					//artista.setDiscografia(colonne[4]);
					artista.setNome(colonne[5]);
				}
				canzone.setTesto((colonne[6]));
				result.add(canzone);
			}
		} catch (IOException e) {
			throw new BusinessException();
		}
		return result;
	}
	
	@Override
	public Canzone findCanzoneById(int id) throws BusinessException{
		Canzone canzone = new Canzone();
		try {
			FileData fileData = Utility.readAllRighe(canzoniFilename);
			for (String[] colonne : fileData.getRighe()) {
				if (Integer.parseInt(colonne[0]) == id) {
					canzone.setId(Integer.parseInt(colonne[0]));
					canzone.setTitolo(colonne[1]);
					//canzone.getArtista().setNome(colonne[2]);
					canzone.setTesto((colonne[3]));
	
				}
			}
		} catch (IOException e) {
			throw new BusinessException();
		}
		return canzone;
	}
	
	@Override
	public void addCanzone(Canzone canzone) throws BusinessException{
		try {
			FileData fileData = Utility.readAllRighe(canzoniFilename);
			try (PrintWriter writer = new PrintWriter (new File(canzoniFilename))){
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
				row.append(canzone.getTitolo().toString());
				row.append(Utility.SEPARATORE_COLONNA);
				//row.append(canzone.getArtista().getNome());
				row.append(Utility.SEPARATORE_COLONNA);
				row.append(canzone.getTesto().toString());
				row.append(Utility.SEPARATORE_COLONNA);
				writer.println(row);
			}
		} catch (IOException e) {
			throw new BusinessException();
		}
	}
	
	@Override
	public void updateCanzone(Canzone canzone) throws BusinessException{
		try {
			FileData fileData = Utility.readAllRighe(canzoniFilename);
			try (PrintWriter writer = new PrintWriter(new File(canzoniFilename))) {
				writer.println(fileData.getContatore());
				for (String[] righe : fileData.getRighe()) {
					if (Long.parseLong(righe[0]) != canzone.getId()){
						writer.println(String.join(Utility.SEPARATORE_COLONNA, righe));
					} else if (Long.parseLong(righe[0]) == canzone.getId()){
						StringBuilder row = new StringBuilder();
						row.append(canzone.getId());
						row.append(Utility.SEPARATORE_COLONNA);
						row.append(canzone.getTitolo().toString());
						row.append(Utility.SEPARATORE_COLONNA);
						//row.append(canzone.getArtista().getNome());
						row.append(Utility.SEPARATORE_COLONNA);
						row.append(canzone.getTesto().toString());
						row.append(Utility.SEPARATORE_COLONNA);
						writer.println(row);
					}
				}
			}
		} catch (IOException e) {
			throw new BusinessException();
		}
	}
	
	@Override
	public void removeCanzone(Canzone canzone) throws BusinessException{
		try {
			FileData fileData = Utility.readAllRighe(canzoniFilename);
			try (PrintWriter writer = new PrintWriter(new File(canzoniFilename))){
				writer.println(fileData.getContatore() - 1);
				forEnd: for (String[] righe : fileData.getRighe()) {
					if (Long.parseLong(righe[0]) < canzone.getId()) {
						writer.println(String.join(Utility.SEPARATORE_COLONNA, righe));
					} else if (Long.parseLong(righe[0]) > canzone.getId()) {
						writer.println(String.join(Utility.SEPARATORE_COLONNA, righe));
					} else if (Long.parseLong(righe[0]) == fileData.getContatore() - 1) {
						break forEnd;
					}
				}
			}
		} catch (IOException e) {
			throw new BusinessException();
		}
	}
	
}