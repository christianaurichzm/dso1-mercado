package mercado;

public class CarrinhoVazioException extends Exception {
    
    public CarrinhoVazioException() {
        this("Voce nao pode realizar a compra sem produtos no carrinho.");
    }
       
    public CarrinhoVazioException(String mensagem) {
        super(mensagem);
    }
    
}