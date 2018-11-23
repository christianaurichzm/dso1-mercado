package br.ufsc.ine5605.mercado.view;

import br.ufsc.ine5605.mercado.controller.ControladorLoja;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaRelatorioLojaJFrame extends JFrame {
    
    private int codigo;
    private JButton btVoltar;
    private JButton btVerRecibo;
    private JTable jtRelatorios;
    private JScrollPane spBaseTabela;
    private GerenciadorBotoes btManager;
    private GerenciadorTabela tbManager;
       
    public TelaRelatorioLojaJFrame() {
        super("Tela de relatórios");      
        btManager = new GerenciadorBotoes();
        tbManager = new GerenciadorTabela();
        configura();       
    }
    
    private void configura() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());

        GridBagConstraints cons = new GridBagConstraints();
        
        //Configura button voltar
        btVoltar = new JButton();
        btVoltar.setText("Voltar");
        btVoltar.addActionListener(btManager);
        cons.gridy = 6;
        cons.gridx = 2;
        container.add(btVoltar, cons);

        btVerRecibo = new JButton();
        btVerRecibo.setText("Ver recibo");
        btVerRecibo.addActionListener(btManager);
        cons.gridy = 6;
        cons.gridx = 3;
        container.add(btVerRecibo, cons);

        //Configuração da JTable
        jtRelatorios = new JTable();
        jtRelatorios.setPreferredScrollableViewportSize(new Dimension(300, 200));
        jtRelatorios.setFillsViewportHeight(true);
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridheight = 1;
        cons.gridwidth = 4;
        cons.gridx = 5;
        cons.gridy = 3;
        spBaseTabela = new JScrollPane(jtRelatorios);
        jtRelatorios.addMouseListener(tbManager);
        container.add(spBaseTabela, cons);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }
    
     private void updateData() {      
        DefaultTableModel modelTdItens = new DefaultTableModel();
        modelTdItens.addColumn("Codigo");
        modelTdItens.addColumn("Nome do cliente");
        modelTdItens.addColumn("Numero de itens");
        modelTdItens.addColumn("Valor total da compra");
        for (ConteudoTelaLoja p : getConteudoTela()) {
            modelTdItens.addRow(new Object[]{p.codigo, p.nomeCliente, p.quantidade, p.totalPreco});
        }
        jtRelatorios.setModel(modelTdItens);
        this.repaint();
    }
     
     public ArrayList<ConteudoTelaLoja> getConteudoTela() {
         return ControladorLoja.getInstancia().getListaDeRecibos();
     }
    
      private class GerenciadorBotoes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btVoltar)) {
                ControladorLoja.getInstancia().voltaTelaLoja();
            }else if(e.getSource().equals(btVerRecibo)){
                if (codigo != 0) {
                    JOptionPane.showMessageDialog(null, "\n"
                            + ControladorLoja.getInstancia().getLoja().getThisRecibo(codigo).GerarRelatorioToString(), "\"Recibo numero \" + codigo ", 2);
                    codigo = 0;
                }
                codigo = 0;
            }
        }
    }
     
        private class GerenciadorTabela implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            codigo = Integer.valueOf(jtRelatorios.getValueAt(jtRelatorios.getSelectedRow(), 0).toString());
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }             
        
    }
        public void mostraTela() {
            updateData();
            this.setVisible(true);            
        }        
}