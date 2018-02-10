package br.com.amintasvrp.ws.model;

public class Musica {
	
	private String nome;
	private String artista;
	private String album;
	private String lancamento;
	private String duracao;
	
	public Musica(String nome, String artista, String album, String lancamento, String duracao) {
		this.nome = nome;
		this.artista = artista;
		this.album = album;
		this.lancamento = lancamento;
		this.duracao = duracao;
	}

	public String getNome() {
		return nome;
	}

	public String getArtista() {
		return artista;
	}

	public String getAlbum() {
		return album;
	}

	public String getLancamento() {
		return lancamento;
	}

	public String getDuracao() {
		return duracao;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public void setLancamento(String lancamento) {
		this.lancamento = lancamento;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}
	
}
