package br.ufsc.ine5605.mercado.view;

public class ConteudoTelaPessoa {    
    public String nome;
    public String cpf;
    public String usuario;
    public String senha;
    public float saldo;
    public int codigo;
    public int numero;
    public float preco;
    public float totalPreco;

    public ConteudoTelaPessoa() {

    }

    public ConteudoTelaPessoa(String nome, String cpf, String usuario, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.usuario = usuario;
        this.senha = senha;
    }

    public ConteudoTelaPessoa(int codigo, String nome, float preco) {
        this.codigo = codigo;
        this.nome =  nome;
        this.preco = preco;
    }
    public ConteudoTelaPessoa(int codigo, int numero, float totalPreco) {
        this.codigo = codigo;
        this.numero =  numero;
        this.totalPreco = totalPreco;
    }
}