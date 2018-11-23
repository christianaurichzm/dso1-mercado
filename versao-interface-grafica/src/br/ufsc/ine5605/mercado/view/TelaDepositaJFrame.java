package br.ufsc.ine5605.mercado.view;

import br.ufsc.ine5605.mercado.controller.ControladorPessoa;
import br.ufsc.ine5605.mercado.exceptions.InputStringToFloatException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaDepositaJFrame extends JFrame {
    private JLabel jlDeposita;
    
    private JTextField jtDeposita;

    private JButton btDepositar;
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
        jtDeposita.setText("");
    }

    public TelaDepositaJFrame() {
        super("Tela de Login");
        btManager = new GerenciadorBotoes();
        configuraTelaDeposito();
    }

    public TelaDepositaJFrame(ControladorPessoa ctrlPessoa) {
        super("Tela de deposito");
        this.ctrlPessoa = ctrlPessoa;
        btManager = new GerenciadorBotoes();
        configuraTelaDeposito();
    }

    public void configuraTelaDeposito() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();


        //Configura label
        jlDeposita = new JLabel();
        jlDeposita.setText("Valor do depósito");
        cons.gridy = 2;
        cons.gridx = 1;
        container.add(jlDeposita, cons);

        //Configura textfield de usuario
        jtDeposita = new JTextField();
        jtDeposita.setColumns(5);
        cons.gridy = 2;
        cons.gridx = 2;
        container.add(jtDeposita, cons);


        //Configura button de deposito;
        btDepositar = new JButton();
        btDepositar.setText("Depositar");
        btDepositar.addActionListener(btManager);
        cons.gridy = 4;
        cons.gridx = 2;
        container.add(btDepositar, cons);


        //Configura button de voltar;
        btVoltar = new JButton();
        btVoltar.setText("Voltar");
        btVoltar.addActionListener(btManager);
        cons.gridy = 5;
        cons.gridx = 2;
        container.add(btVoltar, cons);

        setSize(460, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private class GerenciadorBotoes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btDepositar)) {
                try {
                    ControladorPessoa.getInstance().deposito(ControladorPessoa.getInstance().getCliente().getUsuario(), ControladorPessoa.getInstance().getCliente().getSenha(), ControladorPessoa.getInstance().converteStringToFloat(jtDeposita.getText()));
                    JOptionPane.showMessageDialog(null, "Deposito no valor de:  "
                            + ControladorPessoa.getInstance().converteStringToFloat(jtDeposita.getText()), "Deposito ", 2);
                    ControladorPessoa.getInstance().mostraTelaPessoa();
                } catch (Exception f) {
                    JOptionPane.showMessageDialog(null, "Verifique o tipo de dado digitado  "
                            , "Error ", 2);
                }
            } else if (e.getSource().equals(btVoltar)) {
                ControladorPessoa.getInstance().mostraTelaPessoa();
            }

        }

    }
}