package mercado;

import java.util.ArrayList;

public class Recibo {
    private float valorTotal;
    private String nome;
    private String cpf;
    private ArrayList<Produto> carrinho;
    private float valorImposto;
    private float imposto;


    public Recibo (Cliente cliente, float imposto){
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
	this.carrinho = cliente.getCarrinho();
	this.valorTotal= gerarValorCompraAtual(carrinho);
	this.imposto = imposto;
	this.valorImposto = gerarImposto(valorTotal, imposto);
	}

    public float gerarValorCompraAtual(ArrayList<Produto> carrinho){
        float total = 0;
	for (Produto produtoCarrinho: carrinho){
            total += produtoCarrinho.getPreco();
	}        
        return total * (1 + imposto/100);
	}
        
    public float gerarImposto(float totalCompra, float imposto){
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
}