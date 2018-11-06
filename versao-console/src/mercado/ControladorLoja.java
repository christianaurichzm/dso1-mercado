package mercado;

import java.util.ArrayList;

public class ControladorLoja {
    private Loja loja;
    private TelaLoja telaLoja;
    private ArrayList<Produto> produtos;
    private ControladorPessoa ctrlPessoa;

    public ControladorLoja() {
        this.loja = new Loja(0, 10);
        this.telaLoja = new TelaLoja(this);
        this.produtos = loja.getProdutos();
    }
    
    public void setControladorPessoa(ControladorPessoa ctrlPessoa) {
        this.ctrlPessoa = ctrlPessoa;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }    

    public void adicionaProduto(ConteudoTelaLoja conteudoTela) {
        loja.adicionarProduto(conteudoTela);
    }

    public void voltarTelaLogin() {
        ctrlPessoa.menuInicial();
    }

    public void inicializaProdutos() {
        ConteudoTelaLoja carneBovina = new ConteudoTelaLoja("Carne Bovina", 2, 25, (produtos.size()+1));
        adicionaProduto(carneBovina);
        ConteudoTelaLoja leite = new ConteudoTelaLoja("Leite de Vaca", 4, 4, (produtos.size()+1));
        adicionaProduto(leite);
        ConteudoTelaLoja agua = new ConteudoTelaLoja("Agua mineral", 4, 2.5f, (produtos.size()+1));
        adicionaProduto(agua);
        ConteudoTelaLoja detergente = new ConteudoTelaLoja("Detergente", 1, 5, (produtos.size()+1));
        adicionaProduto(detergente);
        ConteudoTelaLoja banana = new ConteudoTelaLoja("Banana", 3, 3, (produtos.size()+1));
        adicionaProduto(banana);
        ConteudoTelaLoja uva = new ConteudoTelaLoja("Uva", 3, 4, (produtos.size()+1));
        adicionaProduto(uva);
        ConteudoTelaLoja frango = new ConteudoTelaLoja("Frango", 2, 19, (produtos.size()+1));
        adicionaProduto(frango);
        ConteudoTelaLoja sabonete = new ConteudoTelaLoja("Sabonete", 1, 0.9f, (produtos.size()+1));
        adicionaProduto(sabonete);
    }

    public void deletaProduto(int codigo) {
        loja.deletarProduto(codigo);
    }

    public void retornaMenuPrincipal() {
        telaLoja.mostraMenuLoja();
    }

    public void abreLoja() {
        telaLoja.mostraMenuLoja();
    }

    public void listaProdutos() {
        ArrayList<ConteudoTelaLoja> listagemProdutos = new ArrayList<>();

        for (Produto produto : produtos) {
            listagemProdutos.add(loja.empacota(produto));
        }

        telaLoja.mostraListaProdutos(listagemProdutos);
    }

    /**
     * lista todos os produtos recebendo um array de produtos
     * @param produtos
     */
    public void listaProdutos(ArrayList<Produto> produtos) {
        ArrayList<ConteudoTelaLoja> listagemProdutos = new ArrayList<>();

        for (Produto produto : produtos) {
            listagemProdutos.add(loja.empacota(produto));
        }

        telaLoja.mostraListaProdutos(listagemProdutos);
    }
       
    public ArrayList<Produto> listaProdutosPorCategoria(int tipo) {
        ArrayList<Produto> listagemProdutosPorCategoria = new ArrayList<>();
        for (Produto produto : getProdutos()) {
            if (TipoProduto.DEFAULT.selecionaTipo(tipo).equals(produto.getTipoProduto())) {
                listagemProdutosPorCategoria.add(produto);
            }
        }
        if (!listagemProdutosPorCategoria.isEmpty()) {
            return listagemProdutosPorCategoria;
        }
        return null;
    }

    public void alteraPreco(int codigo, float preco) {
        loja.alteraPrecoProduto(codigo, preco);
    }

    public void voltaMenuPrincipal() {
        ctrlPessoa.voltaMenuPrincipal();
    }

    /**
     * Gera um recibo contendo um Cliente e os dados referentes a sua compra
     * @param cliente
     * @return Recibo
      */
    public Recibo gerarRecibo(Cliente cliente) {
        Recibo recibo = new Recibo(cliente, loja.getImposto());
        return recibo;

    }

    public void mostrarRecibo(Recibo recibo) {
        ArrayList<ConteudoTelaLoja> produtosRecibo = new ArrayList<>();
        for (Produto produto : recibo.getCarrinho()) {
            produtosRecibo.add(loja.empacota(produto));
        }
        telaLoja.mostraRecibo(recibo.getNome(), recibo.getCpf(), produtosRecibo, recibo.getValorTotal(), recibo.getImposto(), recibo.getValorImposto());
    }    

    public void relatorioVendas() throws NaoPossuiReciboException {
        if (!loja.getRecibos().isEmpty()) {
            for (Recibo recibo : loja.getRecibos()) {
                mostrarRecibo(recibo);
            }
        } else {
            throw new NaoPossuiReciboException("Nao consta nenhum recibo no sistema.");
        }
    }

    public void adicionaReciboLoja(Recibo recibo) {
        loja.addRecibo(recibo);
    }

    public Loja getLoja(){
        return loja;
    }
  }