package br.com.amintasvrp.ws.model;

import java.util.ArrayList;
import java.util.Collection;

public class Artista {
	
	private String nota;
	private boolean favoritado;
	private String url;
	private String nome;
	private Collection<Album> albuns;
	private Collection<Musica> musicasRecentes;
	
	public Artista(String nota, boolean favoritado, String foto, String nome) {
		this.nota = nota;
		this.favoritado = favoritado;
		this.url = foto;
		this.nome = nome;
		this.albuns = new ArrayList<>();
		this.musicasRecentes = new ArrayList<>();
	}

	public String getNota() {
		return nota;
	}

	public boolean isFavoritado() {
		return favoritado;
	}

	public String getUrl() {
		return url;
	}

	public String getNome() {
		return nome;
	}

	public Collection<Album> getAlbuns() {
		return albuns;
	}

	public Collection<Musica> getMusicasRecentes() {
		return musicasRecentes;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public void setFavoritado(boolean favoritado) {
		this.favoritado = favoritado;
	}

	public void setUrl(String foto) {
		this.url = foto;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setAlbuns(Collection<Album> albuns) {
		this.albuns = albuns;
	}

	public void setMusicasRecentes(Collection<Musica> musicasRecentes) {
		this.musicasRecentes = musicasRecentes;
	}
	
}
