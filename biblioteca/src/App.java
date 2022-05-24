import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class App {

// Método para introduzir uma data.
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


// Menu Principal
public static int menu(Scanner teclado) {
    System.out.println("------------------------------------------------------");
    System.out.println("Biblioteca");
    System.out.println("1 - Cadastrar Usuário");
    System.out.println("2 - Cadastrar Livro");
    System.out.println("3 - Realizar empréstimo");
    System.out.println("4 - Visualizar histórico de empréstimo");
    System.out.println("0 - Sair");
    System.out.println("------------------------------------------------------");

    int opcao = teclado.nextInt();
    teclado.nextLine();
    return opcao;
}


// Opção 1 - Cadastro de usuários
// Método para indicar o tipo de usuário que será cadastrado
public static int menuUsuario(Scanner teclado) {
    System.out.println("------------------------------------------------------");
    System.out.println("1 - Cadastrar Professor");
    System.out.println("2 - Cadastrar Aluno");
    System.out.println("3 - Cadastrar Aluno de Graduação");
    System.out.println("4 - Cadastrar Aluno de Pos Graduação");
    System.out.println("------------------------------------------------------");
    int opcao = teclado.nextInt();
    teclado.nextLine();
    return opcao;
}

// Método para cadastro de usuário
    public static Usuarios cadastrarUsuario(Scanner teclado) {
        Usuarios novoUsuario = null;
        String novoNome;
        int opcao = -1;
        System.out.println("------------------------------------------------------");
        System.out.println("Incluir um usuário");
        System.out.println("Digite o nome do usuário: ");
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


// Opção 2 - Cadastro de livros
// Método para indicar o tipo de livro que será cadastrado
    public static int menuLivro(Scanner teclado) {
        System.out.println("------------------------------------------------------");
        System.out.println("1 - Cadastrar Livro Físico");
        System.out.println("2 - Cadastrar Livro Físico Prioritário");
        System.out.println("3 - Cadastrar Livro Digital");
        System.out.println("------------------------------------------------------");
        int opcao = teclado.nextInt();
        teclado.nextLine();
        return opcao;
    }

//Método para cadastro de livro
    public static Livros cadastrarLivro(Scanner teclado) {
        Livros novoLivro = null;
        String novoAutor, novaEditora, novoTitulo;
        int opcao = -1;
        System.out.println("------------------------------------------------------");
        System.out.println("Digite o nome do autor: ");
        novoAutor = teclado.nextLine();
        System.out.println("Digite a editora: ");
        novaEditora = teclado.nextLine();
        System.out.println("Digite o título do livro: ");
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


// Opção 3 - Método para realização de empréstimos
    public static Emprestimo cadastrarEmprestimo(Scanner teclado, LinkedList<Usuarios> usuarios,
            LinkedList<Livros> livros) {
        int novaMatricula;
        String novoTitulo;
        Emprestimo novoEmprestimo;
        Usuarios usuario;
        Livros livro = null;
        System.out.println("------------------------------------------------------");
        System.out.println("Digite o numero da matricula: ");
        novaMatricula = teclado.nextInt();
        System.out.println("Digite o título do livro: ");
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

// Métodos de arquivos
public static void SalvarDaddosNoArquivo(LinkedList<Usuarios> usuarios, LinkedList<Livros> livros, LinkedList<Emprestimo> emprestimos ) throws IOException
{
    FileWriter arq = new FileWriter(caminhoPastaArquivo +"\\usuarios.txt");
    PrintWriter gravarArq = new PrintWriter(arq);

    for (Usuarios usuario : usuarios) {
        var nomeClass = usuario.getClass().getSimpleName();
        gravarArq.println(nomeClass + "|" + usuario.ToString());
    }

    arq.close();

    arq = new FileWriter(caminhoPastaArquivo +"\\livros.txt");
    gravarArq = new PrintWriter(arq);

    for (Livros livro : livros) {
        var nomeClass = livro.getClass().getSimpleName();
        gravarArq.println(nomeClass + "|" + livro.ToString());
    }

    arq.close();

    arq = new FileWriter(caminhoPastaArquivo +"\\emprestimos.txt");
    gravarArq = new PrintWriter(arq);

    for (Emprestimo emprestimo : emprestimos) {
        var nomeClass = emprestimo.getClass().getSimpleName();
        gravarArq.println(nomeClass + "|" + emprestimo.ToString());
    }

    arq.close();
}
public static  String caminhoPastaArquivo = "C:\\Users\\maria.campos\\Documents";
public static void RecuperarDaddosNoArquivo(LinkedList<Usuarios> usuarios, LinkedList<Livros> livros, LinkedList<Emprestimo> emprestimos ) throws FileNotFoundException
{
    Usuarios novoUsuario = null;
    Scanner scanner = new Scanner(new FileReader( caminhoPastaArquivo +"\\usuarios.txt"));
    while (scanner.hasNextLine()) {
        String linha = scanner.nextLine();
        if(!linha.isEmpty()){
            String [] atributos = linha.split("\\|");
            switch (atributos[0]) {
                case "Professor":
                    novoUsuario = new Professor(atributos[1], Integer.parseInt(atributos[2]));
                    usuarios.add(novoUsuario);
                    break;
                case "Alunos":
                    novoUsuario = new Alunos(atributos[1], Integer.parseInt(atributos[2]));
                    usuarios.add(novoUsuario);
                    break;
                case "AlunoGraduacao":
                    novoUsuario = new AlunoGraduacao(atributos[1], Integer.parseInt(atributos[2]));
                    usuarios.add(novoUsuario);
                    break;
                case "AlunoPosGraduacao":
                    novoUsuario = new AlunoPosGraduacao(atributos[1], Integer.parseInt(atributos[2]));
                    usuarios.add(novoUsuario);
                    break;
            } 
        }
    }


    Livros novoLivro = null;
    scanner = new Scanner(new FileReader(caminhoPastaArquivo +"\\livros.txt"));
    while (scanner.hasNextLine()) {
        String linha = scanner.nextLine();
        if(!linha.isEmpty()){
            String [] atributos = linha.split("\\|");
            switch (atributos[0]) {
                case "LivrosFisicos":
                    novoLivro = new LivrosFisicos(atributos[1], atributos[2], atributos[3]);
                    livros.add(novoLivro);
                    break;
                case "LivrosFisicosPrioritarios":
                    novoLivro = new LivrosFisicosPrioritarios(atributos[1], atributos[2], atributos[3]);
                    livros.add(novoLivro);
                    break;
                case "LivrosDigitais":
                    novoLivro = new LivrosDigitais(atributos[1], atributos[2], atributos[3]);
                    livros.add(novoLivro);
                    break;
            } 
        }
    }

    Emprestimo novoEmprestiom = null;
    Usuarios usuarioEmprestimoAtual = null;
    Livros livroEmprestimoAtual = null;
    scanner = new Scanner(new FileReader(caminhoPastaArquivo +"\\emprestimos.txt"));
    while (scanner.hasNextLine()) {
        String linha = scanner.nextLine();
        if(!linha.isEmpty()){
            String [] atributos = linha.split("\\|");
            //Pesquisa livros para instanciar o emprestiom
            for (Livros itemLivro  : livros) {
                if(atributos[1].equals(itemLivro.getTitulo()))
                {
                    livroEmprestimoAtual = itemLivro;
                    break;      
                }
            }
            //Busca Usuario pela matricula
            usuarioEmprestimoAtual = usuarios.get(Integer.parseInt(atributos[2]));
            novoEmprestiom = new Emprestimo(usuarioEmprestimoAtual, livroEmprestimoAtual);
            emprestimos.add(novoEmprestiom);
        }
    }

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
        //Recupera dados em arquivos antes de iniciar os menus com as operaçoes de usuarios, livros e emprestimos 
        RecuperarDaddosNoArquivo(usuarios, livros, emprestimos);
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
        //Caso o usuario saia do sistema os dados que estão nos objetos serão salvos
        SalvarDaddosNoArquivo(usuarios, livros, emprestimos);

    }
}
