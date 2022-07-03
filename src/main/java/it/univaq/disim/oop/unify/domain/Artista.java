package it.univaq.disim.oop.unify.domain;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.image.Image;

public class Artista {
	
	

	
	private int id;

    private String nome;

    private String biografia;

    private  byte[] foto;

	private Amministratore amministratore;

    //private Set<Canzone> canzoni = new HashSet<>();//sono tutte le canzoni riferite ad un artista specifico
    
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

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Amministratore getAmministratore() {
		return amministratore;
	}

	public void setAmministratore(Amministratore amministratore) {
		this.amministratore = amministratore;
	}

	/*
	public Set<Canzone> getCanzoni() {
		return canzoni;
	}

	public void setCanzoni(Set<Canzone> canzoni) {
		this.canzoni = canzoni;
	}
	*/

	public Set<Album> getDiscografia() {
		return discografia;
	}

	public void setDiscografia(Set<Album> discografia) {
		this.discografia = discografia;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	private Set<Album> discografia = new HashSet<>();

    private Utente utente;
    
    @Override
    public String toString() {
    	return nome;
    }
    
    
    
    
    
}
