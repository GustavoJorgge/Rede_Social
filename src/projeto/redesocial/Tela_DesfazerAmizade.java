package projeto.redesocial;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Tela_DesfazerAmizade extends Tela_AdicionarAmigo{

	private JFrame frame;
	private JTextField txt_Amigo;
	private JTextField txt_Id_Amigo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_DesfazerAmizade window = new Tela_DesfazerAmizade(idUsuarioLogado);
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
	public Tela_DesfazerAmizade(int idUsuarioLogado) {
		this.idUsuarioLogado = idUsuarioLogado;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 510, 621);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_DesfazerAmizade = new JLabel("DESFAZER AMIZADE");
		lbl_DesfazerAmizade.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lbl_DesfazerAmizade.setBounds(135, 29, 228, 28);
		frame.getContentPane().add(lbl_DesfazerAmizade);
		
		JLabel lbl_Buscar = new JLabel("Buscar amigo:");
		lbl_Buscar.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lbl_Buscar.setBounds(29, 94, 164, 23);
		frame.getContentPane().add(lbl_Buscar);
		
		txt_Amigo = new JTextField();
		txt_Amigo.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		txt_Amigo.setColumns(10);
		txt_Amigo.setBounds(179, 80, 251, 37);
		frame.getContentPane().add(txt_Amigo);
		
		JButton btn_Buscar = new JButton("BUSCAR");
		btn_Buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Amigo amigo = new Amigo();
				String nome = txt_Amigo.getName();
				
				try {
					amigo.listar_Amigos(idUsuarioLogado);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_Buscar.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btn_Buscar.setBounds(282, 126, 148, 35);
		frame.getContentPane().add(btn_Buscar);
		
		JTextArea txt_ListAmigos = new JTextArea();
		txt_ListAmigos.setEditable(false);
		txt_ListAmigos.setBounds(58, 171, 372, 215);
		frame.getContentPane().add(txt_ListAmigos);
		
		JLabel lbl_ID_Usuario = new JLabel("<html>ID do amigo que<br> deseja remover:</html>");
		lbl_ID_Usuario.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lbl_ID_Usuario.setBounds(58, 410, 147, 60);
		frame.getContentPane().add(lbl_ID_Usuario);
		
		txt_Id_Amigo = new JTextField();
		txt_Id_Amigo.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		txt_Id_Amigo.setColumns(10);
		txt_Id_Amigo.setBounds(195, 422, 232, 44);
		frame.getContentPane().add(txt_Id_Amigo);
		
		JButton btn_Remover = new JButton("REMOVER");
		btn_Remover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Remover.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btn_Remover.setBounds(36, 480, 164, 37);
		frame.getContentPane().add(btn_Remover);
		
		JButton btn_Voltar = new JButton("VOLTAR");
		btn_Voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btn_Voltar.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btn_Voltar.setBounds(266, 480, 164, 37);
		frame.getContentPane().add(btn_Voltar);
	}
}
