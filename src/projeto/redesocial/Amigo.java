package projeto.redesocial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Amigo {
		
	int id_Amigo;
	int id_Usuario;
	String nome;
	String endereco;
	
	private final String url = "jdbc:postgresql://localhost/BDredeSocial";
	private final String user = "postgres";
	private final String password = "Gustavo@";
	Connection conn = null;
	
	StringBuilder resultado_consulta;
	StringBuilder Lista_amigos;
	
	public Amigo() {
		resultado_consulta = new StringBuilder(); // Inicializar o StringBuilder
		Lista_amigos = new StringBuilder();
		
	}
	
	public StringBuilder getResultadoConsulta() {
		return resultado_consulta;
	}
	
	public StringBuilder getListaAmigos() {
		return Lista_amigos;
	}
	
		
	public Connection connect() {
			
			try {
				conn = DriverManager.getConnection(url,user,password);
				
				if(conn!=null) {
					System.out.println("Conexão com PostGreSQL estabelecida com sucesso!");
				}else {
					System.out.println("Falha na conexão com o PostGreSQL");
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
	
	
	/*
	 * metodo para adicionar o amigo que o usuario digitar
	 * Condiçoes:
	 * 	O usuario não consegue voce mesmo
	 * 	O usuario não consegue adicionar um usuario que não esta cadastrado
	 * 	O usuario adiciona apenas ID's que estejam disponiveis
	 */
	void adicionar_Amigo(int id_usuario, int id_amigo) throws SQLException {
		Connection connection = DriverManager.getConnection(url, user, password);
		int aux = 0;
		
		String QUERY_VALIDAR = "select * from usuarios where id_user = " + id_amigo; //Query para buscar o id do amigo digitado
		PreparedStatement preparedStatement = connection.prepareStatement(QUERY_VALIDAR);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		 while(resultSet.next()) {//loop para verificar todos usuarios
        	 aux = resultSet.getInt("id_user");//armazenando o id do resultado da consulta
		 }
		 
		if(aux == id_usuario ) { //condição para nao conseguir conectar consigo mesmo
			JOptionPane.showMessageDialog(null,"Não é possivel conectar consigo mesmo, entre com outro usuario!","Erro!",JOptionPane.ERROR_MESSAGE);
		}else if(aux!=0) { // Só ira adicionar se for um usuario valido no banco.
			String QUERY_ADICIONAR = "insert into lista_amigos (id_usuario, id_amigo) values (" + id_usuario +"," + id_amigo +")";
			PreparedStatement preparedStatementAdicionar = connection.prepareStatement(QUERY_ADICIONAR);
	        preparedStatementAdicionar.executeUpdate();
			JOptionPane.showMessageDialog(null,"adicionado com sucesso");
		}else { //Se o id for nulo (ou seja, não possui no banco) não adicionara nenhum
			JOptionPane.showMessageDialog(null,"Não foi encontrado nenhum usuario!","Usuario invalido!",JOptionPane.ERROR_MESSAGE);
		}
		
		resultSet.close();
        preparedStatement.close();
        connection.close();
	}
	
	//Metodo para buscar todos usuarios que começam com os caracteres digitados	 
	public void consultar_Usuario(String busca) throws SQLException {
		Connection connection = DriverManager.getConnection(url, user, password);
		
		String QUERY_BUSCAR = "select * from usuarios where user_name LIKE'" + busca + "%'";		
		
		PreparedStatement preparedStatement = connection.prepareStatement(QUERY_BUSCAR);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        //Loop para receber e armazenar em uma variavel todos os usuarios
        while(resultSet.next()) {
       	 int id = resultSet.getInt("id_User");
       	 id_Usuario = id;
       	 String nome = resultSet.getString("user_name");
       	 String email = resultSet.getString("user_email");
       	 String endereco = resultSet.getString("endereco");
       	 
       	 //Atribuindo os valores para realizar a impressao de todos usuarios na tela
	    	 resultado_consulta.append("Id usuario: ").append(id).append("\n");
	         resultado_consulta.append("Nome: ").append(nome).append("\n");
	         resultado_consulta.append("E-mail: ").append(email).append("\n");
	         resultado_consulta.append("Local:").append(endereco).append("\n");
	         resultado_consulta.append("------------------------------------\n");
        }
        
        resultSet.close();
        preparedStatement.close();
        connection.close();
	}
	
	/*
	 * Metodo para listar todos os amigos que estao conectados na tabela 
	 * Lista amigos com o usuario que esta logado.
	 * o metodo recebe como parametro o ID do usuario que esta logado e 
	 * executa uma query que busca no banco todos os usuarios conectados.
	 */
	void listar_Amigos(int id_Usuario) throws SQLException {
		Connection connection = DriverManager.getConnection(url, user, password);
		String QUERY_LISTAAMIGOS = "SELECT u.user_name AS nome_amigo, u.user_email AS email_amigo FROM lista_amigos la JOIN usuarios u ON u.id_user = la.id_amigo WHERE la.id_usuario = " + id_Usuario + ";";
		PreparedStatement preparedStatement = connection.prepareStatement(QUERY_LISTAAMIGOS);
		ResultSet resultSet = preparedStatement.executeQuery();
		JOptionPane.showMessageDialog(null,id_Usuario);
		while(resultSet.next()) {
			String nome = resultSet.getString("nome_amigo");
			String email = resultSet.getString("email_amigo");
			
			Lista_amigos.append("Nome: ").append(nome).append("\n");
			Lista_amigos.append("E-mail: ").append(email).append("\n");
			Lista_amigos.append("----------------------------------------\n");
		}
		
		resultSet.close();
        preparedStatement.close();
        connection.close();
		
	}
	
}
