package projeto.redesocial;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class Tela_EditarPerfil extends Tela_Principal {
	
	Perfil perfil;
    JFrame frame;
    JTextField txt_Atualizacao;
    private Usuario usuario;
	private static int ID_UsuarioLogado;    
    
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
            	int idUsuarioLogado = 1;
                	Tela_EditarPerfil window = new Tela_EditarPerfil(idUsuarioLogado);
                   
                    window.frame.setVisible(true);
            }
        });
    }
    
    /*
     * Aqui estamos recebendo por parametro o ID do usuario logado
     * e atribuindo a variavel ID_UsuarioLogado da classe o valor para realizar
     * as movimentações adequadas
     */
    public Tela_EditarPerfil(int iDUsuarioLogado) {
    	this.ID_UsuarioLogado = iDUsuarioLogado;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 486, 614);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        usuario = new Usuario();

        JLabel lbl_EditarPerfil = new JLabel("EDITAR PERFIL");
        lbl_EditarPerfil.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        lbl_EditarPerfil.setBounds(149, 26, 183, 24);
        frame.getContentPane().add(lbl_EditarPerfil);

        JComboBox<String> cb_Alteracao = new JComboBox<>();
        cb_Alteracao.setBounds(43, 276, 382, 47);
        frame.getContentPane().add(cb_Alteracao);
        cb_Alteracao.addItem("O que deseja editar:"); // Opções do comboBox
        cb_Alteracao.addItem("Nome");
        cb_Alteracao.addItem("E-mail");
        cb_Alteracao.addItem("Senha");
        
        /*
         * Condições do botão para chamar metodos diferentes de acordo com a escolha
         */
        JButton btn_Editar = new JButton("SALVAR");
        btn_Editar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String atualizacao = txt_Atualizacao.getText();
                if (cb_Alteracao.getSelectedItem().equals("Nome")) {
                    try {
                        usuario.editarNome(ID_UsuarioLogado, atualizacao);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                } else if (cb_Alteracao.getSelectedItem().equals("E-mail")) {
                    try {
                        usuario.editarEmail(ID_UsuarioLogado, atualizacao);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                } else if (cb_Alteracao.getSelectedItem().equals("Senha")) {
                    try {
                        usuario.editarSenha(ID_UsuarioLogado, atualizacao);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        btn_Editar.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        btn_Editar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn_Editar.setBounds(60, 474, 163, 47);
        frame.getContentPane().add(btn_Editar);

        JButton btn_Voltar = new JButton("VOLTAR");
        btn_Voltar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();
        	}
        });
        btn_Voltar.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        btn_Voltar.setAlignmentX(0.5f);
        btn_Voltar.setBounds(256, 474, 156, 47);
        frame.getContentPane().add(btn_Voltar);

        JTextPane txt_Dados = new JTextPane();
    	txt_Dados.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
        txt_Dados.setEditable(false);
        txt_Dados.setBounds(43, 113, 382, 139);
        frame.getContentPane().add(txt_Dados);       
     // chamando o metodo para obter todos os dados do usuario logado e armazenando em uma variavel auxiliar
        Usuario aux = usuario.obterPerfilUsuario(ID_UsuarioLogado); 
        txt_Dados.setText("Usuário: " + aux.getNome() + "\nE-mail:" + aux.getEmail() + "\nEndereco:" + aux.getEndereco());

        JLabel lbl_Dados = new JLabel("Seus dados:");
        lbl_Dados.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        lbl_Dados.setBounds(43, 79, 183, 24);
        frame.getContentPane().add(lbl_Dados);
        
        JLabel lbl_Novo = new JLabel("O que deseja editar:");
        lbl_Novo.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        lbl_Novo.setBounds(43, 362, 183, 24);
        frame.getContentPane().add(lbl_Novo);

        txt_Atualizacao = new JTextField();
        txt_Atualizacao.setBounds(43, 396, 382, 47);
        frame.getContentPane().add(txt_Atualizacao);
        txt_Atualizacao.setColumns(10);

        // Bloqueando os campos e botões conforme a seleção inicial
        txt_Atualizacao.setEditable(false);
        btn_Editar.setEnabled(false);
        
        /*
         * Condição para aparecer as mensagens 
         * Condição para o campo receber tipos de dados diferentes
         * Condição para que só esteja disponivel se o usuario escolhar algo
         */
        cb_Alteracao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) cb_Alteracao.getSelectedItem();

                if (selectedItem.equals("Nome")) {
                    lbl_Novo.setText("Edite o nome:");
                    txt_Atualizacao.setEditable(true);
                    btn_Editar.setEnabled(true);
                } else if (selectedItem.equals("E-mail")) {
                    lbl_Novo.setText("Edite seu e-mail:");
                    txt_Atualizacao.setEditable(true);
                    btn_Editar.setEnabled(true);
                } else if (selectedItem.equals("Senha")) {
                    lbl_Novo.setText("Nova senha:");
                    txt_Atualizacao.setEditable(true);
                    btn_Editar.setEnabled(true);
                } else {
                    lbl_Novo.setText("O que deseja editar:");
                    txt_Atualizacao.setEditable(false);
                    btn_Editar.setEnabled(false);
                }
            }
        });
    }
     
    public void setVisible(boolean b) {
		frame.setVisible(true);		
	}
}