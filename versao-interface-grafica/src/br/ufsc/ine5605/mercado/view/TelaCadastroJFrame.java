package br.ufsc.ine5605.mercado.view;

import br.ufsc.ine5605.mercado.controller.ControladorPessoa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroJFrame extends JFrame {
    private JLabel lbTitulo;
    private JLabel lbNome;
    private JLabel lbCpf;
    private JLabel lbUsuario;
    private JLabel lbSenha;
    private JTextField tfSenha;
    private JTextField tfUsuario;
    private JTextField tfnome;
    private JTextField tfCpf;


    private JButton btCadastrar;
    private JButton btVoltar;

    private ControladorPessoa ctrlPessoa;

    private GerenciadorBotoes btManager;

    public void setControladorPessoa(ControladorPessoa ctrlPessoa) {
        this.ctrlPessoa = ctrlPessoa;
    }

    public void mostraTela() {
        this.setVisible(true);
    }

    public void escondeTela() {
        this.setVisible(false);
        tfnome.setText("");
        tfCpf.setText("");
        tfUsuario.setText("");
        tfSenha.setText("");
    }


    public TelaCadastroJFrame() {
        super("Tela de Cadastro");
        btManager = new GerenciadorBotoes();
        configuraTelaIniciol();
    }

    public TelaCadastroJFrame(ControladorPessoa ctrlPessoa) {
        super("Tela de Cadastro");
        this.ctrlPessoa = ctrlPessoa;
        btManager = new GerenciadorBotoes();
        configuraTelaIniciol();
    }

    public void configuraTelaIniciol() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();

        //Configura Tela

        //Configura label do nome
        lbNome = new JLabel();
        lbNome.setText("Nome");
        cons.gridy = 1;
        cons.gridx = 1;
        container.add(lbNome, cons);

        //Configura textfield de nome
        tfnome = new JTextField();
        tfnome.setColumns(7);
        cons.gridy = 1;
        cons.gridx = 2;
        container.add(tfnome, cons);

        //Configura label do cpf
        lbCpf = new JLabel();
        lbCpf.setText("CPF");
        cons.gridy = 2;
        cons.gridx = 1;
        container.add(lbCpf, cons);

        //Configura textfield do cpf
        tfCpf = new JTextField();
        tfCpf.setColumns(7);
        cons.gridy = 2;
        cons.gridx = 2;
        container.add(tfCpf, cons);

        //Configura label
        lbUsuario = new JLabel();
        lbUsuario.setText("Usuário");
        cons.gridy = 3;
        cons.gridx = 1;
        container.add(lbUsuario, cons);

        //Configura campo de field usuario
        tfUsuario = new JTextField();
        tfUsuario.setColumns(7);
        cons.gridy = 3;
        cons.gridx = 2;
        container.add(tfUsuario, cons);

        //Configura label
        lbSenha = new JLabel();
        lbSenha.setText("Senha");
        cons.gridy = 4;
        cons.gridx = 1;
        container.add(lbSenha, cons);

        //Configura campo de field senha
        tfSenha = new JTextField();
        tfSenha.setColumns(7);
        cons.gridy = 4;
        cons.gridx = 2;
        container.add(tfSenha, cons);

        //Configura button Cadastrar
        btCadastrar = new JButton();
        btCadastrar.setText("Cadastrar");
        btCadastrar.addActionListener(btManager);
        cons.gridy = 5;
        cons.gridx = 2;
        container.add(btCadastrar, cons);

        //Configura button voltar
        btVoltar = new JButton();
        btVoltar.setText("Voltar");
        btVoltar.addActionListener(btManager);
        cons.gridy = 6;
        cons.gridx = 2;
        container.add(btVoltar, cons);

        setSize(460, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private class GerenciadorBotoes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btCadastrar)) {
                if (!(ControladorPessoa.getInstance().validaCpfUsuasrio(tfCpf.getText()) && ControladorPessoa.getInstance().validaNomeUsuasrio(tfnome.getText()))) {

                    ControladorPessoa.getInstance().CadastraCliente(tfnome.getText(), tfCpf.getText(), tfUsuario.getText(), tfSenha.getText());

                    JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso"
                            , "Mercado ", 2);
                    ControladorPessoa.getInstance().mostraTelaLogin();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou CPF já consta na lista de cadastros"
                            , "Erro ", 2);
                    tfCpf.setText("");
                    tfUsuario.setText("");
                }


            } else if (e.getSource().equals(btVoltar)) {
                ControladorPessoa.getInstance().mostraTelaLogin();
            }
        }

    }
}