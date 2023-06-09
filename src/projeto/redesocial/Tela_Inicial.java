package projeto.redesocial;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tela_Inicial {

	private JFrame frame;
	private JTextField txt_Email;
	private JTextField txt_Password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_Inicial window = new Tela_Inicial();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tela_Inicial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 508, 524);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_Login = new JLabel("LOGIN");
		lbl_Login.setFont(new Font("Comic Sans MS", Font.BOLD, 23));
		lbl_Login.setBounds(194, 20, 104, 54);
		frame.getContentPane().add(lbl_Login);
		
		JLabel lbl_User = new JLabel("User:");
		lbl_User.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lbl_User.setBounds(85, 102, 50, 28);
		frame.getContentPane().add(lbl_User);
		
		txt_Email = new JTextField();
		txt_Email.setBounds(146, 100, 200, 39);
		frame.getContentPane().add(txt_Email);
		txt_Email.setColumns(10);
		
		JLabel lbl_Password = new JLabel("Password:");
		lbl_Password.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lbl_Password.setBounds(47, 162, 88, 28);
		frame.getContentPane().add(lbl_Password);
		
		txt_Password = new JTextField();
		txt_Password.setBounds(146, 160, 200, 39);
		frame.getContentPane().add(txt_Password);
		txt_Password.setColumns(10);
		
		JButton btn_Logar = new JButton("ENTRAR");
		btn_Logar.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 realizarLogin();
			 }
			 });
		btn_Logar.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		btn_Logar.setBounds(180, 234, 128, 39);
		frame.getContentPane().add(btn_Logar);
		
		JLabel lbl_Cadastro = new JLabel("Não possui cadastro?");
		lbl_Cadastro.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 15));
		lbl_Cadastro.setBounds(165, 304, 172, 28);
		frame.getContentPane().add(lbl_Cadastro);
		
		JButton btn_Cadastro = new JButton("CADASTRE-SE");
		btn_Cadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela_Cadastro telaCadastro = new Tela_Cadastro();
				telaCadastro.setVisible(true);
			}
		});
		btn_Cadastro.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		btn_Cadastro.setBounds(175, 350, 146, 28);
		frame.getContentPane().add(btn_Cadastro);
	}
	
	/*
	 * Metodo para realizar login
	 * Esta recebendo os dados dos campos e armazenando para realizar a validação
	 */
	public void realizarLogin() {
	    String email = txt_Email.getText();
	    String senha = txt_Password.getText();
	    Perfil perfil = new Perfil();	    
	    Login login = new Login();
	    Tela_Principal telaPrincipal = new Tela_Principal();
	    //Chamando metodo para validar o login
	    boolean loginValido = login.ValidarLogin(email, senha);
	    
	    if (loginValido) {	        
	        // Obter o perfil do usuário logado
	        perfil.setEmail(email); // Armazenando o email no objeto perfil para obter o usuario
	        perfil = perfil.obterPerfilUsuario(perfil); //chamando o metodo para retornar os dados do usuario
	        
	        if (perfil != null) {
	            telaPrincipal.exibirTelaPrincipal(perfil,telaPrincipal);
	        } else {
	            JOptionPane.showMessageDialog(null, "Não foi possível obter o perfil do usuário.", "Erro!", JOptionPane.ERROR_MESSAGE);
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Você não possui cadastro!", "Erro!", JOptionPane.ERROR_MESSAGE);
	    }
	}
}
