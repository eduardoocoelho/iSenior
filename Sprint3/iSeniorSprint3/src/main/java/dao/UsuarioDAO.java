package dao;

import model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAO extends DAO {	
	public UsuarioDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Usuario usuario) {
		boolean status = false;
		try {
			String sql = "INSERT INTO usuario (email, senha, sobrenome, nomeusuario, primeironome) "
		               + "VALUES ('" + usuario.getEmail() + "', '"
		               + usuario.getSenha() + "', '" + usuario.getSobrenome() + "', '" + usuario.getNomeUsuario() + 
		               "', '" + usuario.getPrimeiroNome() + "');";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Usuario get(int idUsuario) {
		Usuario usuario = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario WHERE idusuario = " + idUsuario;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 usuario = new Usuario(rs.getInt("idUsuario"), rs.getString("email"), rs.getString("nomeUsuario"), 
	                				   rs.getString("senha"), 
	        			               rs.getString("primeiroNome"),
	        			               rs.getString("sobrenome"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuario;
	}
	
	
	public List<Usuario> get() {
		return get("");
	}

	
	public List<Usuario> getOrderByIdUsuario() {
		return get("idUsuario");		
	}
	
	
	public List<Usuario> getOrderByNomeUsuario() {
		return get("nomeUsuario");		
	}
	
	
	public List<Usuario> getOrderByPrimeiroNome() {
		return get("primeiroNome");		
	}
	
	
	private List<Usuario> get(String orderBy) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Usuario u = new Usuario(rs.getInt("idUsuario"), rs.getString("email"), rs.getString("nomeUsuario"), 
	        			                rs.getString("senha"),
	        			                rs.getString("primeiroNome"),
	        			                rs.getString("sobrenome"));
	            usuarios.add(u);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuarios;
	}
	
	
	public boolean update(Usuario usuario) {
		boolean status = false;
		try {  
			String sql = "UPDATE usuario SET email = '" + usuario.getEmail() + "', "
					   + "nomeusuario = '" + usuario.getNomeUsuario() + "', " 
					   + "senha = '" + usuario.getSenha() + "',"
					   + "primeironome = '" + usuario.getPrimeiroNome() + "', " 
					   + "sobrenome = '" + usuario.getSobrenome() + "' WHERE idusuario = " + usuario.getIdUsuario();
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int idUsuario) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM usuario WHERE idusuario = " + idUsuario);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}
