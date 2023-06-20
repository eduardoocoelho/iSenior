package model;

public class Faq {
	private int id;
	private String pergunta, resposta;
	
	public Faq() {
		id = -1;
		pergunta = resposta = "";
	}

	public Faq(int id, String pergunta, String resposta) {
		setId(id);
		setPergunta(pergunta);
		setResposta(resposta);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	
	@Override
	public String toString() {
		return "Id: " + id + "Pergunta: " + pergunta + "   Resposta: " + resposta; 
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Faq) obj).getId());
	}	
}