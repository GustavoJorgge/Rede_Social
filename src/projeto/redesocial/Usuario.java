package projeto.redesocial;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JOptionPane;

public class Usuario {
	

	private final String url = "jdbc:postgresql://localhost/BDredeSocial";
	private final String user = "postgres";
	private final String password = "Gustavo@";
	Connection conn = null;
	
	private static int id_Usuario;
	private String nome;
	private String email;
	private String senha;
	private String endereco;
	private Date dta_Criacao;
	
	public Usuario(String nome, String email, String senha, String endereco) {
		super();
		this.id_Usuario += 1;
		this.nome = nome;
		this.email = email;
		 this.senha = senha;
		this.endereco = endereco;
		this.dta_Criacao = new Date();
	}

	public int getId_Usuario() {
		return id_Usuario;
	}

	public void setId_Usuario(int id_Usuario) {
		this.id_Usuario = id_Usuario;
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

	public String getPassword() {
		return senha;
	}

	public void setPassword(String senha) {
		this.senha = senha;
	}
	
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	void DeletarUsuario() {	
	}
	
	
	/*
	 * Estabelecendo conexão com o banco
	 */
	public Connection connect() throws IOException {
		try {
			conn = DriverManager.getConnection(url,user,password);
			
			if(conn!=null) {
				System.out.println("Conexao com banco estabelecida com sucesso!");				
			}else {
				System.out.println("Falha na conexão com o banco!");
			}
			Statement statement = conn.createStatement(); // Criando instancia do objeto que representa um canal de comunicação com banco de dados
			ResultSet resultSet = statement.executeQuery("SELECT VERSION()");//Esta consulta obtem a versão do PostGreSQL
			
			if(resultSet.next()) {
				System.out.printf(resultSet.getString(1));
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return conn;
	}
	
	private final String QUERY_CADASTRAR = "insert into usuarios (user_name, user_email, senha, endereco) values (?,?,?,?)";
	
	/*
	 * Metodo que recebe por parametro o objeto usuario e cadastra no banco de dados
	 */
	public void cadastraUsuario(Usuario usuario) {
		
		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CADASTRAR)){//final do TRY
					
					preparedStatement.setString(1,getNome());
					preparedStatement.setString(2,getEmail());
					preparedStatement.setString(3,getPassword());
					preparedStatement.setString(4,getEndereco());
					
					preparedStatement.executeUpdate();
					preparedStatement.close();
			        connection.close();
			}catch(SQLException e) {
				JOptionPane.showConfirmDialog(null, e);
			}	
		
	}
	
}
