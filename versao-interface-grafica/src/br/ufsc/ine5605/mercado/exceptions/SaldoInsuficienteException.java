package br.ufsc.ine5605.mercado.exceptions;

public class SaldoInsuficienteException extends RuntimeException {
    
    public SaldoInsuficienteException() {
        this("Saldo insuficiente!");
    }
       
    public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }
    
}