package projeto.redesocial;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Tela_Amigos extends Tela_AdicionarAmigo {

	private JFrame frame;
		
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                int idUsuarioLogado = 1; // Substitua pelo valor correto do ID do usuário logado
	                Tela_Amigos window = new Tela_Amigos(idUsuarioLogado);
	                window.frame.setVisible(true);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });
	}

	
	/*
	 * neste construtor eu estou recebendo como parametro o id do usuario que esta logado.
	 * Esta recebendo ta Tela principal
	 */
	
	 public Tela_Amigos(int idUsuarioLogado) {
	        this.idUsuarioLogado = idUsuarioLogado;
	        try {
	            initialize();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 486, 614);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_ListaAmigos = new JLabel("AMIGOS ADICIONADOS");
		lbl_ListaAmigos.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lbl_ListaAmigos.setBounds(105, 33, 262, 34);
		frame.getContentPane().add(lbl_ListaAmigos);
		
		JTextArea txt_ListaAmigos = new JTextArea();
		txt_ListaAmigos.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txt_ListaAmigos.setEditable(false);
		txt_ListaAmigos.setBounds(60, 92, 347, 305);
		frame.getContentPane().add(txt_ListaAmigos);
		
		Amigo amigo = new Amigo();
		//JOptionPane.showMessageDialog(idUsuarioLogado);
		amigo.listar_Amigos(idUsuarioLogado);
		txt_ListaAmigos.setText(amigo.getListaAmigos().toString());
		
		JLabel lbl_TotalAmigos = new JLabel("Total de Amigos:");
		lbl_TotalAmigos.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		lbl_TotalAmigos.setBounds(41, 414, 169, 22);
		frame.getContentPane().add(lbl_TotalAmigos);
		
		JButton btn_DeletarAmigo = new JButton("<html>DESFAZER<br> AMIZADE</html>");
		btn_DeletarAmigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela_DesfazerAmizade telaDesfazerAmizade = new Tela_DesfazerAmizade(idUsuarioLogado);
				telaDesfazerAmizade.setVisible(true);
			}
		});
		btn_DeletarAmigo.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btn_DeletarAmigo.setBounds(30, 460, 136, 57);
		frame.getContentPane().add(btn_DeletarAmigo);
		
		JButton btn_AddAmigo = new JButton("<html>ADICIONAR<br> AMIGO</html>");
		btn_AddAmigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela_AdicionarAmigo telaAdicionarAmigo = new Tela_AdicionarAmigo();
				telaAdicionarAmigo.setVisible(true);
			}
		});
		btn_AddAmigo.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btn_AddAmigo.setBounds(176, 460, 136, 57);
		frame.getContentPane().add(btn_AddAmigo);
		
		JButton btn_Voltar = new JButton("VOLTAR");
		btn_Voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btn_Voltar.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btn_Voltar.setBounds(322, 460, 136, 57);
		frame.getContentPane().add(btn_Voltar);
	}

	public void setVisible(boolean b) {
		frame.setVisible(true);		
	}
}
