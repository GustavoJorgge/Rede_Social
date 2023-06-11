package projeto.redesocial;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Tela_AdicionarAmigo {

	private JFrame frame;
	private JTextField txt_BuscaUsuario;
	private JTextField txt_ID_Add;
	protected int idUsuarioLogado; // variavel para armazenar o id de quem esta conectado

	//Metodo para receber o id do usuario conectado
	public void setIdUsuario(int idUsuarioLogado) {
		this.idUsuarioLogado = idUsuarioLogado;
	}
	
	
	public int getIdUsuarioLogado() {
		return idUsuarioLogado;
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_AdicionarAmigo window = new Tela_AdicionarAmigo();
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
	public Tela_AdicionarAmigo() {
        initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 470, 595);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_AdicionarAmigo = new JLabel("Adicionar Amigo");
		lbl_AdicionarAmigo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_AdicionarAmigo.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lbl_AdicionarAmigo.setBounds(112, 27, 226, 35);
		frame.getContentPane().add(lbl_AdicionarAmigo);
		
		JLabel lbl_Buscar = new JLabel("Buscar usuario:");
		lbl_Buscar.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lbl_Buscar.setBounds(22, 84, 164, 23);
		frame.getContentPane().add(lbl_Buscar);
		
		txt_BuscaUsuario = new JTextField();
		txt_BuscaUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		txt_BuscaUsuario.setBounds(184, 78, 232, 37);
		frame.getContentPane().add(txt_BuscaUsuario);
		txt_BuscaUsuario.setColumns(10);
		
		JTextArea txt_ListUsuarios = new JTextArea();
		txt_ListUsuarios.setEditable(false);
		txt_ListUsuarios.setBounds(44, 170, 372, 215);
		frame.getContentPane().add(txt_ListUsuarios);
		
		JLabel lbl_ID_Usuario = new JLabel("<html>ID do usuario que<br> deseja adicionar:</html>");
		lbl_ID_Usuario.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lbl_ID_Usuario.setBounds(22, 411, 147, 60);
		frame.getContentPane().add(lbl_ID_Usuario);
		
		txt_ID_Add = new JTextField();
		txt_ID_Add.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		txt_ID_Add.setBounds(184, 427, 232, 44);
		frame.getContentPane().add(txt_ID_Add);
		txt_ID_Add.setColumns(10);
		
		JButton btn_Adicionar = new JButton("ADICIONAR");
		btn_Adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Amigo amigo = new Amigo();
				
				int id_amigo = Integer.parseInt(txt_ID_Add.getText());
				
				try {
					//passando por parametro o id do usuario conectado e o id do amigo que deseja adicionar
					amigo.adicionar_Amigo(idUsuarioLogado,id_amigo);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btn_Adicionar.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btn_Adicionar.setBounds(22, 496, 164, 37);
		frame.getContentPane().add(btn_Adicionar);
		
		JButton btn_Voltar = new JButton("VOLTAR");
		btn_Voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btn_Voltar.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btn_Voltar.setBounds(252, 496, 164, 37);
		frame.getContentPane().add(btn_Voltar);
		
		JButton btn_Buscar = new JButton("BUSCAR");
		btn_Buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Amigo amigo = new Amigo();
				String consulta = txt_BuscaUsuario.getText();
				try {
					amigo.consultar_Usuario(consulta);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				txt_ListUsuarios.setText(amigo.getResultadoConsulta().toString());
			}
		});
		btn_Buscar.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btn_Buscar.setBounds(268, 125, 148, 35);
		frame.getContentPane().add(btn_Buscar);
	}

	public void setVisible(boolean b) {
		frame.setVisible(true);		
	}
}
