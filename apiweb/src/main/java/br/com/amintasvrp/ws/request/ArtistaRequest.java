package br.com.amintasvrp.ws.request;

public class ArtistaRequest {
	
	private String nota;
	private boolean favoritado;
	private String url;
	private String nome;
	
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

}
