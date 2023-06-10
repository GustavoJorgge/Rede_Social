package projeto.redesocial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Perfil {

	private final String url = "jdbc:postgresql://localhost/BDredeSocial";
	private final String user = "postgres";
	private final String password = "Gustavo@";
	Connection conn = null;
	
	private int id;
	private String nome;
	private String email;
	private String senha;
	private String endereco;
	
	public Perfil() {
		
	}
	
	public Perfil(int id, String nome, String email, String senha, String endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	/*
	 * Metodo para obter os dados do usuario
	 */
	public Perfil obterPerfilUsuario(Perfil perfil) {
	    try (Connection connection = DriverManager.getConnection(url, user, password);
	         PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM usuarios WHERE user_email = ?")) {

	        preparedStatement.setString(1, perfil.getEmail());
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        if (resultSet.next()) {
	            int id = resultSet.getInt("Id_user");
	            String nome = resultSet.getString("user_name");
	            String email = resultSet.getString("user_email");
	            String senha = resultSet.getString("senha");
	            String endereco = resultSet.getString("endereco");
	            //Atribuindo os valores no objeto perfil
	            perfil.setId(id);
	            perfil.setNome(nome);
	            perfil.setEmail(email);
	            perfil.setSenha(senha);
	            perfil.setEndereco(endereco);
	            JOptionPane.showMessageDialog(null, "nome: " + perfil.getNome() + " email: " + perfil.getEmail());
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return perfil;//retorna o objeto com os dados do usuario
	}
	
}
