import java.util.LinkedList;
import java.util.Scanner;

public class App {

    public static Usuarios cadastrarUsuario(Scanner teclado) {
        Usuarios novoUsuario = null;
        String novoNome;
        int opcao = -1;
        System.out.println("Biblioteca");
        System.out.println("#########################");
        System.out.println("INCLUIR Usuario");
        System.out.println("Digite o nome do Usuario: ");
        novoNome = teclado.nextLine();
        opcao = menuUsuario(teclado);
        switch (opcao) {
            case 1:
                novoUsuario = new Professor(novoNome);
                break;
            case 2:
                novoUsuario = new Alunos(novoNome);
                break;
            case 3:
                novoUsuario = new AlunoGraduacao(novoNome);
                break;
            case 4:
                novoUsuario = new AlunoPosGraduacao(novoNome);
                break;
        }

        return novoUsuario;
    }

    public static Livros cadastrarLivro(Scanner teclado) {
        Livros novoLivro = null;
        String novoAutor, novaEditora, novoTitulo;
        int opcao = -1;
        System.out.println("Biblioteca");
        System.out.println("#########################");
        System.out.println("INCLUIR Usuario");
        System.out.println("Digite o nome do Autor: ");
        novoAutor = teclado.nextLine();
        System.out.println("Digite o nome da Editora: ");
        novaEditora = teclado.nextLine();
        System.out.println("Digite o nome do Titulo: ");
        novoTitulo = teclado.nextLine();
        opcao = menuLivro(teclado);
        switch (opcao) {
            case 1:
                novoLivro = new LivrosFisicos(novoAutor, novaEditora, novoTitulo);
                break;
            case 2:
                novoLivro = new LivrosFisicosPrioritarios(novoAutor, novaEditora, novoTitulo);
                break;
            case 3:
                novoLivro = new LivrosDigitais(novoAutor, novaEditora, novoTitulo);
                break;
        }

        return novoLivro;
    }

    public static Emprestimo cadastrarEmprestimo(Scanner teclado, LinkedList<Usuarios> usuarios,
            LinkedList<Livros> livros) {
        int novaMatricula;
        String novoTitulo;
        Emprestimo novoEmprestimo;
        Usuarios usuario;
        Livros livro = null;
        int opcao = -1;
        System.out.println("Biblioteca");
        System.out.println("#########################");
        System.out.println("INCLUIR Usuario");
        System.out.println("Digite o numero da matricula: ");
        novaMatricula = teclado.nextInt();
        System.out.println("Digite o nome do Titulo: ");
        novoTitulo = teclado.nextLine();

        usuario = usuarios.get(novaMatricula);
        for (Livros itemLivro : livros) {
            if (itemLivro.getTitulo().equals(novoTitulo)) {
                livro = itemLivro;
                break;
            }
        }

        novoEmprestimo = new Emprestimo(usuario, livro);

        return novoEmprestimo;
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
        System.out.println("1 - Cadastrar Livro Fisico");
        System.out.println("2 - Cadastrar Livro Fisico Prioritario");
        System.out.println("3 - Cadastrar Digital");

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
        Usuarios novoUsuario;
        LinkedList<Usuarios> usuarios = new LinkedList<Usuarios>();
        Livros novoLivro;
        LinkedList<Livros> livros = new LinkedList<Livros>();
        Emprestimo novoEmprestimo;
        LinkedList<Emprestimo> emprestimos = new LinkedList<Emprestimo>();
        String escolhaRecorrencia;
        int recorrencia, qtdDeVezes;

        do {
            opcao = menu(teclado);
            switch (opcao) {
                case 1:
                    novoUsuario = cadastrarUsuario(teclado);
                    usuarios.add(novoUsuario);
                    break;
                case 2:
                    novoLivro = cadastrarLivro(teclado);
                    livros.add(novoLivro);
                    break;
                case 3:
                    novoEmprestimo = cadastrarEmprestimo(teclado, usuarios, livros);
                    emprestimos.add(novoEmprestimo);
                    break;
            }
            // pausa(teclado);
        } while (opcao != 0);
    }
}
