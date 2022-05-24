import java.util.LinkedList;
import java.util.Scanner;

public class App {

    public static Usuarios cadastrarUsuario(Scanner teclado) {
        Usuarios novoUsuario = null;
        String novoNome, diaDaSemana;
        Data data;
        // limparTela();
        System.out.println("Biblioteca");
        System.out.println("#########################");
        System.out.println("INCLUIR Usuario");
        System.out.println("Digite o nome do Usuario: ");
        novoNome = teclado.nextLine();

        // data = criarData(teclado);

        novoCompromisso = new Usuarios(novoNome);
        return novoCompromisso;
    }

    public static Data criarData(Scanner teclado) {
        int dia, mes, ano;
        System.out.println("Digite número do dia: ");
        dia = Integer.parseInt(teclado.nextLine());
        System.out.println("Digite número do mes: ");
        mes = Integer.parseInt(teclado.nextLine());
        System.out.println("Digite número do ano: ");
        ano = Integer.parseInt(teclado.nextLine());

        Data dataRetorno = new Data(dia, mes, ano);
        return dataRetorno;
    }

    public static int menu(Scanner teclado) {
        System.out.println("Biblioteca");
        System.out.println("#########################");
        System.out.println("1 - Cadastrar Usuário");
        System.out.println("2 - Cadastrar Livro");
        System.out.println("3 - Realizar empréstimo");
        System.out.println("4 - Visualizar histórico de empréstimo");
        System.out.println("0 - Sair");

        int opcao = teclado.nextInt();
        teclado.nextLine();
        return opcao;
    }

    public static int menuUsuario(Scanner teclado) {
        System.out.println("Biblioteca");
        System.out.println("#########################");
        System.out.println("1 - Cadastrar Professor");
        System.out.println("2 - Cadastrar Aluno");
        System.out.println("3 - Cadastrar Aluno de Graduação");
        System.out.println("4 - Cadastrar Aluno de Pos Graduação");

        int opcao = teclado.nextInt();
        teclado.nextLine();
        return opcao;
    }

    public static int menuLivro(Scanner teclado) {
        System.out.println("Biblioteca");
        System.out.println("#########################");
        System.out.println("1 - Cadastrar Professor");
        System.out.println("2 - Cadastrar Aluno");
        System.out.println("3 - Cadastrar AlunoGraduação");
        System.out.println("4 - Cadastrar AlunoGraduação");
        System.out.println("0 - Sair");

        int opcao = teclado.nextInt();
        teclado.nextLine();
        return opcao;
    }

    public static void main(String[] args) throws Exception {
        int opcao = -1;
        Data dataInicial = null;
        Data dataFinal = null;
        Scanner teclado = new Scanner(System.in);
        Livros livro = null;
        LinkedList<Usuarios> novoUsuario = null;
        String escolhaRecorrencia;
        int recorrencia, qtdDeVezes;

        do {
            opcao = menu(teclado);
            switch (opcao) {
                case 1:
                    novoUsuario = cadastrarUsuario(teclado);
                    novaAgenda = new Agenda(compromisso);
                    System.out.println("Deseja que esse compromisso se repita? Digite: sim ou nao ");
                    escolhaRecorrencia = teclado.nextLine();
                    if (escolhaRecorrencia.equalsIgnoreCase("sim")) {
                        System.out.println("Deseja que esse compromisso repita a cada quantos dias? ");
                        recorrencia = Integer.parseInt(teclado.nextLine());

                        System.out.println("Quantas vezes deseja que aconteca essa recorrencia? ");
                        qtdDeVezes = Integer.parseInt(teclado.nextLine());

                        for (int i = 0; i < qtdDeVezes; i++) {
                            compromisso = new Compromisso(compromisso.descricao, " ",
                                    compromisso.dataCompromisso.acrescentaDias(recorrencia));
                            novaAgenda.cadastrarCompromisso(compromisso);
                        }
                    }
                    break;
                case 2:
                    // limparTela();
                    compromisso = cadastrarCompromisso(teclado);
                    novaAgenda.cadastrarCompromisso(compromisso);
                    System.out.println("Deseja que esse compromisso se repita? Digite: sim ou nao ");
                    escolhaRecorrencia = teclado.nextLine();
                    if (escolhaRecorrencia.equalsIgnoreCase("sim")) {
                        System.out.println("Deseja que esse compromisso repita a cada quantos dias? ");
                        recorrencia = Integer.parseInt(teclado.nextLine());

                        System.out.println("Quantas vezes deseja que aconteca essa recorrencia? ");
                        qtdDeVezes = Integer.parseInt(teclado.nextLine());

                        for (int i = 0; i < qtdDeVezes; i++) {
                            compromisso = new Compromisso(compromisso.descricao, " ",
                                    compromisso.dataCompromisso.acrescentaDias(recorrencia));
                            novaAgenda.cadastrarCompromisso(compromisso);
                        }
                    }
                    break;
                case 3:
                    // limparTela();
                    System.out.println("Agenda De Compromissos");
                    System.out.println("#########################");
                    System.out.println("Digite a data incial: !!!!");
                    System.out.println(" ");
                    dataInicial = criarData(teclado);
                    System.out.println("#########################");
                    System.out.println("Digite a data final: !!!!");
                    System.out.println(" ");
                    dataFinal = criarData(teclado);
                    System.out.println(novaAgenda.relatorio(dataInicial, dataFinal));
                    break;
            }
            // pausa(teclado);
        } while (opcao != 0);
    }
}
