package br.ufsc.ine5605.mercado.model;

import br.ufsc.ine5605.mercado.model.Cliente;

import java.io.Serializable;
import java.util.ArrayList;

public class Recibo implements Serializable {
    private float valorTotal;
    private String nome;
    private String cpf;
    private ArrayList<Produto> carrinho;
    private float valorImposto;
    private float imposto;
    private int codigo;

    public Recibo(){}

    public Recibo(int codigo, Cliente cliente, float imposto) {
        this.codigo = codigo + 1;
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.carrinho = cliente.getCarrinho();
        this.valorTotal = gerarValorCompraAtual(carrinho);
        this.imposto = imposto;
        this.valorImposto = gerarImposto(valorTotal, imposto);
    }

    public float gerarValorCompraAtual(ArrayList<Produto> carrinho) {
        float total = 0;
        for (Produto produtoCarrinho : carrinho) {
            total += produtoCarrinho.getPreco();
        }
        return total * (1 + imposto / 100);
    }

    public float gerarImposto(float totalCompra, float imposto) {
        return totalCompra * imposto / 100;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ArrayList<Produto> getCarrinho() {
        return carrinho;
    }

    public float getImposto() {
        return imposto;
    }

    public void setCarrinho(ArrayList<Produto> carrinho) {
        this.carrinho = carrinho;
    }

    public float getValorImposto() {
        return valorImposto;
    }

    public int getCodigo() {
        return codigo;
    }

    public String GerarRelatorioToString() {

        String reciboString = "Cliente: " + nome + " ---- CPF do cliente: " + cpf + "\n";

        reciboString += "-------- LISTAGEM DE PRODUTOS --------" + "\n";
        for (Produto p : carrinho) {
            reciboString +="Codigo: " + p.getCodigo() + " - Nome: " + p.getNome() + " - Tipo: " + p.getTipoProduto() + " - Preco " + p.getPreco() + "\n";
        }
        System.out.println("\n\n");        
        reciboString +="Foi pago um total de " + imposto + "% de tributo " + "\n";
        reciboString +="Valor total da compra: " + (valorTotal + valorImposto);
        reciboString +="\n \n";

        return reciboString;
    }

}