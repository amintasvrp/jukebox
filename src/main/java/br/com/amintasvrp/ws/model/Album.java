package br.com.amintasvrp.ws.model;

import java.util.ArrayList;
import java.util.Collection;

public class Album {
	
	private String nome;
	private Collection<Musica> tracklist;
	
	public Album(String nome) {
		this.nome = nome;
		this.tracklist = new ArrayList<>();
	}

	public String getNome() {
		return nome;
	}

	public Collection<Musica> getTracklist() {
		return tracklist;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setTracklist(Collection<Musica> tracklist) {
		this.tracklist = tracklist;
	}
	
}
