package br.ufsc.ine5605.mercado.view;

import br.ufsc.ine5605.mercado.controller.ControladorLoja;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class TelaLojaJFrame extends JFrame {
    
    private JButton btAddProduto;    
    private JButton btListProdutos;
    private JButton btGeraRelatorio;
    private JButton btVoltaLogin;
    private GerenciadorBotoes btManager;   
    
    public TelaLojaJFrame() {        
        super("Tela principal da loja"); 
        btManager = new GerenciadorBotoes();
        configuraTela();        
    }

    public void configuraTela() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        
        GridBagConstraints cons = new GridBagConstraints();
        
        //Configuracao dos componentes
        
        //Configura label btAddProduto
        btAddProduto = new JButton("Adicionar produto");
        cons.gridx = 1;
        cons.gridy = 1;
        btAddProduto.addActionListener(btManager);
        container.add(btAddProduto, cons);
       
        //Configura label listProdutos
        btListProdutos = new JButton("Listar produtos");
        cons.gridx = 1;
        cons.gridy = 3;
        btListProdutos.addActionListener(btManager);
        container.add(btListProdutos, cons);

        //Configura label geraRelatorio
        btGeraRelatorio = new JButton("Gerar relatório geral");
        cons.gridx = 1;
        cons.gridy = 4;
        btGeraRelatorio.addActionListener(btManager);
        container.add(btGeraRelatorio, cons);
                
        //Configura label voltaLogin
        btVoltaLogin = new JButton("Voltar");
        cons.gridx = 1;
        cons.gridy = 8;
        btVoltaLogin.addActionListener(btManager);
        container.add(btVoltaLogin, cons);       
        
        
        //Configura Frame
        setSize(460, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);        
    }
    
    public void mostraTela() {
        this.setVisible(true);
    }
    
    private class GerenciadorBotoes implements ActionListener {            
        @Override
        public void actionPerformed(ActionEvent e) {
           if (e.getSource().equals(btAddProduto)) {
               ControladorLoja.getInstancia().mostraTelaAddProduto();
           } else if (e.getSource().equals(btListProdutos)) {
               ControladorLoja.getInstancia().mostraTelaLista();
           } else if (e.getSource().equals(btGeraRelatorio)) {
               ControladorLoja.getInstancia().mostraTelaRelatorios();
           } else if (e.getSource().equals(btVoltaLogin)) {
               ControladorLoja.getInstancia().voltarTelaLoginSwing();
           }         
    }
        
    }
}