package dao;

import model.Faq;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class FaqDAO extends DAO {	
	public FaqDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Faq faq) {
		boolean status = false;
		try {
			String sql = "INSERT INTO faq (pergunta, resposta) "
		               + "VALUES ('" + faq.getPergunta() + "', '"
		               + faq.getResposta() + "');";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Faq get(int id) {
		Faq faq = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM faq WHERE idfaq = " + id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 faq = new Faq(rs.getInt("idfaq"), rs.getString("pergunta"), rs.getString("resposta"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return faq;
	}
	
	
	public List<Faq> get() {
		return get("");
	}

	
	public List<Faq> getOrderById() {
		return get("idfaq");		
	}
	
	
	public List<Faq> getOrderByPergunta() {
		return get("pergunta");		
	}
	
	
	public List<Faq> getOrderByResposta() {
		return get("resposta");		
	}
	
	
	private List<Faq> get(String orderBy) {
		List<Faq> faqs = new ArrayList<Faq>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM faq" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Faq f = new Faq(rs.getInt("idfaq"), rs.getString("pergunta"), rs.getString("resposta"));
	            faqs.add(f);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return faqs;
	}
	
	
	public boolean update(Faq faq) {
		boolean status = false;
		try {  
			String sql = "UPDATE faq SET pergunta = '" + faq.getPergunta() + "', "
					   + "resposta = '" + faq.getResposta() + "' WHERE idfaq = " + faq.getId();
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM faq WHERE idfaq = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}