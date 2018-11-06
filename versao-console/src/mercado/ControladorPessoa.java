package mercado;

import java.util.ArrayList;

public class ControladorPessoa {
    private TelaPessoa telaPessoa;
    private Cliente cliente;
    private ControladorLoja ctrlLoja;
    private ArrayList<Pessoa> usuarios;

    public ControladorPessoa() {
        this.telaPessoa = new TelaPessoa(this);
        ctrlLoja = new ControladorLoja();
        usuarios = new ArrayList<>();
    }

    public void iniciaUsusarios() {
        ConteudoTelaPessoa conteudoTelaPessoa = new ConteudoTelaPessoa("admin", "1234", "admin", "admin");
        ConteudoTelaPessoa conteudoTelaPessoa1 = new ConteudoTelaPessoa("1", "1", "1", "1");
        Pessoa gerente = desempacotaGerente(conteudoTelaPessoa);
        Cliente cliente = (Cliente) desempacota(conteudoTelaPessoa1);
        usuarios.add(cliente);
        usuarios.add(gerente);
    }

    public void menuInicial() {
        iniciaUsusarios();
        ctrlLoja.setControladorPessoa(this);
        ctrlLoja.inicializaProdutos();
        telaPessoa.mostraTelaInicial();
    }

    public void voltaMenuPrincipal() {
        telaPessoa.mostraTelaInicial();
    }

    public void pegaOpcaoListarProdutos(int tipo) {
        ctrlLoja.listaProdutos(ctrlLoja.listaProdutosPorCategoria(tipo));
        telaPessoa.mostraEscolhaDeProduto(tipo);
    }


    public void menuCliente() {
        telaPessoa.mostraTelaCompra();
    }


    public void incluiCliente(ConteudoTelaPessoa conteudoTelaPessoa) {
        Pessoa cliente = desempacota(conteudoTelaPessoa);
        usuarios.add(cliente);
    }

    public Cliente encontraCliente(String usuario, String senha) {
        for (Pessoa c : usuarios) {
            if (c.getUsuario().equals(usuario) && c.getSenha().equals(senha)) {
                return (Cliente) c;
            }
        }
        return null;
    }

    public void verSaldo(String usuario, String senha) {
        telaPessoa.mostraSaldo((encontraCliente(usuario, senha)));
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

    public void logar(String usuario, String senha) {
        for (Pessoa p : usuarios) {
            if (p.autentica(usuario, senha)) {
                if (p instanceof Gerente) {
                    ctrlLoja.abreLoja();
                } else {
                    this.cliente = (Cliente) p;
                    menuCliente();
                }
            }
        }
    }

    public void deposito(String usuario, String senha, float valor) {
        encontraCliente(usuario, senha).setSaldo(encontraCliente(usuario, senha).getSaldo() + valor);
    }

    public boolean addProdutoCarrinho(int codigo, int tipo) {
        for (Produto pro : ctrlLoja.getProdutos()) {
            if ((pro.getCodigo() == codigo) && (pro.getTipoProduto().equals(TipoProduto.DEFAULT.selecionaTipo(tipo)))) {
                cliente.addCarrinho(pro);
                return true;
            }
        }
        return false;
    }

    public void escolherProduto(int tipo) {
        ctrlLoja.listaProdutos(ctrlLoja.listaProdutosPorCategoria(tipo));
        telaPessoa.mostraEscolhaDeProduto(tipo);
    }

    public void getCarrinhoCliente() {
        ctrlLoja.listaProdutos(cliente.getCarrinho());
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void finalizaCarrinho() throws CarrinhoVazioException {
        if (!cliente.getCarrinho().isEmpty()) {
            float total = 0;
            for (Produto produto : cliente.getCarrinho()) {
                total += produto.getPreco();
            }
            if (total < cliente.getSaldo()) {
                Recibo recibo = ctrlLoja.gerarRecibo(cliente);
                float descontaTotalCompra = cliente.getSaldo() - (total * (1 + (ctrlLoja.getLoja().getImposto() / 100))); ;
                cliente.setSaldo(descontaTotalCompra);
                ctrlLoja.adicionaReciboLoja(recibo);
                cliente.addRecibo(recibo);
                cliente.novoCarrinho();
                ctrlLoja.mostrarRecibo(cliente.getUltimoRecibo());
            } else {
                throw new SaldoInsuficienteException("Saldo insuficiente!");
            }

        } else {
            throw new CarrinhoVazioException("Voce nao pode realizar a compra sem produtos no carrinho.");
        }
    }

    public void pegarRecibos() throws NaoPossuiReciboException {
        if (!cliente.pegarRecibos().isEmpty()) {
            int cont = 1;
            for (Recibo recibo : cliente.pegarRecibos()) {
                telaPessoa.mostraIndexCompra(cont);
                ctrlLoja.mostrarRecibo(recibo);
                cont++;
            }
        } else {
            throw new NaoPossuiReciboException("Nao consta nenhum recibo no sistema.");
        }

    }

    public void salvarDadosCliente() {
        for (Pessoa usuario : usuarios) {
            if (cliente.getCpf().equals(usuario.getCpf())) {
                usuario = cliente;                
            }
        }
    }

    public boolean remomveProduto(int codigo) {
        ctrlLoja.listaProdutos(cliente.getCarrinho());
        for (Produto produto : cliente.getCarrinho()) {
            if (codigo == produto.getCodigo()) {
                cliente.getCarrinho().remove(produto);
                return true;
            }
        }
        return false;
    }

    public boolean validaCpfUsuasrio(String nomeUsuario) {
        boolean existe = false;
        for (Pessoa p : usuarios) {
            if (p.getNome().equals(nomeUsuario)) {
                existe = true;
            }
        }
        return existe;
    }

    public boolean validaNomeUsuasrio(String cpf) {
        boolean existe = false;
        for (Pessoa p : usuarios) {
            if (p.getCpf().equals(cpf)) {
                existe = true;
            }
        }
        return existe;
    }
}