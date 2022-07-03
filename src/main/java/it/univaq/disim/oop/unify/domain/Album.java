package it.univaq.disim.oop.unify.domain;

import java.util.HashSet;
import java.util.Set;

public class Album {
	
    private int id;

    private String nome;

    private String biografia;

    private Genere genere;

    private byte[] foto;
    
    private Artista artista;

    private Set<Genere> generi = new HashSet<>();

    private Set<Canzone> canzoni = new HashSet<>();

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

	public Genere getGenere() {
		return genere;
	}

	public void setGenere(Genere genere) {
		this.genere = genere;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	public Set<Genere> getGeneri() {
		return generi;
	}

	public void setGeneri(Set<Genere> generi) {
		this.generi = generi;
	}

	public Set<Canzone> getCanzoni() {
		return canzoni;
	}

	public void setCanzoni(Set<Canzone> canzoni) {
		this.canzoni = canzoni;
	}
	
    
    
}
