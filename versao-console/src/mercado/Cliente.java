package mercado;

import java.util.ArrayList;

public class Cliente extends Pessoa {
    private float saldo;    
    private ArrayList<Produto> carrinho;
    private ArrayList<Recibo> recibos;

    public Cliente(String nome, String cpf, String login, String senha) {
        super(nome, cpf, login, senha);
        carrinho = new ArrayList<>();
        recibos = new ArrayList<>();
    }       

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public ArrayList<Produto> getCarrinho() {
        return carrinho;
    }

    public void addCarrinho(Produto produto) {
        this.carrinho.add(produto);
    }

    public void addRecibo(Recibo recibo){
        recibos.add(recibo);
    }
    
    public Recibo getUltimoRecibo() {
        int index = recibos.size() - 1;
        if (index > -1) {
            return recibos.get(index);
        }
        return null;
    }

    public void novoCarrinho() {
        this.carrinho = new ArrayList<>();
    }

    public ArrayList<Recibo> pegarRecibos(){
        return this.recibos;
    }
}