package br.ufsc.ine5605.mercado.controller;

import br.ufsc.ine5605.mercado.model.Recibo;
import br.ufsc.ine5605.mercado.model.Loja;
import br.ufsc.ine5605.mercado.model.Produto;
import br.ufsc.ine5605.mercado.model.TipoProduto;
import br.ufsc.ine5605.mercado.view.TelaAlteraPrecoJFrame;
import br.ufsc.ine5605.mercado.view.ConteudoTelaLoja;
import br.ufsc.ine5605.mercado.view.TelaLojaJFrame;
import br.ufsc.ine5605.mercado.view.TelaRelatorioLojaJFrame;
import br.ufsc.ine5605.mercado.view.TelaListaProdutoJTable;
import br.ufsc.ine5605.mercado.armazena.MapeamentoLoja;
import br.ufsc.ine5605.mercado.model.*;
import br.ufsc.ine5605.mercado.view.*;

import java.util.ArrayList;

public class ControladorLoja {
    private MapeamentoLoja mapaLoja;
    private TelaLojaJFrame tela;
    private TelaAddProdutoJFrame telaProduto;
    private TelaAlteraPrecoJFrame telaPreco;
    private TelaListaProdutoJTable telaLista;
    private TelaRelatorioLojaJFrame telaRelatorios;    
    private ArrayList<Produto> produtos;
    private TelaListaProdutoJTable tabelaProdutos;
    private static ControladorLoja instanciaLoja = new ControladorLoja();

    public ControladorLoja() {
        this.mapaLoja = new MapeamentoLoja();
        if (mapaLoja.get(1) == null) {
            mapaLoja.put(new Loja(0, 10));
        }
        this.tela = new TelaLojaJFrame();
        this.produtos = getLoja().getProdutos();
        this.telaProduto = new TelaAddProdutoJFrame();
        this.telaPreco = new TelaAlteraPrecoJFrame();
        this.telaLista = new TelaListaProdutoJTable();
        this.tabelaProdutos = new TelaListaProdutoJTable();
        this.telaRelatorios = new TelaRelatorioLojaJFrame();
    }
    
    public static ControladorLoja getInstancia() {        
        if (instanciaLoja == null) {
            instanciaLoja = new ControladorLoja();
        }        
        return instanciaLoja;
    }
     
    public void inicia() {
         tela.mostraTela();
    }
     
     
    public Loja getLoja(){
        return mapaLoja.get(1);
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }    

    public void adicionaProduto(ConteudoTelaLoja conteudoTela) {
        boolean jaExiste = false;
        for(Produto p : getProdutos()){
            if(p.getNome().equals(conteudoTela.nome) && p.getPreco() == conteudoTela.preco && p.getTipoProduto() == TipoProduto.DEFAULT.selecionaTipo(conteudoTela.tipoProduto)){
                jaExiste = true;
                break;
            }
        }
        if(jaExiste == false) {
            getLoja().adicionarProduto(conteudoTela);
            mapaLoja.put(getLoja());
        }
    }
    
    public float alteraPrecoProduto(int codigo) {
        return getProdutos().get(codigo - 1).getPreco();
    }
    
    public void adicionaProduto(String nome, int tipoProduto, float preco) {
        boolean jaExiste = false;
        ConteudoTelaLoja produto = new ConteudoTelaLoja(nome, tipoProduto, preco, getLoja().getProdutos().size()+1);
        for(Produto p : getProdutos()){
            if(p.getNome().equals(produto.nome) ){
                jaExiste = true;
                break;
            }
        }
        if(jaExiste == false) {
            getLoja().adicionarProduto(produto);
            mapaLoja.put(getLoja());
        }
    }
    
    public void inicializaProdutos() {
        if (getProdutos().size() < 1) {
        ConteudoTelaLoja carneBovina = new ConteudoTelaLoja("Carne Bovina", 2, 25, getLoja().getIndex());
        adicionaProduto(carneBovina);
        ConteudoTelaLoja leite = new ConteudoTelaLoja("Leite de Vaca", 4, 4, (produtos.size()+1));
        adicionaProduto(leite);
        ConteudoTelaLoja agua = new ConteudoTelaLoja("Agua mineral", 4, 2.5f, (produtos.size()+1));
        adicionaProduto(agua);
        ConteudoTelaLoja detergente = new ConteudoTelaLoja("Detergente", 1, 5, (produtos.size()+1));
        adicionaProduto(detergente);
        ConteudoTelaLoja banana = new ConteudoTelaLoja("Banana", 3, 3, (produtos.size()+1));
        adicionaProduto(banana);
        ConteudoTelaLoja uva = new ConteudoTelaLoja("Uva", 3, 4, (produtos.size()+1));
        adicionaProduto(uva);
        ConteudoTelaLoja frango = new ConteudoTelaLoja("Frango", 2, 19, (produtos.size()+1));
        adicionaProduto(frango);
        } else {
            System.out.println("Produtos nao inicializados");
        }
    }

    public void alteraPreco(int codigo, float preco) {
        getLoja().alteraPrecoProduto(codigo, preco);
        mapaLoja.put(getLoja());
    }
    
    public void deletaProduto(int codigo) {
        getLoja().deletarProduto(codigo);
        mapaLoja.put(getLoja());
    }

     public ArrayList<ConteudoTelaLoja> getListaProdutos() {
        ArrayList<ConteudoTelaLoja> listagemProdutos = new ArrayList<>();
        for (Produto p : getProdutos()) {
            listagemProdutos.add(new ConteudoTelaLoja(p.getNome(), p.getTipoProduto().tipo, p.getPreco(), p.getCodigo()));
        }
        return listagemProdutos;
    }
   
    public ArrayList<Produto> listaProdutosPorCategoria(int tipo) {
        ArrayList<Produto> listagemProdutosPorCategoria = new ArrayList<>();
        for (Produto produto : getProdutos()) {
            if (TipoProduto.DEFAULT.selecionaTipo(tipo).equals(produto.getTipoProduto())) {
                listagemProdutosPorCategoria.add(produto);
            }
        }
        if (!listagemProdutosPorCategoria.isEmpty()) {
            return listagemProdutosPorCategoria;
        }
        return null;
    }

    public Recibo gerarRecibo(Cliente cliente) {
        Recibo recibo = new Recibo(getLoja().getRecibos().size(), cliente, getLoja().getImposto());
        return recibo;
    }
  
    public void adicionaReciboLoja(Recibo recibo) {
        getLoja().addRecibo(recibo);
        mapaLoja.put(getLoja());
    }
    
    public void mostraTelaAddProduto() {
        tela.setVisible(false);
        telaProduto.setVisible(true);
    }
    
    public void mostraTelaAlteraPreco(int codigo) {
        tela.setVisible(false);    
        telaLista.setVisible(false);
        telaPreco.setaCodigo(codigo);
        telaPreco.setVisible(true);
    }
    
    public void mostraTelaLista() {
        telaLista.setConteudoTelaLoja(getListaProdutos());
        tela.setVisible(false);
        telaLista.mostraTela();        
    }
    
    public void mostraTelaRelatorios() {        
        tela.setVisible(false);
        telaRelatorios.mostraTela();
    }
    
    public void voltarTelaLoginSwing() {        
        tela.setVisible(false);
        ControladorPessoa.getInstance().mostraTelaLogin();
    }
    
    public ArrayList<ConteudoTelaLoja> getListaDeRecibos() {
        ArrayList<ConteudoTelaLoja> listaRecibos = new ArrayList<>();
        for (Recibo r : getLoja().getRecibos()) {
            listaRecibos.add(new ConteudoTelaLoja(r.getCodigo(), r.getNome(), r.getCarrinho().size(), r.getValorTotal()));
        }
        return listaRecibos;
    }
    
    public void voltaTelaLoja() {
        telaRelatorios.setVisible(false);
        telaProduto.setVisible(false);
        telaLista.setVisible(false);
        tela.setVisible(true);        
    }    
}