package br.ufsc.ine5605.mercado.view;

import br.ufsc.ine5605.mercado.controller.ControladorLoja;
import br.ufsc.ine5605.mercado.model.TipoProduto;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TelaListaProdutoJTable extends JFrame {
    private JButton btDeletar;
    private JButton btVoltar;
    private JButton btAlterarPreco;    
    
    private JTable jtProdutos;
    private JScrollPane spBaseTabela;
   
    private GerenciadorTabela tbManager;
    private GerenciadorBotoes btManager;
    
    private int codigo;
    public ArrayList<ConteudoTelaLoja> conteudoTelaLoja;    
    
    public TelaListaProdutoJTable() {
        super("Tela de produtos");
        btManager = new GerenciadorBotoes();
        tbManager = new GerenciadorTabela();
        conteudoTelaLoja = new ArrayList<>();
        configuraTela();       
        updateData();
    }
    
    public void configuraTela() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());

        GridBagConstraints cons = new GridBagConstraints();
        
        //Configura button alterar preço
        btAlterarPreco = new JButton();
        btAlterarPreco.setText("Alterar preço");
        btAlterarPreco.addActionListener(btManager);
        cons.gridy = 6;
        cons.gridx = 0;
        container.add(btAlterarPreco, cons);
        
        //Configura button deletar
        btDeletar = new JButton();
        btDeletar.setText("Deletar");
        btDeletar.addActionListener(btManager);
        cons.gridy = 6;
        cons.gridx = 1;
        container.add(btDeletar, cons);

        //Configura button voltar
        btVoltar = new JButton();
        btVoltar.setText("Voltar");
        btVoltar.addActionListener(btManager);
        cons.gridy = 6;
        cons.gridx = 2;
        container.add(btVoltar, cons);
        
        jtProdutos = new JTable();
        jtProdutos.setPreferredScrollableViewportSize(new Dimension(300, 200));
        jtProdutos.setFillsViewportHeight(true);
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridheight = 1;
        cons.gridwidth = 4;
        cons.gridx = 5;
        cons.gridy = 3;
        spBaseTabela = new JScrollPane(jtProdutos);
        jtProdutos.addMouseListener(tbManager);
        container.add(spBaseTabela, cons);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }
    
    private class GerenciadorBotoes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btAlterarPreco)) {
                ControladorLoja.getInstancia().mostraTelaAlteraPreco(codigo);
            } else if (e.getSource().equals(btDeletar)) {
                try {
                    ControladorLoja.getInstancia().deletaProduto(codigo);
                    System.out.println(codigo);
                    updateData();
                    JOptionPane.showMessageDialog(null, "Produto deletado com sucesso!"); 
                } catch (Exception t) {
                    JOptionPane.showMessageDialog(null, t.getMessage(), "Erro ao deletar o produto!", 2);
                }
                
            } else if (e.getSource().equals(btVoltar)) {
                ControladorLoja.getInstancia().voltaTelaLoja();
            } 
            
        }
    }
    
    private class GerenciadorTabela implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
             codigo = Integer.valueOf(jtProdutos.getValueAt(jtProdutos.getSelectedRow(), 3).toString());
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
    
    private void updateData() {      
        DefaultTableModel modelTdItens = new DefaultTableModel();
        modelTdItens.addColumn("Nome");
        modelTdItens.addColumn("Tipo");
        modelTdItens.addColumn("Preço");
        modelTdItens.addColumn("Codigo");
        for (ConteudoTelaLoja p : conteudoTelaLoja) {
            modelTdItens.addRow(new Object[]{p.nome, TipoProduto.DEFAULT.selecionaTipo(p.tipoProduto), p.preco, p.codigo});
        }
        jtProdutos.setModel(modelTdItens);
        this.repaint();
    }
    
    public void setConteudoTelaLoja(ArrayList<ConteudoTelaLoja> lista) {
        this.conteudoTelaLoja = lista;
    }
    
    public void mostraTela() {
        this.updateData();
        this.setVisible(true);
    }
    
}