package it.univaq.disim.oop.unify.domain;

public class Canzone {
	
	private int id;
    
    private String titolo;
    
    private Artista nome;
    
    private String testo;
    
    private Genere genere;
    
    private String path;
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public Artista getNome() {
		return nome;
	}

	public void setNome(Artista nome) {
		this.nome = nome;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public Genere getGenere() {
		return genere;
	}

	public void setGenere(Genere genere) {
		this.genere = genere;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

	private Album album;
    
    private Playlist playlist;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

    
	
    
   
    
    
}
