package mercado;

public enum TipoProduto {
    DEFAULT(0),
    LIMPEZA(1),
    FRIOS(2),
    FRUTAS(3),
    BEBIDA(4);    
    
    public final int tipo;
    
    TipoProduto(int tipoRecebido) {
        tipo = tipoRecebido;
    }
    
    public int getTipo() {
        return tipo;
    }
    
    public TipoProduto selecionaTipo(int tipo) {
        switch(tipo) {
            case 1 : return TipoProduto.LIMPEZA;
            case 2 : return TipoProduto.FRIOS;
            case 3 : return TipoProduto.FRUTAS;
            case 4 : return TipoProduto.BEBIDA;        
            default: return TipoProduto.DEFAULT;            
        }
    }
}