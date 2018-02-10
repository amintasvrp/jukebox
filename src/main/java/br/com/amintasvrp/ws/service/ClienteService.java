package br.com.amintasvrp.ws.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.amintasvrp.ws.model.*;

import br.com.amintasvrp.ws.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	HashMap<String, Cliente> clienteBD = new HashMap<>();
	
	public Cliente cadastrarCliente(Cliente cliente) {
		clienteBD.put(cliente.getEmail() + cliente.getSenha(), cliente);
		return cliente;
	}
	
	public Collection<Cliente> obterListaClientes(){
		return clienteBD.values();
	}
	
	public Cliente obterCliente(String emailSenha){
		return clienteBD.get(emailSenha);
	}
	
	public Collection<Artista> obterListaArtistas(Cliente cliente){
		if (cliente != null) {
			return cliente.getArtistas();
		} else {
			return new ArrayList<Artista>();
		}
	}
	
	public Artista cadastrarArtista(Cliente cliente, Artista artista) {
		cliente.getArtistas().add(artista);
		return artista;
	}
	
	public Collection<Musica> obterListaMusicas(Cliente cliente){
		if (cliente != null) {
			return cliente.getMusicas();
		} else {
			return new ArrayList<Musica>();
		}
	}
	
	public Musica cadastrarMusica(Cliente cliente, Musica musica) {
		cliente.getMusicas().add(musica);
		Collection<Artista> artistas = cliente.getArtistas();
		for (Artista artista : artistas) {
			if (artista.getNome().equals(musica.getArtista())) {
				boolean novoAlbum = true;
				artista.getMusicasRecentes().add(musica);
				Collection<Album> albuns = artista.getAlbuns();
				for (Album album : albuns) {
					if (album.getNome().equals(musica.getAlbum())) {
						album.getTracklist().add(musica);
						novoAlbum = false;
					}										
				}
				if (novoAlbum) {
					Album album = new Album(musica.getAlbum());
					album.getTracklist().add(musica);
					artista.getAlbuns().add(album);
				}				
			}
		}
		return musica;
	}

	public Collection<Playlist> obterListaPlaylists(Cliente cliente) {
		if (cliente != null) {
			return cliente.getPlaylists();
		} else {
			return new ArrayList<Playlist>();
		}
	}
	
	public Playlist cadastrarMusicaPlaylist(Cliente cliente, String musicaNome, String playlistNome) {
		Playlist result = null;
		Collection<Playlist> playlists = cliente.getPlaylists();
		boolean novaPlaylist = true;
		for (Playlist playlist : playlists) {
			if (playlist.getNome().equals(playlistNome)) {
				playlist.getTracklist().add(musicaNome);
				result = playlist;
				novaPlaylist = false;
			}
		}
		if (novaPlaylist) {
			Playlist playlist = new Playlist(playlistNome);
			playlist.getTracklist().add(musicaNome);
			playlists.add(playlist);
			result = playlist;
		}
		return result; 
	}

	public boolean deletarMusicaPlaylist(Cliente cliente, String musicaNome, String playlistNome) {
		Collection<Playlist> playlists = cliente.getPlaylists();
		for (Playlist playlist : playlists) {
			if (playlist.getNome().equals(playlistNome)) {
				Collection<String> tracklist = playlist.getTracklist();
				for (String musica : tracklist) {
					if (musica.equals(musicaNome)) {
						return tracklist.remove(musicaNome);
					}
				}
			}
		}
		return false;
	}

	public boolean deletarPlaylist(Cliente cliente, String playlistNome) {
		Collection<Playlist> playlists = cliente.getPlaylists();
		for (Playlist playlist : playlists) {
			if (playlist.getNome().equals(playlistNome)) {
				return playlists.remove(playlist);
			}
		}
		return false;
	}

	public void favoritarArtista(Cliente cliente, String nome) {
		Collection<Artista> artistas = cliente.getArtistas();
		for (Artista artista : artistas) {
			if (artista.getNome().equals(nome)) {
				boolean favoritado = artista.isFavoritado();
				artista.setFavoritado(!favoritado);
			}
		}
		
	}

}
