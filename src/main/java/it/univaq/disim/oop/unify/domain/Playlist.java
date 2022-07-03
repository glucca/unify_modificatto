package it.univaq.disim.oop.unify.domain;

import java.util.HashSet;
import java.util.Set;

public class Playlist {
	
	private int id;

	private String nome;

	private Set<Canzone> canzoni = new HashSet<>();

	private Utente utente;

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

	public Set<Canzone> getCanzoni() {
		return canzoni;
	}

	public void setCanzoni(Set<Canzone> canzoni) {
		this.canzoni = canzoni;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	
	
	

}
