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

public class Usuario extends Perfil {
	

	private final String url = "jdbc:postgresql://localhost/BDredeSocial";
	private final String user = "postgres";
	private final String password = "Gustavo@";
	Connection conn = null;
	
	private int id_Usuario;
	private String nome;
	private String email;
	private String senha;
	private String endereco;
	
	public Usuario() {}
	
	public Usuario(String nome, String email, String senha, String endereco) {
		super();		
		this.nome = nome;
		this.email = email;
		 this.senha = senha;
		this.endereco = endereco;
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
	/*
	 * Abaixo nos teremos os metodos que possibilitarão o usuario de editar um de seus dados cadastrais
	 * Como:
	 * 		Nome
	 * 		E-mail
	 * 		Senha
	 */
	public void editarNome(int id_Usuario,String atualizacao) throws SQLException {
		String QUERY_EDITAR_NOME = "UPDATE usuarios SET user_name = '" + atualizacao + "'WHERE id_user = " + id_Usuario;
		Connection connection = DriverManager.getConnection(url, user, password);
		
		PreparedStatement preparedStatement = connection.prepareStatement(QUERY_EDITAR_NOME);
        preparedStatement.executeUpdate();
        
        preparedStatement.executeUpdate();
		preparedStatement.close();
        connection.close();
	}
	
	public void editarEmail(int id_Usuario,String atualizacao) throws SQLException {
		String QUERY_EDITAR_EMAIL = "UPDATE usuarios SET user_email = '" + atualizacao + "'WHERE id_user = " + id_Usuario;
		Connection connection = DriverManager.getConnection(url, user, password);
		
		PreparedStatement preparedStatement = connection.prepareStatement(QUERY_EDITAR_EMAIL);
        preparedStatement.executeUpdate();
        
        preparedStatement.executeUpdate();
		preparedStatement.close();
        connection.close();
	}
	
	public void editarSenha(int id_Usuario,String atualizacao) throws SQLException {
		String QUERY_EDITAR_SENHA = "UPDATE usuarios SET senha = '" + atualizacao + "'WHERE id_user = " + id_Usuario;
		Connection connection = DriverManager.getConnection(url, user, password);
		
		PreparedStatement preparedStatement = connection.prepareStatement(QUERY_EDITAR_SENHA);
        preparedStatement.executeUpdate();
        
        preparedStatement.executeUpdate();
		preparedStatement.close();
        connection.close();
	}
	
	public Usuario obterPerfilUsuario(int idUsuario) {
	    Usuario aux = null; // Inicialize a variável aux

	    try (Connection connection = DriverManager.getConnection(url, user, password);
	         PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM usuarios WHERE Id_user = ?")) {
	        
	        preparedStatement.setInt(1, idUsuario);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        if (resultSet.next()) {
	            int id = resultSet.getInt("Id_user");
	            String nome = resultSet.getString("user_name");
	            String email = resultSet.getString("user_email");
	            String senha = resultSet.getString("senha");
	            String endereco = resultSet.getString("endereco");
	            // Crie uma instância de Usuario e atribua valores a ela
	            aux = new Usuario(nome, email, senha, endereco);
	            JOptionPane.showMessageDialog(null,aux.getNome());
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return aux; // retorna o objeto com os dados do usuario
	}
}
