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
	
	 public Amigo() {
	        resultado_consulta = new StringBuilder(); // Inicializar o StringBuilder
	   }
	
	public StringBuilder getResultadoConsulta() {
		return resultado_consulta;
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
	
	void adicionar_Amigo() {
		
	}
	
	public void consultar_Usuario(String busca) throws SQLException {
		Connection connection = DriverManager.getConnection(url, user, password);
		
		String QUERY_BUSCAR = "select * from usuarios where user_name LIKE'" + busca + "%'";
		
		
		PreparedStatement preparedStatement = connection.prepareStatement(QUERY_BUSCAR);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while(resultSet.next()) {
       	 int id = resultSet.getInt("id_User");
       	 String nome = resultSet.getString("user_name");
       	 String email = resultSet.getString("user_email");
       	 String endereco = resultSet.getString("endereco");
       	 
       	 //Atribuindo os valores para realizar a impressao.
	    	 resultado_consulta.append("Id usuario: ").append(id).append("\n");
	         resultado_consulta.append("Nome: ").append(nome).append("\n");
	         resultado_consulta.append("Email: ").append(email).append("\n");
	         resultado_consulta.append("Local:").append(endereco).append("\n");
	         resultado_consulta.append("------------------------------------\n");
        }
        
        resultSet.close();
        preparedStatement.close();
        connection.close();
	}
	
	void excluir_Amigo() {
		
	}
	
}
