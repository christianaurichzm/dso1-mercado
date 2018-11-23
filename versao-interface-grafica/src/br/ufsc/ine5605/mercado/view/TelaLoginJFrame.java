package br.ufsc.ine5605.mercado.view;

import br.ufsc.ine5605.mercado.controller.ControladorPessoa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLoginJFrame extends JFrame {

    private JLabel lbUsuario;
    private JLabel lbSenha;
    private JTextField tfSenha;
    private JTextField tfUsuario;

    private JButton btCadastrar;
    private JButton btLogar;
    private JButton btVoltar;
    private JButton btValidar;

    private ControladorPessoa ctrlPessoa;

    private GerenciadorBotoes btManager;

    public void mostraTela() {
        this.setVisible(true);
    }

    public void escondeTela() {
        this.setVisible(false);
        tfUsuario.setText("");
        tfSenha.setText("");
    }

    public TelaLoginJFrame(int numero) {
        super("Tela de Login");
        if(numero == 2) {
            btManager = new GerenciadorBotoes();
            configuraTelaDeposito();
        }else if(numero ==1){
            btManager = new GerenciadorBotoes();
            configuraTelaIniciol();
        }
    }

    public TelaLoginJFrame() {
        super("Tela de Login");
        btManager = new GerenciadorBotoes();
        configuraTelaIniciol();
    }

    public TelaLoginJFrame(ControladorPessoa ctrlPessoa) {
        super("Tela de Login");
        this.ctrlPessoa = ctrlPessoa;
        btManager = new GerenciadorBotoes();
        configuraTelaIniciol();
    }

    public void configuraTelaIniciol() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();

        //Configura label
        lbUsuario = new JLabel();
        lbUsuario.setText("Usuario");
        cons.gridy = 4;
        cons.gridx = 1;
        container.add(lbUsuario, cons);
       
        tfUsuario = new JTextField();
        tfUsuario.setColumns(5);
        cons.gridy = 5;
        cons.gridx = 1;
        container.add(tfUsuario, cons);
        
        lbSenha = new JLabel();
        lbSenha.setText("Senha");
        cons.gridy = 6;
        cons.gridx = 1;
        container.add(lbSenha, cons);
        
        tfSenha = new JTextField();
        tfSenha.setColumns(5);
        cons.gridy = 7;
        cons.gridx = 1;
        container.add(tfSenha, cons);

        btLogar = new JButton();
        btLogar.setText("Acessar");
        btLogar.addActionListener(btManager);
        cons.gridy = 8;
        cons.gridx = 1;
        container.add(btLogar, cons);

        btCadastrar = new JButton();
        btCadastrar.setText("Nova conta");
        btCadastrar.addActionListener(btManager);
        cons.gridy = 9;
        cons.gridx = 1;
        container.add(btCadastrar, cons);

        setSize(460, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }


    public void configuraTelaDeposito() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();

        //Configura label de usuario
        lbUsuario = new JLabel();
        lbUsuario.setText("Usuario");
        cons.gridy = 4;
        cons.gridx = 1;
        container.add(lbUsuario, cons);

        //Configura textfield de usuario
        tfUsuario = new JTextField();
        tfUsuario.setColumns(5);
        cons.gridy = 5;
        cons.gridx = 1;
        container.add(tfUsuario, cons);

        //Configura label de senha
        lbSenha = new JLabel();
        lbSenha.setText("Senha");
        cons.gridy = 6;
        cons.gridx = 1;
        container.add(lbSenha, cons);

        //Configura textfield de senha
        tfSenha = new JTextField();
        tfSenha.setColumns(5);
        cons.gridy = 7;
        cons.gridx = 1;
        container.add(tfSenha, cons);

        btValidar = new JButton();
        btValidar.setText("Validar");
        btValidar.addActionListener(btManager);
        cons.gridy = 8;
        cons.gridx = 1;
        container.add(btValidar, cons);

        btVoltar = new JButton();
        btVoltar.setText("Voltar");
        btVoltar.addActionListener(btManager);
        cons.gridy = 9;
        cons.gridx = 1;
        container.add(btVoltar, cons);

        setSize(460, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private class GerenciadorBotoes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btLogar)) {
                if (!(ControladorPessoa.getInstance().logar(tfUsuario.getText(), tfSenha.getText()))) {
                    JOptionPane.showMessageDialog(null, "Usuario ou senha incorretos "
                            , "Erro de login ", 2);

                }
            } else if (e.getSource().equals(btCadastrar)) {
                ControladorPessoa.getInstance().mostraTelaCadastro();
            } else if (e.getSource().equals(btValidar)) {
                if ( ControladorPessoa.getInstance().encontraCliente(tfUsuario.getText(), tfSenha.getText()) != null) {
                    if ( ControladorPessoa.getInstance().encontraCliente(tfUsuario.getText(), tfSenha.getText()).getUsuario().equals(tfUsuario.getText())
                            && ControladorPessoa.getInstance().encontraCliente(tfUsuario.getText(), tfSenha.getText()).getSenha().equals(tfSenha.getText())) {
                        ControladorPessoa.getInstance().mostraTelaDeposito();

                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario ou senha incorretos "
                                , "Erro de login ", 2);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario ou senha incorretos "
                            , "Erro de login ", 2);
                }
            } else if (e.getSource().equals(btVoltar)) {
                ControladorPessoa.getInstance().mostraTelaPessoa();
            }

        }

    }
}