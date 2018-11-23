package br.ufsc.ine5605.mercado.exceptions;

public class NaoPossuiReciboException extends Exception {
    
    public NaoPossuiReciboException() {
        this("Nao consta nenhum recibo no sistema.");
    }
       
    public NaoPossuiReciboException(String mensagem) {
        super(mensagem);
    }
    
}