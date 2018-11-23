package br.ufsc.ine5605.mercado.view;

import br.ufsc.ine5605.mercado.controller.ControladorLoja;
import br.ufsc.ine5605.mercado.controller.ControladorPessoa;
import br.ufsc.ine5605.mercado.model.TipoProduto;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TelaAddProdutoJFrame extends JFrame {
    private JLabel lbTitulo;
    private JLabel lbNome;
    private JLabel lbTipo;
    private JLabel lbPreco;
    private JLabel lbLegenda;
    
    private JTextField tfNome;
    private JTextField tfTipo;
    private JTextField tfPreco;

    private JButton btAdicionar;
    private JButton btVoltar;
    
    private GerenciadorBotoes btManager;

    public TelaAddProdutoJFrame() {
        super("Tela de cadastro de produto");
        btManager = new GerenciadorBotoes();
        configuraTela();
    }

    public void configuraTela() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        
        GridBagConstraints cons = new GridBagConstraints();

        //Configura Tela

        //Configura label
        lbLegenda = new JLabel();
        lbLegenda.setText("Tipos: " + TipoProduto.LIMPEZA.getTipo() + " - " + TipoProduto.LIMPEZA + "    " + TipoProduto.FRIOS.getTipo() + " - " + TipoProduto.FRIOS + "    " + TipoProduto.FRUTAS.getTipo() + " - " + TipoProduto.FRUTAS + "    " + TipoProduto.BEBIDA.getTipo() + " - " + TipoProduto.BEBIDA);
        cons.gridy = -1;
        cons.gridx = 1;
        container.add(lbLegenda, cons);
        
        lbNome = new JLabel();
        lbNome.setText("Nome do produto:");
        cons.gridy = 1;
        cons.gridx = 1;
        container.add(lbNome, cons);

        //Configura campo de field nome
        tfNome = new JTextField();
        tfNome.setColumns(7);
        cons.gridy = 1;
        cons.gridx = 2;
        container.add(tfNome, cons);

        //Configura label do tipo de produto
        lbTipo = new JLabel();
        lbTipo.setText("Tipo do produto");
        cons.gridy = 2;
        cons.gridx = 1;
        container.add(lbTipo, cons);

        //Configura campo de field tipo
        tfTipo = new JTextField();
        tfTipo.setColumns(7);
        cons.gridy = 2;
        cons.gridx = 2;
        container.add(tfTipo, cons);

        //Configura label de preço
        lbPreco = new JLabel();
        lbPreco.setText("Preço do produto:");
        cons.gridy = 3;
        cons.gridx = 1;
        container.add(lbPreco, cons);

        //Configura campo de field preço
        tfPreco = new JTextField();
        tfPreco.setColumns(7);
        cons.gridy = 3;
        cons.gridx = 2;
        container.add(tfPreco, cons);       

        //Configura button cadastro
        btAdicionar = new JButton();
        btAdicionar.setText("Adicionar");
        btAdicionar.addActionListener(btManager);
        cons.gridy = 5;
        cons.gridx = 2;
        container.add(btAdicionar, cons);

        //Configura  button voltar
        btVoltar = new JButton();
        btVoltar.setText("Voltar");
        btVoltar.addActionListener(btManager);
        cons.gridy = 6;
        cons.gridx = 2;
        container.add(btVoltar, cons);
        
        //Configura frame
        setSize(460, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private class GerenciadorBotoes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btAdicionar)) {
                try {
                ControladorLoja.getInstancia().adicionaProduto(tfNome.getText(), Integer.parseInt(tfTipo.getText()), ControladorPessoa.getInstance().converteStringToFloat(tfPreco.getText()));
                JOptionPane.showMessageDialog(null, "Produto adicionado");                
                } catch (Exception t) {
                    JOptionPane.showMessageDialog(null, t.getMessage(), "Erro de adição de produto", 2);
                }
                
                tfNome.setText("");
                tfTipo.setText("");
                tfPreco.setText("");
                    
            } else if (e.getSource().equals(btVoltar)) {
                ControladorLoja.getInstancia().voltaTelaLoja();
            }
        }
    }
}