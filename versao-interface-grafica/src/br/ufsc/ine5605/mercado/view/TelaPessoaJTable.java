package br.ufsc.ine5605.mercado.view;

import br.ufsc.ine5605.mercado.controller.ControladorPessoa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class TelaPessoaJTable extends JFrame {
    private ControladorPessoa ctrlPessoa;
    public ArrayList<ConteudoTelaPessoa> conteudoTelaPessoa;
    private JTable jtTabelaCorredor;
    private JScrollPane spBaseTabela;
    private JTextField tfTexto;
    private JButton btAdiciona;
    private JButton btTipoLimpeza;
    private JButton btTipoFrios;
    private JButton btTipoFrutas;
    private JButton btTipoBebidas;
    private JButton btDeleta;
    private JButton btVolta;
    private JButton btVerRecibo;

    private int codigo;

    private GerenciadorBotoes btManager;
    private GerenciadorTabela tbManager;


    public void setControladorPessoa(ControladorPessoa ctrlPessoa) {
        this.ctrlPessoa = ctrlPessoa;
    }


    public TelaPessoaJTable() {

        super("Tela de produtos");
        btManager = new GerenciadorBotoes();
        tbManager = new GerenciadorTabela();
        conteudoTelaPessoa = new ArrayList<>();
        configuraTelaIniciol();
        updateData();

    }


    public TelaPessoaJTable(ControladorPessoa ctrlPessoa) {
        super("Tela de produtos");
        this.ctrlPessoa = ctrlPessoa;
        btManager = new GerenciadorBotoes();
        tbManager = new GerenciadorTabela();
        conteudoTelaPessoa = new ArrayList<>();
        configuraTelaIniciol();
        updateData();

    }

    public TelaPessoaJTable(int numero, ControladorPessoa ctrlPessoa) {
        super("Tela de produtos");
        if (numero == 1) {
            this.ctrlPessoa = ctrlPessoa;
            btManager = new GerenciadorBotoes();
            tbManager = new GerenciadorTabela();
            conteudoTelaPessoa = new ArrayList<>();
            configuraTelaIniciol();
            updateData();
        } else if (numero == 2) {
            btManager = new GerenciadorBotoes();
            tbManager = new GerenciadorTabela();
            conteudoTelaPessoa = new ArrayList<>();
            configuraTelaRelarios();
            updateDataCarrinho();
        } else if (numero == 3) {
            btManager = new GerenciadorBotoes();
            tbManager = new GerenciadorTabela();
            conteudoTelaPessoa = new ArrayList<>();
            configuraTelaCarrinhoInicio();
            updateData();
        }
    }

    public TelaPessoaJTable(int numero) {
        super("Tela de produtos");
        if (numero == 1) {
            btManager = new GerenciadorBotoes();
            tbManager = new GerenciadorTabela();
            conteudoTelaPessoa = new ArrayList<>();
            configuraTelaIniciol();
            updateData();
        } else if (numero == 2) {
            btManager = new GerenciadorBotoes();
            tbManager = new GerenciadorTabela();
            conteudoTelaPessoa = new ArrayList<>();
            configuraTelaRelarios();
            updateDataCarrinho();
        } else if (numero == 3) {
            btManager = new GerenciadorBotoes();
            tbManager = new GerenciadorTabela();
            conteudoTelaPessoa = new ArrayList<>();
            configuraTelaCarrinhoInicio();
            updateData();
        }
    }

    private void configuraTelaIniciol() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());

        GridBagConstraints cons = new GridBagConstraints();

        //Configura botao limpeza
        btTipoLimpeza = new JButton("Limpeza");
        btTipoLimpeza.addActionListener(btManager);
        cons.gridx = 1;
        cons.gridy = 1;
        container.add(btTipoLimpeza, cons);

        //Configura botao frios
        btTipoFrios = new JButton("Frios");
        btTipoFrios.addActionListener(btManager);
        cons.gridx = 1;
        cons.gridy = 2;
        container.add(btTipoFrios, cons);

        //Configura botao bebidas
        btTipoBebidas = new JButton("Bebidas");
        btTipoBebidas.addActionListener(btManager);
        cons.gridx = 1;
        cons.gridy = 3;
        container.add(btTipoBebidas, cons);

        //Configura botao Frutas
        btTipoFrutas = new JButton("Frutas");
        btTipoFrutas.addActionListener(btManager);
        cons.gridx = 1;
        cons.gridy = 4;
        container.add(btTipoFrutas, cons);

        //Configura botao adicionar
        btAdiciona = new JButton("Adicionar");
        btAdiciona.addActionListener(btManager);
        cons.gridx = 2;
        cons.gridy = 6;
        container.add(btAdiciona, cons);

        //Configura button voltar
        btVolta = new JButton("Voltar");
        btVolta.addActionListener(btManager);
        cons.gridx = 3;
        cons.gridy = 6;
        container.add(btVolta, cons);

        //Configuração da JTable
        jtTabelaCorredor = new JTable();
        jtTabelaCorredor.setPreferredScrollableViewportSize(new Dimension(300, 200));
        jtTabelaCorredor.setFillsViewportHeight(true);
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridheight = 1;
        cons.gridwidth = 4;
        cons.gridx = 5;
        cons.gridy = 3;
        spBaseTabela = new JScrollPane(jtTabelaCorredor);
        jtTabelaCorredor.addMouseListener(tbManager);
        container.add(spBaseTabela, cons);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void configuraTelaCarrinhoInicio() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();


        //Configura button voltar
        btDeleta = new JButton("Deletar Produto");
        btDeleta.addActionListener(btManager);
        cons.gridx = 1;
        cons.gridy = 1;
        container.add(btDeleta, cons);

        //Configura button voltar
        btVolta = new JButton("Voltar");
        btVolta.addActionListener(btManager);
        cons.gridx = 1;
        cons.gridy = 2;
        container.add(btVolta, cons);

        //Configuração da jTable
        jtTabelaCorredor = new JTable();
        jtTabelaCorredor.setPreferredScrollableViewportSize(new Dimension(300, 200));
        jtTabelaCorredor.setFillsViewportHeight(true);
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridheight = 1;
        cons.gridwidth = 4;
        cons.gridx = 5;
        cons.gridy = 3;
        spBaseTabela = new JScrollPane(jtTabelaCorredor);
        jtTabelaCorredor.addMouseListener(tbManager);
        container.add(spBaseTabela, cons);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void configuraTelaRelarios() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();

        btVerRecibo = new JButton("Ver Recibo");
        btVerRecibo.addActionListener(btManager);
        cons.gridx = 1;
        cons.gridy = 1;
        container.add(btVerRecibo, cons);

        btVolta = new JButton("Voltar");
        btVolta.addActionListener(btManager);
        cons.gridx = 1;
        cons.gridy = 2;
        container.add(btVolta, cons);

        jtTabelaCorredor = new JTable();
        jtTabelaCorredor.setPreferredScrollableViewportSize(new Dimension(300, 200));
        jtTabelaCorredor.setFillsViewportHeight(true);
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridheight = 1;
        cons.gridwidth = 4;
        cons.gridx = 5;
        cons.gridy = 3;
        spBaseTabela = new JScrollPane(jtTabelaCorredor);
        jtTabelaCorredor.addMouseListener(tbManager);
        container.add(spBaseTabela, cons);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void updateData() {
        DefaultTableModel modelTdItens = new DefaultTableModel();
        modelTdItens.addColumn("Codigo");
        modelTdItens.addColumn("Nome");
        modelTdItens.addColumn("Preço");
        for (ConteudoTelaPessoa p : conteudoTelaPessoa) {
            modelTdItens.addRow(new Object[]{p.codigo, p.nome, p.preco});
        }
        jtTabelaCorredor.setModel(modelTdItens);
        this.repaint();
    }

    private void updateDataCarrinho() {
        DefaultTableModel modelTdItens = new DefaultTableModel();
        modelTdItens.addColumn("Codigo");
        modelTdItens.addColumn("Numero de itens");
        modelTdItens.addColumn("Valor total");
        for (ConteudoTelaPessoa p : conteudoTelaPessoa) {
            modelTdItens.addRow(new Object[]{p.codigo, p.numero, p.totalPreco});
        }
        jtTabelaCorredor.setModel(modelTdItens);
        this.repaint();
    }

    private class GerenciadorBotoes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btTipoBebidas)) {
                ControladorPessoa.getInstance().mostraTabela(4);
            } else if (e.getSource().equals(btTipoFrios)) {
                ControladorPessoa.getInstance().mostraTabela(2);


            } else if (e.getSource().equals(btTipoFrutas)) {
                ControladorPessoa.getInstance().mostraTabela(3);


            } else if (e.getSource().equals(btTipoLimpeza)) {
                ControladorPessoa.getInstance().mostraTabela(1);


            } else if (e.getSource().equals(btAdiciona)) {
                if (codigo != 0) {
                    JOptionPane.showMessageDialog(null, "Produto Adicionado com sucesso! "
                            + e.getActionCommand(), "Mercado ", 2);
                    ControladorPessoa.getInstance().addProdutoCarrinho(codigo);
                    codigo = 0;

                }
                codigo = 0;

            } else if (e.getSource().equals(btVolta)) {
                ControladorPessoa.getInstance().mostraTelaPessoa();

            } else if (e.getSource().equals(btDeleta)) {
                ControladorPessoa.getInstance().removeProduto(codigo);
                updateData();
            } else if (e.getSource().equals(btVerRecibo)) {
                if (codigo != 0) {
                    JOptionPane.showMessageDialog(null, "\n"
                            + ControladorPessoa.getInstance().getCliente().getThisRecibo(codigo).GerarRelatorioToString(), "\"Recibo numero \" + codigo ", 2);
                    codigo = 0;
                }
                codigo = 0;
            }
        }
    }

    private class GerenciadorTabela implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            codigo = Integer.valueOf(jtTabelaCorredor.getValueAt(jtTabelaCorredor.getSelectedRow(), 0).toString());
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

    public void setConteudoTelaPessoa(ArrayList<ConteudoTelaPessoa> lista) {
        this.conteudoTelaPessoa = lista;
    }

    public void mostraTela() {
        this.updateData();
        this.setVisible(true);
    }

    public void mostraTelaRecido() {
        this.updateDataCarrinho();
        this.setVisible(true);
    }

    public void escondeTela() {
        this.setVisible(false);
    }
}