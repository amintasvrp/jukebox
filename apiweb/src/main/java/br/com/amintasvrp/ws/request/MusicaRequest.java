package br.com.amintasvrp.ws.request;

public class MusicaRequest {
	private String nome;
	private String artista;
	private String album;
	private String lancamento;
	private String duracao;
	
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
