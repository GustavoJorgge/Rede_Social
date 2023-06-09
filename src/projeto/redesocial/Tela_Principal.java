package projeto.redesocial;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tela_Principal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_Principal window = new Tela_Principal();
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
	public Tela_Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 503, 672);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_User = new JLabel("[nome do usuario]");
		lbl_User.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_User.setFont(new Font("Comic Sans MS", Font.BOLD, 19));
		lbl_User.setBounds(128, 109, 233, 24);
		frame.getContentPane().add(lbl_User);
		
		JLabel lbl_Id = new JLabel("ID:");
		lbl_Id.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lbl_Id.setBounds(380, 26, 45, 13);
		frame.getContentPane().add(lbl_Id);
		
		JLabel lbl_Id_User = new JLabel("ID:");
		lbl_Id_User.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lbl_Id_User.setBounds(413, 26, 45, 13);
		frame.getContentPane().add(lbl_Id_User);
		
		JLabel lbl_RedeSocial = new JLabel("Rede Social");
		lbl_RedeSocial.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 27));
		lbl_RedeSocial.setBounds(160, 49, 162, 43);
		frame.getContentPane().add(lbl_RedeSocial);
		
		JTextPane txt_Dados = new JTextPane();
		txt_Dados.setEditable(false);
		txt_Dados.setBounds(66, 199, 359, 194);
		frame.getContentPane().add(txt_Dados);
		
		JLabel lbl_Dados = new JLabel("Seus dados:");
		lbl_Dados.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Dados.setFont(new Font("Comic Sans MS", Font.BOLD, 19));
		lbl_Dados.setBounds(66, 165, 233, 24);
		frame.getContentPane().add(lbl_Dados);
		
		JButton btn_Amigos = new JButton("AMIGOS");
		btn_Amigos.setFont(new Font("Comic Sans MS", Font.BOLD, 19));
		btn_Amigos.setBounds(52, 432, 181, 50);
		frame.getContentPane().add(btn_Amigos);
		
		JButton btn_Adicionar = new JButton("<html>ADICIONAR <br>   AMIGOS</html>");
		btn_Adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Adicionar.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btn_Adicionar.setBounds(258, 432, 181, 50);
		frame.getContentPane().add(btn_Adicionar);
		
		JButton btn_Mensagem = new JButton("SAIR");
		btn_Mensagem.setFont(new Font("Comic Sans MS", Font.BOLD, 19));
		btn_Mensagem.setBounds(52, 506, 181, 50);
		frame.getContentPane().add(btn_Mensagem);
		
		JButton btn_Logoff = new JButton("SAIR");
		btn_Logoff.setFont(new Font("Comic Sans MS", Font.BOLD, 19));
		btn_Logoff.setBounds(258, 506, 181, 50);
		frame.getContentPane().add(btn_Logoff);
	}
	
	/*
	 * Metodo para iniciar a tela principal
	 */
	public void exibirTelaPrincipal(Perfil perfilUsuario, Tela_Principal telaPrincipal) {
		String perfil = perfilUsuario.getNome();
		JOptionPane.showMessageDialog(null, perfilUsuario.getNome());//Printei apenas para validar se nao estava nulo
		txt_Dados.setText("Usuário: " + perfilUsuario.getNome());
		telaPrincipal.frame.setVisible(true);
	}

	private void setVisible(boolean b) {
		frame.setVisible(true);
	}
}
