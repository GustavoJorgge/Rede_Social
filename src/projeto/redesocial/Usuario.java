package projeto.redesocial;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
	private Date dta_criacao;
	
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

	public Date getDta_criacao() {
		return dta_criacao;
	}

	public void setDta_criacao(Date dta_criacao) {
		this.dta_criacao = dta_criacao;
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
	         PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CADASTRAR)) {
	        
	        preparedStatement.setString(1, usuario.getNome());
	        preparedStatement.setString(2, usuario.getEmail());
	        preparedStatement.setString(3, usuario.getPassword());
	        preparedStatement.setString(4, usuario.getEndereco());
	        
	        preparedStatement.executeUpdate();
	        preparedStatement.close();
	        connection.close();
	    } catch (SQLIntegrityConstraintViolationException e) {
	    	//Tratando exceção caso o e-mail seja repetido
	        JOptionPane.showMessageDialog(null, "O email informado já está em uso. Por favor, escolha outro email.");
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário: " + e.getMessage());
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
        
        JOptionPane.showMessageDialog(null,"Nome atualizado para: " + atualizacao ,"Atualizacao de Dados", JOptionPane.INFORMATION_MESSAGE);
        preparedStatement.executeUpdate();
		preparedStatement.close();
        connection.close();
	}
	
	public void editarEmail(int id_Usuario,String atualizacao) throws SQLException {
		String QUERY_EDITAR_EMAIL = "UPDATE usuarios SET user_email = '" + atualizacao + "'WHERE id_user = " + id_Usuario;
		Connection connection = DriverManager.getConnection(url, user, password);
		
		PreparedStatement preparedStatement = connection.prepareStatement(QUERY_EDITAR_EMAIL);
        preparedStatement.executeUpdate();
        
        JOptionPane.showMessageDialog(null,"E-mail atualizado para: " + atualizacao ,"Atualizacao de Dados", JOptionPane.INFORMATION_MESSAGE);
        preparedStatement.executeUpdate();
		preparedStatement.close();
        connection.close();
	}
	
	public void editarSenha(int id_Usuario,String atualizacao) throws SQLException {
		String QUERY_EDITAR_SENHA = "UPDATE usuarios SET senha = '" + atualizacao + "'WHERE id_user = " + id_Usuario;
		Connection connection = DriverManager.getConnection(url, user, password);
		
		PreparedStatement preparedStatement = connection.prepareStatement(QUERY_EDITAR_SENHA);
        preparedStatement.executeUpdate();
        
        JOptionPane.showMessageDialog(null,"Senha atualizada!","Atualizacao de Dados", JOptionPane.INFORMATION_MESSAGE);
        preparedStatement.executeUpdate();
		preparedStatement.close();
        connection.close();
	}
	
	/*
	 * Metodo para obter dados do usuario
	 */
	public Usuario obterPerfilUsuario(int idUsuario) {
	    Usuario aux = null; // Iniciando a variável aux

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
	            // Criando uma instancia de Usuario e atribuindo valores a ela
	            aux = new Usuario(nome, email, senha, endereco);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return aux; // retorna o objeto com os dados do usuario
	}
}
