package dao;


import model.Usuario;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
	
	
	public boolean insert(Usuario usuario)  {
		boolean status = false;
		try {
			
			String senha = usuario.getSenha();
			MessageDigest m=MessageDigest.getInstance("MD5");
			m.update(senha.getBytes(),0,senha.length());
			String senhaMD5 = new BigInteger(1,m.digest()).toString(64);
			usuario.setSenha(senhaMD5);
			
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
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return status;
	}

	
	public Usuario get(int idUsuario) {
		Usuario usuario = null;
		
		try {
			//Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM usuario WHERE idusuario = ?";
			PreparedStatement st = conexao.prepareStatement(query);
			st.setInt(1,idUsuario);
			//String sql = "SELECT * FROM usuario WHERE idusuario = " + idUsuario;
			ResultSet rs = st.executeQuery(query);	
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
	
	public Usuario login(String login,String senha) throws Exception {
		Usuario usuario = new Usuario();
		
		try {
			
			MessageDigest m=MessageDigest.getInstance("MD5");
			m.update(senha.getBytes(),0,senha.length());
			String senhaMD5 = new BigInteger(1,m.digest()).toString(64);
			System.out.println("MD5: "+ senhaMD5 + " len: " + senhaMD5.length());
			
			String query = "SELECT * FROM usuario WHERE login= ? AND senha= ?";
			PreparedStatement st = conexao.prepareStatement(query);
			st.setString(1,login);
			st.setString(2, senhaMD5);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				usuario = new Usuario(rs.getInt("idusuario"),rs.getString("email"),
						rs.getString("nomeusuario"), rs.getString("senha"), rs.getString("primeironome"), rs.getString("sobrenome"));		//Inserir parametros
			}
			
			st.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		if(usuario.getNomeUsuario().equals("nomeusuario")) {
			throw new Exception("Email ou senha incorretos");
		}
		return usuario;
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
