package mercado;

public class ConteudoTelaLoja {
    public String nome;
    public int tipoProduto;
    public float preco;
    public int codigo;

    public ConteudoTelaLoja() {

    }   
                  
    public ConteudoTelaLoja(String nome, int tipoProduto, float preco, int codigo) {
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.preco = preco;
        this.codigo = codigo;
    }

}