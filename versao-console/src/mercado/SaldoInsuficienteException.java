package mercado;

public class SaldoInsuficienteException extends RuntimeException {
    
    public SaldoInsuficienteException() {
        this("Saldo insuficiente!");
    }
       
    public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }
    
}