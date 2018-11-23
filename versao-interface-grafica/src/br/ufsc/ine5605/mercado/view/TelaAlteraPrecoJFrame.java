package br.ufsc.ine5605.mercado.view;

import br.ufsc.ine5605.mercado.controller.ControladorLoja;
import br.ufsc.ine5605.mercado.controller.ControladorPessoa;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TelaAlteraPrecoJFrame extends JFrame {
    private JLabel lbTitulo;
    private JLabel lbCodigo;
    private JLabel lbPreco;
    
    private JTextField tfCodigo;
    private JTextField tfPreco;

    private GerenciadorBotoes btManager;
    
    private JButton btAlterarPreco;
    private JButton btVoltar;
      
    public TelaAlteraPrecoJFrame() {
        super("Tela de alteração de preço de produto");
        btManager = new GerenciadorBotoes();
        configuraTela();
    }
    
    public void configuraTela() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        
        GridBagConstraints cons = new GridBagConstraints();
        
        //Configura Tela

        //Configura label
        
        lbTitulo = new JLabel();
        lbTitulo.setText("Alterar preço:");
        cons.gridy = -1;
        cons.gridx = 1;
        container.add(lbTitulo, cons);
        
        lbCodigo = new JLabel();
        lbCodigo.setText("Código do produto:");
        cons.gridy = 1;
        cons.gridx = 1;
        container.add(lbCodigo, cons);

        //Configura campo de field codigo
        tfCodigo = new JTextField();
        tfCodigo.setColumns(7);
        cons.gridy = 1;
        cons.gridx = 2;
        container.add(tfCodigo, cons);

        //Configura label
        lbPreco = new JLabel();
        lbPreco.setText("Novo preço do produto:");
        cons.gridy = 2;
        cons.gridx = 1;
        container.add(lbPreco, cons);

        //Configura campo de field tipo
        tfPreco = new JTextField();
        tfPreco.setColumns(7);
        cons.gridy = 2;
        cons.gridx = 2;
        container.add(tfPreco, cons);      
        
        //Configura button alterar preço
        btAlterarPreco = new JButton();
        btAlterarPreco.setText("Alterar preço");
        btAlterarPreco.addActionListener(btManager);
        cons.gridy = 6;
        cons.gridx = 2;
        container.add(btAlterarPreco, cons);
        
        //Configura button voltar
        btVoltar = new JButton();
        btVoltar.setText("Voltar");
        btVoltar.addActionListener(btManager);
        cons.gridy = 6;
        cons.gridx = 3;
        container.add(btVoltar, cons);
        
        //Configura Tela
        setSize(460, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);           
    }
    
     private class GerenciadorBotoes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btAlterarPreco)) {
                try {
                    ControladorLoja.getInstancia().alteraPreco(Integer.parseInt(tfCodigo.getText()), ControladorPessoa.getInstance().converteStringToFloat(tfPreco.getText()));
                     JOptionPane.showMessageDialog(null, "Preço alterado com sucesso!");                     
                } catch (Exception ex) {
                    Logger.getLogger(TelaAlteraPrecoJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource().equals(btVoltar)) {
                setVisible(false);
                ControladorLoja.getInstancia().mostraTelaLista();
            }
        }
    }
    
   
    public void setaCodigo(int codigo) {
        tfCodigo.setText(Integer.toString(codigo));
    }
    
}