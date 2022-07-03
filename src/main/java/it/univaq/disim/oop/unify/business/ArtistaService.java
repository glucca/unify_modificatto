package it.univaq.disim.oop.unify.business;

import java.util.List;

import it.univaq.disim.oop.unify.domain.Artista;

public interface ArtistaService {
	
	List<Artista> findAllArtisti() throws BusinessException;
	
	Artista findArtistaById(int id) throws BusinessException;
	
	void addArtista(Artista artista) throws BusinessException;
	
	void updateArtista(Artista artista) throws BusinessException;
	
	void removeArtista(Artista artista) throws BusinessException;
	
}
