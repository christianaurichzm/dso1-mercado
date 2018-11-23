package br.ufsc.ine5605.mercado.view;

import br.ufsc.ine5605.mercado.controller.ControladorPessoa;
import br.ufsc.ine5605.mercado.exceptions.CarrinhoVazioException;
import br.ufsc.ine5605.mercado.exceptions.SaldoInsuficienteException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPessoaJFrame extends JFrame {

    /**
     * 1 - Ver saldo
     * 2 - Depositar valor na conta
     * 3 - Listar produtos por tipo
     * 4 - Deletar produto carrinho
     * 5 - Ver carrinho
     * 6 - Finalizar compra
     * 7 - Relatorio de compras Feitas
     * 0 - Sair
     */

    private JLabel lbTitulo;
    private JButton btSaldo;
    private JButton btDepositar;
    private JButton btListaProdutos;
    private JButton btVerCarrinho;
    private JButton btFinalizar;
    private JButton btRelatorio;
    private JButton btConfirma;
    private JButton btCancela;
    private JButton btSair;
    private ControladorPessoa ctrlPessoa;

    private GerenciadorBotoes btManager;

    public void setControladorPessoa(ControladorPessoa ctrlPessoa) {
        this.ctrlPessoa = ctrlPessoa;
    }

    public TelaPessoaJFrame() {
        super("Tela Principal");
        btManager = new GerenciadorBotoes();
        configuraTelaIniciol();
    }

    public TelaPessoaJFrame(ControladorPessoa ctrlPessoa) {
        super("Tela Principal");
        this.ctrlPessoa = ctrlPessoa;
        btManager = new GerenciadorBotoes();
        configuraTelaIniciol();
    }

    private void configuraTelaIniciol() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();

        //Configura componentes
  
        //Configura button de saldo
        btSaldo = new JButton();
        btSaldo.setText("Ver saldo");
        btSaldo.addActionListener(btManager);
        cons.gridy = 1;
        cons.gridx = 1;
        container.add(btSaldo, cons);

        //Configura button de deposito
        btDepositar = new JButton();
        btDepositar.setText("Depositar");
        btDepositar.addActionListener(btManager);
        cons.gridy = 2;
        cons.gridx = 1;
        container.add(btDepositar, cons);

        //Configura button de corredores
        btListaProdutos = new JButton();
        btListaProdutos.setText("Listar corredores");
        btListaProdutos.addActionListener(btManager);
        cons.gridy = 3;
        cons.gridx = 1;
        container.add(btListaProdutos, cons);

        btVerCarrinho = new JButton();
        btVerCarrinho.setText("Ver carrinho");
        btVerCarrinho.addActionListener(btManager);
        cons.gridy = 4;
        cons.gridx = 1;
        container.add(btVerCarrinho, cons);

        btFinalizar = new JButton();
        btFinalizar.setText("Finalizar compra");
        btFinalizar.addActionListener(btManager);
        cons.gridy = 5;
        cons.gridx = 1;
        container.add(btFinalizar, cons);

        btRelatorio = new JButton();
        btRelatorio.setText("Relatorio de compras");
        btRelatorio.addActionListener(btManager);
        cons.gridy = 6;
        cons.gridx = 1;
        container.add(btRelatorio, cons);

        btSair = new JButton();
        btSair.setText("Voltar");
        btSair.addActionListener(btManager);
        cons.gridy = 8;
        cons.gridx = 1;
        container.add(btSair, cons);

        //Configura frame
        setSize(460, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private class GerenciadorBotoes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btListaProdutos)) {
                ControladorPessoa.getInstance().mostraTabela();

            } else if (e.getSource().equals(btSair)) {
                ControladorPessoa.getInstance().mostraTelaLogin();

            } else if (e.getSource().equals(btSaldo)) {
                JOptionPane.showMessageDialog(null, "Seu saldo é "
                        + ControladorPessoa.getInstance().getCliente().getSaldo(), "Mercado ", 2);

            } else if (e.getSource().equals(btDepositar)) {
                ControladorPessoa.getInstance().mostraTelaValidaLogin();

            } else if (e.getSource().equals(btVerCarrinho)) {
                ControladorPessoa.getInstance().mostraTelaCarrinhoTabela();

            } else if (e.getSource().equals(btFinalizar)) {
                try {
                    if (ControladorPessoa.getInstance().getCliente().getCarrinho().size() == 0) {
                        throw new CarrinhoVazioException();
                    } else if (ControladorPessoa.getInstance().getCustoTotalCarrinho() > ControladorPessoa.getInstance().getUsuario().getSaldo()) {
                        throw new SaldoInsuficienteException();
                    } else {
                        ControladorPessoa.getInstance().finalizaCarrinho();
                        JOptionPane.showMessageDialog(null, ControladorPessoa.getInstance().getUsuario().getUltimoRecibo().GerarRelatorioToString(), "Recibo", 1);
                    }
                } catch (SaldoInsuficienteException t) {
                    JOptionPane.showMessageDialog(null, t.getMessage()
                            , "Erro ", 2);
                }catch (CarrinhoVazioException t) {
                    JOptionPane.showMessageDialog(null, t.getMessage()
                            , "Erro ", 2);
                }
            } else if(e.getSource().equals(btRelatorio)){
                ControladorPessoa.getInstance().mostraTabelaRecibos();
            }
        }

    }

    public void mostraTela() {
        this.setVisible(true);
    }

    public void escondeTela() {
        this.setVisible(false);
    }

}