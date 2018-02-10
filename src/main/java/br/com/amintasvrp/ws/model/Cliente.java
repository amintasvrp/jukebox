package br.com.amintasvrp.ws.model;

import java.util.ArrayList;
import java.util.Collection;

public class Cliente {

	private String email;
	private String senha;
	private String nome;
	private Collection<Artista> artistas;
	private Collection<Musica> musicas;
	private Collection<Playlist> playlists;

	public Cliente(String email, String senha, String nome) {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.artistas = new ArrayList<>();
		this.musicas = new ArrayList<>();
		this.playlists = new ArrayList<>();
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public String getNome() {
		return nome;
	}

	public Collection<Artista> getArtistas() {
		return artistas;
	}

	public Collection<Musica> getMusicas() {
		return musicas;
	}

	public Collection<Playlist> getPlaylists() {
		return playlists;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setArtistas(Collection<Artista> artistas) {
		this.artistas = artistas;
	}

	public void setMusicas(Collection<Musica> musicas) {
		this.musicas = musicas;
	}

	public void setPlaylists(Collection<Playlist> playlists) {
		this.playlists = playlists;
	}

}
