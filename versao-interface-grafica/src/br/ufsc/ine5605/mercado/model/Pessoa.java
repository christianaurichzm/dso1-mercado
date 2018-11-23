package br.ufsc.ine5605.mercado.model;

import br.ufsc.ine5605.mercado.interfaces.Autenticavel;

import java.io.Serializable;

public abstract class Pessoa implements Autenticavel, Serializable {
    private String nome;
    private String cpf;
    private String usuario;
    private String senha;

    public Pessoa(String nome, String cpf, String usuario, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.usuario = usuario;
        this.senha = senha;
    }

    @Override
    public boolean autentica(String usuario, String senha) {
        return this.usuario.equals(usuario) && this.senha.equals(senha);
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String login) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }    
}