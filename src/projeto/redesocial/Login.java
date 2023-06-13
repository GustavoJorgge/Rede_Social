package projeto.redesocial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Login {
	
	private final String url = "jdbc:postgresql://localhost/BDredeSocial";
	private final String user = "postgres";
	private final String password = "Gustavo@";
	Connection conn = null;
	
	private String email;
	private String senha;
	
	public Login() {
		
	}
	
	public Login(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}
	
	private final String QUERY_LOGAR = "SELECT COUNT(*) FROM usuarios WHERE user_email = ? AND senha = ?";
			
	public boolean ValidarLogin(String email, String senha) {
	    boolean loginValido = false;

	    try (Connection connection = DriverManager.getConnection(url, user, password);
	         PreparedStatement preparedStatement = connection.prepareStatement(QUERY_LOGAR)) {

	        preparedStatement.setString(1, email);
	        preparedStatement.setString(2, senha);
	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            int count = resultSet.getInt("count");
	            if (count > 0) {
	                loginValido = true;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return loginValido;
	}
}
