package mercado;

public class Produto {
    private String nome;
    private TipoProduto tipoProduto;
    private float preco;
    private int codigo;

    public Produto() {
            
    }
        
    public Produto(String nome, TipoProduto tipoProduto, float preco, int codigo) {
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.preco = preco;
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }     

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }
}