package dao;

import model.Artigo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ArtigoDAO extends DAO {	
	public ArtigoDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Artigo artigo) {
		boolean status = false;
		try {
			String sql = "INSERT INTO artigo (categoria, palavrachave, tag, texto, titulo) "
		               + "VALUES ('" + artigo.getCategoria() + "', '"
		               + artigo.getPalavraChave() + "', '" + artigo.getTag() + "', '" + artigo.getTexto() + 
		               "', '" + artigo.getTitulo() + "');";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Artigo get(int idArtigo) {
		Artigo artigo = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM artigo WHERE idartigo = " + idArtigo;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 artigo = new Artigo(rs.getInt("idArtigo"), rs.getInt("categoria"), rs.getString("palavraChave"), 
	                				   rs.getString("tag"), 
	        			               rs.getString("titulo"),
	        			               rs.getString("texto"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return artigo;
	}
	
	
	public List<Artigo> get() {
		return get("");
	}

	
	public List<Artigo> getOrderByIdArtigo() {
		return get("idArtigo");		
	}
	
	
	public List<Artigo> getOrderByCategoria() {
		return get("categoria");		
	}
	
	
	public List<Artigo> getOrderByTitulo() {
		return get("titulo");		
	}
	
	
	private List<Artigo> get(String orderBy) {
		List<Artigo> artigos = new ArrayList<Artigo>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM artigo" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Artigo u = new Artigo(rs.getInt("idArtigo"), rs.getInt("categoria"), rs.getString("palavraChave"), 
	        			                rs.getString("tag"),
	        			                rs.getString("titulo"),
	        			                rs.getString("texto"));
	            artigos.add(u);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return artigos;
	}
	
	
	public boolean update(Artigo artigo) {
		boolean status = false;
		try {  
			String sql = "UPDATE artigo SET categoria = '" + artigo.getCategoria() + "', "
					   + "palavrachave = '" + artigo.getPalavraChave() + "', " 
					   + "tag = '" + artigo.getTag() + "',"
					   + "texto = '" + artigo.getTexto() + "', " 
					   + "titulo = '" + artigo.getTitulo() + "' WHERE idartigo = " + artigo.getIdArtigo();
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int idArtigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM artigo WHERE idartigo = " + idArtigo);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}