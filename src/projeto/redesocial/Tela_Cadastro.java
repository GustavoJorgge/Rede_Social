package projeto.redesocial;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tela_Cadastro {

	private JFrame frame;
	private JTextField txt_Rua;
	private JTextField txt_Numero;
	private JTextField txt_Bairro;
	private JTextField txt_Cidade;
	private JTextField txt_UF;
	private JTextField txt_Nome;
	private JTextField txt_Email;
	private JTextField txt_Password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_Cadastro window = new Tela_Cadastro();
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
	public Tela_Cadastro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 524, 638);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_Cadastro = new JLabel("CADASTRO");
		lbl_Cadastro.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		lbl_Cadastro.setBounds(179, 22, 163, 48);
		frame.getContentPane().add(lbl_Cadastro);
		
		JLabel lbl_UserName = new JLabel("Nome:");
		lbl_UserName.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lbl_UserName.setBounds(33, 104, 67, 26);
		frame.getContentPane().add(lbl_UserName);
		
		JLabel lbl_Email = new JLabel("E-mail:");
		lbl_Email.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lbl_Email.setBounds(33, 387, 67, 26);
		frame.getContentPane().add(lbl_Email);
		
		JLabel lbl_Endereco = new JLabel("Endereco");
		lbl_Endereco.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lbl_Endereco.setBounds(33, 153, 109, 26);
		frame.getContentPane().add(lbl_Endereco);
		
		JLabel lbl_Password = new JLabel("Password:");
		lbl_Password.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lbl_Password.setBounds(33, 442, 92, 26);
		frame.getContentPane().add(lbl_Password);
		
		JLabel lbl_Endereco_Rua = new JLabel("Rua:");
		lbl_Endereco_Rua.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lbl_Endereco_Rua.setBounds(33, 200, 109, 26);
		frame.getContentPane().add(lbl_Endereco_Rua);
		
		txt_Rua = new JTextField();
		txt_Rua.setBounds(79, 190, 405, 36);
		frame.getContentPane().add(txt_Rua);
		txt_Rua.setColumns(10);
		
		JLabel lbl_Endereco_Num = new JLabel("N°:");
		lbl_Endereco_Num.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lbl_Endereco_Num.setBounds(33, 272, 40, 26);
		frame.getContentPane().add(lbl_Endereco_Num);
		
		txt_Numero = new JTextField();
		txt_Numero.setBounds(73, 263, 96, 35);
		frame.getContentPane().add(txt_Numero);
		txt_Numero.setColumns(10);
		
		JLabel lbl_Endereco_Bairro = new JLabel("Bairro:");
		lbl_Endereco_Bairro.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lbl_Endereco_Bairro.setBounds(179, 273, 67, 26);
		frame.getContentPane().add(lbl_Endereco_Bairro);
		
		txt_Bairro = new JTextField();
		txt_Bairro.setColumns(10);
		txt_Bairro.setBounds(244, 263, 240, 36);
		frame.getContentPane().add(txt_Bairro);
		
		JLabel lbl_Endereco_Cidade = new JLabel("Cidade:");
		lbl_Endereco_Cidade.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lbl_Endereco_Cidade.setBounds(33, 326, 109, 26);
		frame.getContentPane().add(lbl_Endereco_Cidade);
		
		txt_Cidade = new JTextField();
		txt_Cidade.setColumns(10);
		txt_Cidade.setBounds(102, 316, 240, 36);
		frame.getContentPane().add(txt_Cidade);
		
		JLabel lbl_Endereco_UF = new JLabel("UF:");
		lbl_Endereco_UF.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lbl_Endereco_UF.setBounds(352, 326, 40, 26);
		frame.getContentPane().add(lbl_Endereco_UF);
		
		txt_UF = new JTextField();
		txt_UF.setColumns(10);
		txt_UF.setBounds(388, 317, 96, 35);
		frame.getContentPane().add(txt_UF);
		
		txt_Nome = new JTextField();
		txt_Nome.setColumns(10);
		txt_Nome.setBounds(102, 94, 382, 36);
		frame.getContentPane().add(txt_Nome);
		
		txt_Email = new JTextField();
		txt_Email.setColumns(10);
		txt_Email.setBounds(102, 377, 382, 36);
		frame.getContentPane().add(txt_Email);
		
		txt_Password = new JTextField();
		txt_Password.setColumns(10);
		txt_Password.setBounds(129, 432, 355, 36);
		frame.getContentPane().add(txt_Password);
		
		JButton btn_Cadastrar = new JButton("CADASTRAR");
		btn_Cadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarUsuario();
				txt_Nome.setText("");
				txt_Email.setText("");
				txt_Rua.setText("");
				txt_Numero.setText("");
				txt_Bairro.setText("");
				txt_UF.setText("");
				txt_Cidade.setText("");
				txt_Password.setText("");
			}
		});
		btn_Cadastrar.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		btn_Cadastrar.setBounds(180, 511, 139, 36);
		frame.getContentPane().add(btn_Cadastrar);
			
	}
	
	public void CadastrarUsuario() {
		String nome = txt_Nome.getText();
		String email = txt_Email.getText();
		String password = txt_Password.getText();
		String endereco = txt_Rua.getText() + "," + txt_Numero.getText() + ", " + txt_Bairro.getText() + ", " + txt_Cidade.getText() + " - " + txt_UF.getText(); 
		Usuario usuario = new Usuario(nome,email,password,endereco);
		usuario.cadastraUsuario(usuario);
		
	}
	
	public void setVisible(boolean b) {
		frame.setVisible(true);		
	}
}
