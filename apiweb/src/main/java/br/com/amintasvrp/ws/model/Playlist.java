package br.com.amintasvrp.ws.model;

import java.util.ArrayList;
import java.util.Collection;

public class Playlist {
	
	private String nome;
	private Collection<String> tracklist;
	
	public Playlist(String nome) {
		this.nome = nome;
		this.tracklist = new ArrayList<>();
	}

	public String getNome() {
		return nome;
	}

	public Collection<String> getTracklist() {
		return tracklist;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setTracklist(Collection<String> tracklist) {
		this.tracklist = tracklist;
	}

}
