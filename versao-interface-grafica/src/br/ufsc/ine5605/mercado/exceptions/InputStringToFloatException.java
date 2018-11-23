package br.ufsc.ine5605.mercado.exceptions;

public class InputStringToFloatException extends Exception {
    public InputStringToFloatException() {
        this("Insira um dado numérico");
    }


    public InputStringToFloatException(String mensagem) {
        super(mensagem);

    }

}