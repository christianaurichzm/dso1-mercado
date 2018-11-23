package br.ufsc.ine5605.mercado.exceptions;

public class CarrinhoVazioException extends Exception {
    
    public CarrinhoVazioException() {
        this("Voce nao pode finalizar a compra sem produtos no carrinho!");
    }
       
    public CarrinhoVazioException(String mensagem) {
        super(mensagem);
    }
    
}