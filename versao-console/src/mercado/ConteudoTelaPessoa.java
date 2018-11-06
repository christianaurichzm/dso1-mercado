package mercado;

public class ConteudoTelaPessoa {    
    public String nome;
    public String cpf;
    public String usuario;
    public String senha;
    public float saldo;

    public ConteudoTelaPessoa() {

    }

    public ConteudoTelaPessoa(String nome, String cpf, String usuario, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.usuario = usuario;
        this.senha = senha;
    }



}