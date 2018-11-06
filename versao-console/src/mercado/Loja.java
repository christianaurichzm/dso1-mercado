package mercado;

import java.util.ArrayList;

public class Loja {     
    private float valorCaixa;
    private float imposto;
    private ArrayList<Produto> produtos;
    private ArrayList<Recibo> recibos;

    public Loja(float valorCaixa, float imposto) {
        this.valorCaixa = valorCaixa;
        this.imposto = imposto;
        produtos = new ArrayList<>();
        recibos = new ArrayList<>();
    }

    public void adicionarProduto(ConteudoTelaLoja conteudoTela) {
        Produto produto = desempacota(conteudoTela);
        produtos.add(produto);
    }

    private Produto desempacota(ConteudoTelaLoja conteudoTela) {
        return new Produto(conteudoTela.nome, TipoProduto.DEFAULT.selecionaTipo(conteudoTela.tipoProduto), conteudoTela.preco, (produtos.size()+1));
    }

    public ConteudoTelaLoja empacota(Produto produto) {
        return new ConteudoTelaLoja(produto.getNome(), produto.getTipoProduto().tipo, produto.getPreco(), produto.getCodigo());
    }

    public Produto pegaProduto(int codigo) {
        for (Produto produto : produtos) {
            if (produto.getCodigo() == codigo) {
                return produto;
            }
        }
        return null;
    }

    public void deletarProduto(int codigo) {
        produtos.remove(pegaProduto(codigo));
    }

    public void alteraPrecoProduto(int codigo, float preco) {
        pegaProduto(codigo).setPreco(preco);
    }

    public void setImposto(float imposto){
        this.imposto = imposto;
    }

    public float getImposto() {
        return imposto;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public ArrayList<Recibo> getRecibos(){
        return recibos;
    }
    
    public void addRecibo(Recibo recibo){
        recibos.add(recibo);
    }
    
    public float aplicaImpostoNoProduto(float valorProduto) {
        return valorProduto * 1.1f;
    }
}