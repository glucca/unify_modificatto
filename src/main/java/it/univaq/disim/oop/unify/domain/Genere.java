package it.univaq.disim.oop.unify.domain;

public class Genere {
	
	private int id;
	
    private String nome;
    
    private Canzone canzone ; //forse non servono
    
    private Album album ; // forse non servono

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Canzone getCanzone() {
		return canzone;
	}

	public void setCanzone(Canzone canzone) {
		this.canzone = canzone;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
	
	 @Override
	    public String toString() {
	    	return nome;
	    }
    
    
    
    
    
}
