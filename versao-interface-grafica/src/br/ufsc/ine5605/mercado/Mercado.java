package br.ufsc.ine5605.mercado;

import br.ufsc.ine5605.mercado.controller.ControladorPessoa;

public class Mercado {
    public static void main(String[] args) {        
        ControladorPessoa.getInstance().menuInicial();
    }    
}