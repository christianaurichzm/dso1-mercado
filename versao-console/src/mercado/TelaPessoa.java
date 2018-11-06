package mercado;

import java.util.ArrayList;
import java.util.Scanner;

public class TelaPessoa {
    private ControladorPessoa ctrlPessoa;
    private Scanner teclado;

    public TelaPessoa(ControladorPessoa ctrlPessoa) {
        teclado = new Scanner(System.in);
        this.ctrlPessoa = ctrlPessoa;
    }

    private String lerProximaLinha() {
        teclado.nextLine();
        String s = teclado.nextLine();
        return s;
    }

    public void mostraTelaInicial() {
        int opcao = 0;
        do {
            System.out.println("Bem-vindo ao Mercado.");
            System.out.println("1 - Fazer login");
            System.out.println("2 - Cadastrar cliente");
            opcao = teclado.nextInt();
            switch (opcao) {
                case 1:
                    mostraTelaLogin();
                    break;
                case 2:
                    mostraTelaCadastro();
                    break;
            }
        } while (true);
    }

    public void mostraTelaCompra() {
        int opcao = 0;
        do {
            System.out.println("Bem-vindo ao Mercado!");
            System.out.println("1 - Ver saldo");
            System.out.println("2 - Depositar valor na conta");
            System.out.println("3 - Listar produtos por tipo");
            System.out.println("4 - Deletar produto carrinho");
            System.out.println("5 - Ver carrinho");
            System.out.println("6 - Finalizar compra");
            System.out.println("7 - Relatorio de compras feitas");
            System.out.println("0 - Sair");
            opcao = teclado.nextInt();
            switch (opcao) {
                case 1:
                    encontraClienteGetSaldo();
                    break;
                case 2:
                    encontraClienteESetSaldo();
                    break;
                case 3:
                    pegaOpcaoListagem();
                    break;
                case 4:
                    removeProdutoCarrinho();
                    break;
                case 5:
                    mostraCarrinhoCliente();
                    break;
                case 6:
                    finalizaCompraCliente();
                    break;
                case 7:
                    mostrarRecibosCliente();
                    break;
            }
        } while (opcao > 0);
    }

    public void mostraTelaCadastro() {
        System.out.println("-------Cadastro de cliente-------");
        System.out.println("Digite seu nome: ");
        ConteudoTelaPessoa conteudoTelaPessoa = new ConteudoTelaPessoa();
        conteudoTelaPessoa.nome = lerProximaLinha();
        System.out.println("Digite seu cpf sem caracteres especiais: ");
        String cpf = teclado.nextLine();
        while (ctrlPessoa.validaCpfUsuasrio(cpf)){
            System.out.println("Cpf ja cadastrado");
            System.out.println("Informe um cpf diferente");
            cpf =  teclado.nextLine();
        }
        conteudoTelaPessoa.cpf = cpf;

        System.out.println("Digite um nome de usuário: ");
        String usuario = teclado.nextLine();
        while(ctrlPessoa.validaNomeUsuasrio(usuario)){
            System.out.println("Usuario ja cadastrado");
            System.out.println("Informe um noe diferente para usuario");
            usuario =  teclado.nextLine();
        }
        conteudoTelaPessoa.usuario = usuario;

        System.out.println("Digite uma senha: ");
        conteudoTelaPessoa.senha = teclado.nextLine();
        ctrlPessoa.incluiCliente(conteudoTelaPessoa);
        mostraTelaInicial();
    }

    public void mostraTelaLogin() {
        System.out.println("-------Login de acesso-------");
        System.out.println("Digite seu usuário: ");
        String usuario = lerProximaLinha();
        System.out.println("Digite sua senha: ");
        String senha = teclado.nextLine();
        ctrlPessoa.logar(usuario, senha);
    }

    public void pegaOpcaoListagem() {
        int opcao = 0;
        do {
            System.out.println("Escolha qual corredor" + "\n");            
            System.out.println(TipoProduto.LIMPEZA.getTipo() + " - " + TipoProduto.LIMPEZA);
            System.out.println(TipoProduto.FRIOS.getTipo() + " - " + TipoProduto.FRIOS);
            System.out.println(TipoProduto.FRUTAS.getTipo() + " - " + TipoProduto.FRUTAS);
            System.out.println(TipoProduto.BEBIDA.getTipo() + " - " + TipoProduto.BEBIDA);
            System.out.println("0 - Voltar." + "\n");
            System.out.println("Escolha sua opcao: ");
            opcao = teclado.nextInt();
            if (opcao != 0) {
                ctrlPessoa.pegaOpcaoListarProdutos(opcao);

            }
        } while (opcao > 0);

    }
    
    public void listaProdutos(ArrayList<ConteudoTelaLoja> listagemProdutos) {        
        System.out.println("\n" + "-------- LISTAGEM DE PRODUTOS --------");
        for (ConteudoTelaLoja conteudoTela : listagemProdutos) {
            System.out.println("Nome: " + conteudoTela.nome);
            System.out.println("Tipo: " + TipoProduto.DEFAULT.selecionaTipo(conteudoTela.tipoProduto));
            System.out.println("Preco " + conteudoTela.preco);
            System.out.println("Codigo: " + conteudoTela.codigo);
            System.out.println("---------------------");
        }
        System.out.println("---------------------" + "\n");      
    }

    public void encontraClienteGetSaldo() {
        System.out.println("\n" + "Confirme seu usuario: ");
        String usuario = lerProximaLinha();
        System.out.println("Confirme sua senha: ");
        String senha = teclado.nextLine();
        System.out.println("");
        ctrlPessoa.verSaldo(usuario, senha);
    }

    public void encontraClienteESetSaldo() {        
        System.out.println("\n" + "Confirme seu usuario: ");
        String usuario = lerProximaLinha();
        System.out.println("Confirme sua senha: ");
        String senha = teclado.nextLine();
        System.out.println("Digite o valor do deposito para sua conta: ");
        float valor = teclado.nextInt();
        ctrlPessoa.deposito(usuario, senha, valor);
        System.out.println("");
    }

    public void mostraSaldo(Cliente cliente) {        
        System.out.println("\n" + "Seu saldo é de: " + cliente.getSaldo() + "\n");     
    }
    
    public void mostraEscolhaDeProduto(int tipo) {
        int codigo = 0;
        do {
            System.out.println("");
            System.out.println("Escolha o codigo do produto para adicinar no carrinho, ");
            System.out.println("ou digite 0 para sair.");
            codigo = teclado.nextInt();

            if (codigo != 0) {
                if (ctrlPessoa.addProdutoCarrinho(codigo, tipo)) {
                    System.out.println("\n" + "Produto adicionado com sucesso!" + "\n");                    
                    ctrlPessoa.escolherProduto(tipo);      
                    break;
                } else {
                    System.out.println("Codigo incorreto!");
                }
            }
        } while (codigo != 0);

    }

    public void mostraCarrinhoCliente() {
        ctrlPessoa.getCarrinhoCliente();
    }

    public void finalizaCompraCliente() {
        try {
            ctrlPessoa.finalizaCarrinho();
        } catch (SaldoInsuficienteException | CarrinhoVazioException e) {
            System.out.println("\n" + e.getMessage() + "\n");
        }
    }

    public void mostrarRecibosCliente() {
        try {
            ctrlPessoa.pegarRecibos();
        } catch(NaoPossuiReciboException e) {
            System.out.println("\n" + e.getMessage() + "\n");
        }
    }

    public void removeProdutoCarrinho() {
        mostraCarrinhoCliente();
        System.out.println("");
        System.out.println("Escolha o codigo do produto para retirar do carrinho");
        System.out.println("ou digite 0 para sair");
        int codigo = teclado.nextInt();
        if (codigo != 0) {
            if (ctrlPessoa.remomveProduto(codigo)) {
                System.out.println("Produto retirado do carrinho" + "\n");               
            } else {
                System.out.println("Codigo invaldo");
            }
        }
    }
  
    public void mostraIndexCompra(int index){
        System.out.println("");
        System.out.println("");
        System.out.println("Recibo da " + index + "º  compra");
    }
}