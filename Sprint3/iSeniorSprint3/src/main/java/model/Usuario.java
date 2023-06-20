package model;

public class Usuario {
	private int idUsuario;
	private String email, nomeUsuario, senha, primeiroNome, sobrenome;
	
	public Usuario(){
		idUsuario = -1;
		email = "";
		nomeUsuario = "";
		senha = "";
		primeiroNome = "";
		sobrenome = "";
	}
	
	public Usuario(int idUsuario, String email, String nomeUsuario, String senha, String primeiroNome, String sobrenome){
		setIdUsuario(idUsuario);
		setEmail(email);
		setNomeUsuario(nomeUsuario);
		setSenha(senha);
		setPrimeiroNome(primeiroNome);
		setSobrenome(sobrenome);
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getPrimeiroNome() {
		return primeiroNome;
	}
	
	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}
	
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Usuario: " + idUsuario + "   Nome Usuário: " + nomeUsuario + "   Primeiro Nome: " + primeiroNome + "   Sobrenome: "
				+ sobrenome  + "   Email: " + email + "   Senha: " + senha;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getIdUsuario() == ((Usuario) obj).getIdUsuario());
	}	
}