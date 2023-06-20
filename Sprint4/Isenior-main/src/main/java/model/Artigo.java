package model;

public class Artigo {
	private int idArtigo, categoria;
	private String palavraChave, tag, titulo, texto;

	public Artigo() {
		idArtigo = 0;
		categoria = 0;
		palavraChave = "";
		tag = "";
		titulo ="";
		texto = "";
	}
	
	public Artigo(int idArtigo, int categoria, String palavraChave, String tag, String titulo, String texto) {
		setIdArtigo(idArtigo);
		setCategoria(categoria);
		setPalavraChave(palavraChave);
		setTag(tag);
		setTitulo(titulo);
		setTexto(texto);
	}
	
	public int getIdArtigo() {
		return idArtigo;
	}
	
	public void setIdArtigo(int idArtigo) {
		this.idArtigo = idArtigo;
	}
	
	public int getCategoria() {
		return categoria;
	}
	
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	
	public String getPalavraChave() {
		return palavraChave;
	}

	public void setPalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	/**
	 * MÃ©todo sobreposto da classe Object. Ã‰ executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Artigo: " + idArtigo + "   Titulo Artigo: " + titulo + "   Categoria: " + categoria + "   Palavras Chaves: "
				+ palavraChave  + "   Tag: " + tag + "   Texto: " + texto;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getIdArtigo() == ((Artigo) obj).getIdArtigo());
	}
}