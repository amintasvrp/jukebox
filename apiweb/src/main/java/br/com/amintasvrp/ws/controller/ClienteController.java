package br.com.amintasvrp.ws.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.amintasvrp.ws.model.*;
import br.com.amintasvrp.ws.request.*;
import br.com.amintasvrp.ws.service.ClienteService;

@RestController
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	Cliente clienteLogado;
		
	@RequestMapping(method = RequestMethod.POST, value = "/cliente", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Cliente> cadastrarCliente(@RequestBody ClienteRequest clienteRequest) {
		Cliente cliente = new Cliente(clienteRequest.getEmail(), clienteRequest.getSenha(), clienteRequest.getNome());
		Cliente clienteCadastrado = clienteService.cadastrarCliente(cliente);
		return new ResponseEntity<Cliente>(clienteCadastrado, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Collection<Cliente>> obterClientes() {
		Collection<Cliente> clientes = clienteService.obterListaClientes();
		return new ResponseEntity<Collection<Cliente>>(clientes, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/cliente", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> logarCliente(@RequestBody ClienteRequest clienteRequest) {
		String login = clienteRequest.getEmail() + clienteRequest.getSenha();
		Cliente clienteLogin = clienteService.obterCliente(login);
		if (clienteLogin != null) {
			clienteLogado = clienteLogin;
			return new ResponseEntity<Cliente>(clienteLogado, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/artistas", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Collection<Artista>> obterArtistas() {
		Collection<Artista> artistas = clienteService.obterListaArtistas(clienteLogado);
		return new ResponseEntity<Collection<Artista>>(artistas, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/artista", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Artista> cadastrarArtista(@RequestBody ArtistaRequest artistaRequest) {
		if (clienteLogado != null) {
			Artista artista = new Artista(artistaRequest.getNota(), artistaRequest.isFavoritado(), artistaRequest.getUrl(), artistaRequest.getNome());
			Artista artistaCadastrado = clienteService.cadastrarArtista(clienteLogado, artista);
			return new ResponseEntity<Artista>(artistaCadastrado, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/artista", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Artista> favoritarArtista(@RequestBody ArtistaRequest artistaRequest) {
		clienteService.favoritarArtista(clienteLogado, artistaRequest.getNome());
		return new ResponseEntity<Artista>(HttpStatus.OK); 
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/musicas", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Collection<Musica>> obterMusicas() {
		Collection<Musica> musicas = clienteService.obterListaMusicas(clienteLogado);
		return new ResponseEntity<Collection<Musica>>(musicas, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/musica", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Musica> cadastrarMusica(@RequestBody MusicaRequest musicaRequest) {
		if (clienteLogado != null) {
			Musica musica = new Musica(musicaRequest.getNome(), musicaRequest.getArtista(), musicaRequest.getAlbum(), musicaRequest.getLancamento(), musicaRequest.getDuracao());
			Musica musicaCadastrada = clienteService.cadastrarMusica(clienteLogado, musica);
			return new ResponseEntity<Musica>(musicaCadastrada, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/playlists", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Collection<Playlist>> obterPlaylists() {
		Collection<Playlist> playlists = clienteService.obterListaPlaylists(clienteLogado);
		return new ResponseEntity<Collection<Playlist>>(playlists, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/musicaPlaylist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Playlist> cadastrarMusicaPlaylist(@RequestBody MusicaPlaylistRequest musicaPlaylistRequest) {
		if (clienteLogado != null) {
			Playlist playlist = clienteService.cadastrarMusicaPlaylist(clienteLogado, musicaPlaylistRequest.getMusica(), musicaPlaylistRequest.getPlaylist());
			return new ResponseEntity<Playlist>(playlist, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Playlist>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/musica", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> deletarMusicaPlaylist(@RequestBody MusicaPlaylistRequest musicaPlaylistRequest) {
		if (clienteLogado != null) {
			boolean deletado = clienteService.deletarMusicaPlaylist(clienteLogado, musicaPlaylistRequest.getMusica(), musicaPlaylistRequest.getPlaylist());
			return new ResponseEntity<Boolean>(deletado,HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/playlist", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> deletarPlaylist(@RequestBody PlaylistRequest playlistRequest) {
		if (clienteLogado != null) {
			boolean deletado = clienteService.deletarPlaylist(clienteLogado, playlistRequest.getNome());
			return new ResponseEntity<Boolean>(deletado, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
