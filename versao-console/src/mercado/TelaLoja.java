package mercado;

import java.util.ArrayList;
import java.util.Scanner;

public class TelaLoja {
    private ControladorLoja ctrlLoja;
    private Scanner teclado;

    public TelaLoja(ControladorLoja ctrlLoja) {
        this.ctrlLoja = ctrlLoja;
        this.teclado = new Scanner(System.in);
    }

    private String lerProximaLinha() {
        teclado.nextLine();
        String s = teclado.nextLine();
        return s;
    }

    private int leValorInteiro() {
        int inteiro = teclado.nextInt();
        teclado.nextLine();
        return inteiro;
    }

    public void mostraMenuLoja() {
        int opcao;
        do {
            System.out.println("-----Menu Principal da Loja-----");
            System.out.println("---Escolha uma opcao---");
            System.out.println("1 - Adicionar Produto");
            System.out.println("2 - Deletar produto");
            System.out.println("3 - Alterar preço produto");
            System.out.println("4 - Listar produtos");
            System.out.println("5 - Gerar relatório do dia");
            System.out.println("6 - Fechar a loja");
            System.out.println("0 - Voltar para tela de login");

            opcao = teclado.nextInt();

            switch (opcao) {
                case 1:
                    mostraAddProduto();
                    break;
                case 2:
                    mostraDeletaProduto();
                    break;
                case 3:
                    mostraAlteraPreco();
                    break;
                case 4:
                    ctrlLoja.listaProdutos();
                    break;
                case 5:
                    try {
                        ctrlLoja.relatorioVendas();
                    } catch (NaoPossuiReciboException e) {
                        System.out.println("\n" + e.getMessage() + "\n");
                    }
                    break;                
                case 6:
                    System.exit(0);
                case 0:
                    ctrlLoja.voltaMenuPrincipal();
                    break;

            }
        } while(opcao >0);
    }

    public void mostraAddProduto() {
        System.out.println("-----Tela de Adicionar Produto-----");
        System.out.println("Nome do produto:");
        ConteudoTelaLoja conteudoTela = new ConteudoTelaLoja();
        conteudoTela.nome = lerProximaLinha();
        System.out.println("Tipo do produto:");
        System.out.println("");
        System.out.println("1 - LIMPEZA.");
        System.out.println("2 - FRIOS.");
        System.out.println("3 - FRUTAS.");
        System.out.println("4 - BEBIDAS.");
        System.out.println("");
        conteudoTela.tipoProduto = leValorInteiro();
        System.out.println("Preco do produto:");
        conteudoTela.preco = teclado.nextFloat();

        ctrlLoja.adicionaProduto(conteudoTela);
        System.out.println("Produto adicionado com sucesso!");
        mostraMenuLoja();

    }

    public void mostraAlteraPreco() {
        System.out.println("----Altera Preco----");
        System.out.println("Digite o codigo do produto: ");
        int codigo = teclado.nextInt();
        System.out.println("Digite novo valor do produto: ");
        float preco = teclado.nextFloat();
        ctrlLoja.alteraPreco(codigo, preco);
        System.out.println("Preco alterado com sucesso!");
        mostraMenuLoja();
    }

    public void mostraListaProdutos(ArrayList<ConteudoTelaLoja> listagemProdutos) {
        System.out.println("-------- LISTAGEM DE PRODUTOS --------");
        for (ConteudoTelaLoja conteudoTela : listagemProdutos) {
            System.out.print("Codigo: " + conteudoTela.codigo);
            System.out.print(" - Nome: " + conteudoTela.nome);
            System.out.print(" - Tipo: " + TipoProduto.DEFAULT.selecionaTipo(conteudoTela.tipoProduto));
            System.out.println(" - Preco " + conteudoTela.preco);
            System.out.println("---------------------");
        }
        System.out.println("---------------------");
    }

    public void mostraDeletaProduto() {
        ctrlLoja.listaProdutos();
        System.out.println("Digite o codigo do produto desejado: ");
        int codigo = teclado.nextInt();
        ctrlLoja.deletaProduto(codigo);
        System.out.println("Produto deletado com sucesso!");
        mostraMenuLoja();
    }

    public void mostraRecibo(String nome, String cpf, ArrayList<ConteudoTelaLoja> produtos, float total, float imposto, float valorImposto) {
        System.out.println("Cliente: " + nome + " ---- CPF do cliente: " + cpf + "\n");
        mostraListaProdutos(produtos);
        System.out.printf("\n\n");        
        System.out.println("Foi pago um total de " + imposto + "% de tributo ");
        System.out.println("Total pago em tributos: " + valorImposto);
        System.out.println("Valor total da compra: " + (total + valorImposto));
        System.out.printf("\n\n");
    }    
}