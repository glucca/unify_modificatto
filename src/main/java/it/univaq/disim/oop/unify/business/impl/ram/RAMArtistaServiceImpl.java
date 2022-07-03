package it.univaq.disim.oop.unify.business.impl.ram;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.unify.business.ArtistaService;
import it.univaq.disim.oop.unify.business.BusinessException;
import it.univaq.disim.oop.unify.domain.Artista;

public class RAMArtistaServiceImpl implements ArtistaService{
	private static List<Artista> artisti = new ArrayList<>();
	
	@Override
	public List<Artista> findAllArtisti() throws BusinessException{
		return artisti;
	}
	
	@Override
	public Artista findArtistaById(int id) throws BusinessException{
		for(Artista artista : artisti)
			if(artista.getId() == id)
				return artista;
		return null;
	}
	
	@Override
	public void addArtista(Artista artista) throws BusinessException{
		artisti.add(artista);
	}
	
	@Override
	public void updateArtista(Artista artista) throws BusinessException{
		int x;
		Artista vecchioArtista = this.findArtistaById(artista.getId());
		x = artisti.indexOf(vecchioArtista);
		artisti.remove(x);
		artisti.add(x, artista);
	}
	
	@Override
	public void removeArtista(Artista artista) throws BusinessException{
		artisti.remove(artista);
	}
	
	
	

}
