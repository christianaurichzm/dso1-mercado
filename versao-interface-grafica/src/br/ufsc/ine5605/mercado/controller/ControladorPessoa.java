package br.ufsc.ine5605.mercado.controller;

import br.ufsc.ine5605.mercado.model.Gerente;
import br.ufsc.ine5605.mercado.model.Recibo;
import br.ufsc.ine5605.mercado.model.Produto;
import br.ufsc.ine5605.mercado.model.Pessoa;
import br.ufsc.ine5605.mercado.view.TelaPessoaJFrame;
import br.ufsc.ine5605.mercado.view.ConteudoTelaPessoa;
import br.ufsc.ine5605.mercado.view.TelaCadastroJFrame;
import br.ufsc.ine5605.mercado.view.TelaLoginJFrame;
import br.ufsc.ine5605.mercado.view.TelaPessoaJTable;
import br.ufsc.ine5605.mercado.view.TelaDepositaJFrame;
import br.ufsc.ine5605.mercado.armazena.MapemanetoPessoa;
import br.ufsc.ine5605.mercado.model.*;
import java.util.ArrayList;

public class ControladorPessoa {
    private static ControladorPessoa singleCtrlPessoa;
    private Cliente cliente;
    private String cpf;
    
    private MapemanetoPessoa mapeadorUsuario;
    private TelaPessoaJTable telaTabela;
    private TelaPessoaJTable TelaTabelaCarrinho;
    private TelaPessoaJTable TelaTabelaRecibos;
    private TelaPessoaJFrame telaPessoaJframe;
    private TelaLoginJFrame telalogin;
    private TelaLoginJFrame telaValidaLogin;

    private TelaCadastroJFrame telaCadastro;
    private TelaDepositaJFrame teleDeposito;

/*  public ControladorPessoa() {
        this.telaPessoa = new TelaPessoa(this);
        ctrlLoja = new ControladorLoja();
        mapeadorUsuario = new MapemanetoPessoa();
        telaPessoaJframe = new TelaPessoaJframe(this);
        telaTabela = new TelaPessoaJtable(1,this);
        TelaTabelaCarrinho = new TelaPessoaJtable(3, this);
        TelaTabelaRecibos = new TelaPessoaJtable(2, this);
        telalogin = new TelaLoginFrame(this);
        telaValidaLogin = new TelaLoginFrame(1, this);
       telaCadastro = new TelaNovoCadastroFrame(this);
   }  */

    public static ControladorPessoa getInstance() {
        if (singleCtrlPessoa == null)
            singleCtrlPessoa = new ControladorPessoa();
        return singleCtrlPessoa;
    }
    
    private ControladorPessoa() {
        mapeadorUsuario = new MapemanetoPessoa();
        telaPessoaJframe = new TelaPessoaJFrame();
        telaTabela = new TelaPessoaJTable(1);
        TelaTabelaCarrinho = new TelaPessoaJTable(3);
        TelaTabelaRecibos = new TelaPessoaJTable(2);
        telalogin = new TelaLoginJFrame(1);
        telaValidaLogin = new TelaLoginJFrame(2);
        telaCadastro = new TelaCadastroJFrame();
        teleDeposito = new TelaDepositaJFrame();
    }

    public ArrayList<Pessoa> getPessoas() {
        return mapeadorUsuario.getList();
    }

    public Cliente getUsuario() {
        return (Cliente) getMapeadorUsuario().get(cpf);
    }

    public MapemanetoPessoa getMapeadorUsuario() {
        return mapeadorUsuario;
    }

    public void iniciaUsusarios() {
        ConteudoTelaPessoa conteudoTelaPessoa = new ConteudoTelaPessoa("admin", "1234", "admin", "admin");
        ConteudoTelaPessoa conteudoTelaPessoa1 = new ConteudoTelaPessoa("1", "1", "1", "1");
        Pessoa gerente = desempacotaGerente(conteudoTelaPessoa);
        Cliente cliente = (Cliente) desempacota(conteudoTelaPessoa1);
        if (mapeadorUsuario.get(cliente.getCpf()) == null) {
            mapeadorUsuario.put(cliente);
        }
        if (mapeadorUsuario.get(gerente.getCpf()) == null) {
            mapeadorUsuario.put(gerente);
        }
    }

    public void menuInicial() {
        iniciaUsusarios();
        ControladorLoja.getInstancia().inicializaProdutos();
        telalogin.mostraTela();
    }

    public void incluiCliente(ConteudoTelaPessoa conteudoTelaPessoa) {
        Pessoa cliente = desempacota(conteudoTelaPessoa);
        mapeadorUsuario.put(cliente);
    }

    public Cliente encontraCliente(String usuario, String senha) {
        for (Pessoa c : getPessoas()) {
            if (c.getUsuario().equals(usuario) && c.getSenha().equals(senha)) {
                return (Cliente) c;
            }
        }
        return null;
    }

    private Pessoa desempacota(ConteudoTelaPessoa conteudoTelaPessoa) {
        return new Cliente(conteudoTelaPessoa.nome, conteudoTelaPessoa.cpf, conteudoTelaPessoa.usuario, conteudoTelaPessoa.senha);
    }

    private Pessoa desempacotaGerente(ConteudoTelaPessoa conteudoTelaPessoa) {
        return new Gerente(conteudoTelaPessoa.nome, conteudoTelaPessoa.cpf, conteudoTelaPessoa.usuario, conteudoTelaPessoa.senha);
    }

    private ConteudoTelaPessoa empacota(Cliente cliente) {
        return new ConteudoTelaPessoa(cliente.getNome(), cliente.getCpf(), cliente.getUsuario(), cliente.getSenha());
    }

    public boolean logar(String usuario, String senha) {
        for (Pessoa p : getPessoas()) {
            if (p.autentica(usuario, senha)) {
                if (p instanceof Gerente) {
                    escondeLogin();
                    ControladorLoja.getInstancia().inicia();
                    return true;
                } else {
                    this.cpf = p.getCpf();
                    cliente = (Cliente) mapeadorUsuario.get(p.getCpf());
                    mostraTelaPessoa();
                    return true;
                }
            }
        }
        return false;
    }

    public void deposito(String usuario, String senha, float valor) {
        encontraCliente(usuario, senha).setSaldo(encontraCliente(usuario, senha).getSaldo() + valor);
        mapeadorUsuario.persist();
    }

    public boolean addProdutoCarrinho(int codigo) {
        for (Produto pro : ControladorLoja.getInstancia().getProdutos()) {
            if ((pro.getCodigo() == codigo)) {
                cliente.addCarrinho(pro);
                mapeadorUsuario.put(cliente);
                return true;
            }
        }
        return false;
    }

    public ArrayList<ConteudoTelaPessoa> getCarrinho() {
        ArrayList<ConteudoTelaPessoa> produtoCarrinho = new ArrayList<>();
        for (Produto p : cliente.getCarrinho()) {
            produtoCarrinho.add(new ConteudoTelaPessoa(p.getCodigo(), p.getNome(), p.getPreco()));
        }
        return produtoCarrinho;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void finalizaCarrinho() {
        Recibo recibo = ControladorLoja.getInstancia().gerarRecibo(getUsuario());
        float descontaTotalCompra = getUsuario().getSaldo() - (getCustoTotalCarrinho() * (1 + (ControladorLoja.getInstancia().getLoja().getImposto() / 100)));
        getUsuario().setSaldo(descontaTotalCompra);
        ControladorLoja.getInstancia().adicionaReciboLoja(recibo);
        getUsuario().addRecibo(recibo);
        mapeadorUsuario.put(cliente);
        getUsuario().novoCarrinho();
        mapeadorUsuario.put(cliente);
    }

    public float getCustoTotalCarrinho() {
        float total = 0;
        for (Produto produto : getUsuario().getCarrinho()) {
            total += produto.getPreco();
        }
        return total;
    }

    public void removeProduto(int codigo) {
        Produto prod = new Produto();
        for (Produto produto : cliente.getCarrinho()) {
            if (codigo == produto.getCodigo()) {
                prod = produto;
                break;
            }
        }
        cliente.getCarrinho().remove(prod);
        mapeadorUsuario.put(cliente);
        TelaTabelaCarrinho.setConteudoTelaPessoa(getCarrinho());

    }

    public boolean validaNomeUsuasrio(String nomeUsuario) {
        boolean existe = false;
        for (Pessoa p : getPessoas()) {
            if (p.getNome().equals(nomeUsuario)) {
                existe = true;
            }
        }
        return existe;
    }
    
    public boolean validaCpfUsuasrio(String cpf) {
        boolean existe = false;
        for (Pessoa p : getPessoas()) {
            if (p.getCpf().equals(cpf)) {
                existe = true;
            }
        }
        return existe;
    }

    public ArrayList<ConteudoTelaPessoa> getListaProduto(int tipo) {
        ArrayList<ConteudoTelaPessoa> lista = new ArrayList<>();

        for (Produto p : ControladorLoja.getInstancia().listaProdutosPorCategoria(tipo)) {
            lista.add(new ConteudoTelaPessoa(p.getCodigo(), p.getNome(), p.getPreco()));
        }
        
        return lista;
    }

    public ArrayList<ConteudoTelaPessoa> getListaRecibo() {
        ArrayList<ConteudoTelaPessoa> lista = new ArrayList<>();
        int index = 0;
        
        for (Recibo rec : getUsuario().pegarRecibos()) {
            lista.add(new ConteudoTelaPessoa(rec.getCodigo(), rec.getCarrinho().size(), rec.getValorTotal()));
            index++;
        }
        
        return lista;
    }

   public void mostraTelaLoja() {
        telaTabela.escondeTela();
        telaPessoaJframe.escondeTela();
        telalogin.escondeTela();
        telaCadastro.escondeTela();
        teleDeposito.escondeTela();
        TelaTabelaCarrinho.escondeTela();
        TelaTabelaRecibos.escondeTela();
        mapeadorUsuario.persist();       
        ControladorLoja.getInstancia().inicia();
   }
   
   public void escondeLogin() {       
        telaTabela.escondeTela();
        telaPessoaJframe.escondeTela();
        telalogin.escondeTela();
        telaCadastro.escondeTela();
        teleDeposito.escondeTela();
        TelaTabelaCarrinho.escondeTela();
        TelaTabelaRecibos.escondeTela();
        mapeadorUsuario.persist();           
   }
    
    public void mostraTabela(int tipo) {
        telaTabela.setConteudoTelaPessoa(getListaProduto(tipo));
        telaPessoaJframe.escondeTela();
        telalogin.escondeTela();
        telaValidaLogin.escondeTela();
        telaCadastro.escondeTela();
        teleDeposito.escondeTela();
        TelaTabelaCarrinho.escondeTela();
        TelaTabelaRecibos.escondeTela();
        telaTabela.setControladorPessoa(this);
        telaTabela.mostraTela();
    }

    public void mostraTabela() {
        telaPessoaJframe.escondeTela();
        telalogin.escondeTela();
        telaCadastro.escondeTela();
        telaValidaLogin.escondeTela();
        TelaTabelaCarrinho.escondeTela();
        teleDeposito.escondeTela();
        TelaTabelaRecibos.escondeTela();
        telaTabela.setControladorPessoa(this);
        mapeadorUsuario.persist();
        telaTabela.mostraTela();
    }

    public void mostraTelaCadastro() {
        telaCadastro.mostraTela();
        telaPessoaJframe.escondeTela();
        telaValidaLogin.escondeTela();
        telalogin.escondeTela();
        TelaTabelaCarrinho.escondeTela();
        teleDeposito.escondeTela();
        telaTabela.setControladorPessoa(this);
        mapeadorUsuario.persist();
        TelaTabelaRecibos.escondeTela();
        telaTabela.escondeTela();

    }

    public void mostraTelaLogin() {
        telaTabela.escondeTela();
        telaPessoaJframe.escondeTela();
        telaValidaLogin.escondeTela();
        telaCadastro.escondeTela();
        TelaTabelaCarrinho.escondeTela();
        teleDeposito.escondeTela();
        TelaTabelaRecibos.escondeTela();
        mapeadorUsuario.persist();
        telalogin.mostraTela();

    }

    public void mostraTelaPessoa() {
        telaTabela.escondeTela();
        telalogin.escondeTela();
        telaValidaLogin.escondeTela();
        telaCadastro.escondeTela();
        teleDeposito.escondeTela();
        TelaTabelaCarrinho.escondeTela();
        TelaTabelaRecibos.escondeTela();
        mapeadorUsuario.persist();
        telaPessoaJframe.mostraTela();

    }

    public void mostraTelaDeposito() {
        telaTabela.escondeTela();
        telaPessoaJframe.escondeTela();
        telalogin.escondeTela();
        telaCadastro.escondeTela();
        TelaTabelaCarrinho.escondeTela();
        telaValidaLogin.escondeTela();
        TelaTabelaRecibos.escondeTela();
        mapeadorUsuario.persist();
        teleDeposito.mostraTela();
    }

    public void mostraTelaValidaLogin() {
        telaTabela.escondeTela();
        telaPessoaJframe.escondeTela();
        telalogin.escondeTela();
        telaCadastro.escondeTela();
        teleDeposito.escondeTela();
        TelaTabelaCarrinho.escondeTela();
        TelaTabelaRecibos.escondeTela();
        mapeadorUsuario.persist();
        telaValidaLogin.mostraTela();
    }

    public void mostraTelaCarrinhoTabela() {
        telaTabela.escondeTela();
        telaPessoaJframe.escondeTela();
        telalogin.escondeTela();
        telaCadastro.escondeTela();
        teleDeposito.escondeTela();
        telaValidaLogin.escondeTela();
        TelaTabelaCarrinho.setConteudoTelaPessoa(getCarrinho());
        TelaTabelaRecibos.escondeTela();
        mapeadorUsuario.persist();
        TelaTabelaCarrinho.mostraTela();
    }

    public void mostraTabelaRecibos() {
        telaPessoaJframe.escondeTela();
        telalogin.escondeTela();
        telaCadastro.escondeTela();
        telaValidaLogin.escondeTela();
        TelaTabelaCarrinho.escondeTela();
        teleDeposito.escondeTela();
        telaTabela.escondeTela();
        TelaTabelaRecibos.setConteudoTelaPessoa(getListaRecibo());
        mapeadorUsuario.persist();
        TelaTabelaRecibos.mostraTelaRecido();
    }

    public void CadastraCliente(String nome, String cpf, String usuario, String senha) {
        this.incluiCliente(new ConteudoTelaPessoa(nome, cpf, usuario, senha));
    }

    public float converteStringToFloat(String numero) throws Exception {
            return Float.valueOf(numero.replace(",", "."));
    }
}