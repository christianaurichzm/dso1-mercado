package br.ufsc.ine5605.mercado.view;

public class ConteudoTelaLoja {
    public String nome;
    public int tipoProduto;
    public float preco;
    public int codigo;
    public String nomeCliente;
    public int quantidade;
    public float totalPreco;

    public ConteudoTelaLoja() {

    }   
                  
    public ConteudoTelaLoja(String nome, int tipoProduto, float preco, int codigo) {
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.preco = preco;
        this.codigo = codigo;
    }
    
    public ConteudoTelaLoja(int codigo, String nomeCliente, int quantidade, float totalPreco) {
        this.codigo = codigo;
        this.nomeCliente = nomeCliente;
        this.quantidade = quantidade;
        this.totalPreco = totalPreco;
    }
}