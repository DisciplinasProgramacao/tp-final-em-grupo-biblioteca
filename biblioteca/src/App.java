import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class App {

    // Método para introduzir uma data. Retorna uma classe do tipo Data
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

    // Menu Principal - retorna um int
    public static int menu(Scanner teclado) {
        System.out.println("------------------------------------------------------");
        System.out.println("Biblioteca");
        System.out.println("1 - Cadastrar Usuário");
        System.out.println("2 - Cadastrar Livro");
        System.out.println("3 - Realizar empréstimo");
        System.out.println("4 - Visualizar histórico de empréstimo");
        System.out.println("5 - Visualizar alunos suspensos");
        System.out.println("6 - Visualizar livros em atraso");
        System.out.println("7 - Visualizar livro mais emprestado");
        System.out.println("8 - Visualizar livro mais visualizado");
        System.out.println("0 - Sair");
        System.out.println("------------------------------------------------------");

        int opcao = teclado.nextInt();
        teclado.nextLine();
        return opcao;
    }

    // Opção 1 - Cadastro de usuários
    // menu para indicar o tipo de usuário que será cadastrado, retorna o tipo int
    public static int menuUsuario(Scanner teclado) {
        System.out.println("------------------------------------------------------");
        System.out.println("1 - Cadastrar Professor");
        System.out.println("2 - Cadastrar Aluno de Graduação");
        System.out.println("3 - Cadastrar Aluno de Pos Graduação");
        System.out.println("------------------------------------------------------");
        int opcao = teclado.nextInt();
        teclado.nextLine();
        return opcao;
    }

    // Método para cadastrar um Usuario, retorna uma classe do tipo Usuarios
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
                novoUsuario = new AlunoGraduacao(novoNome);
                break;
            case 3:
                novoUsuario = new AlunoPosGraduacao(novoNome);
                break;
        }

        return novoUsuario;
    }

    // Opção 2 - Cadastro de livros
    // Método para indicar o tipo de livro que será cadastrado, retorna um int
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

    // Método para cadastro de livro, retorna uma classe do tipo Livros
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

    // Método para visuzalizar os Emprestimos de um Usuario especifico.
    public static void visualizarEmprestimo(Scanner teclado, LinkedList<Usuarios> usuarios,
            LinkedList<Emprestimo> emprestimos) {
        int novaMatricula;
        Usuarios usuario;
        System.out.println("------------------------------------------------------");
        System.out.println("Digite o numero da matricula: ");
        novaMatricula = Integer.parseInt(teclado.nextLine());
        usuario = usuarios.get(novaMatricula - 1);
        System.out.println("Dias de Suspensao: " + usuario.getDiasSuspensao());
        for (Emprestimo emprestimoItem : emprestimos) {
            if (emprestimoItem.getUsuario().getMatricula() == novaMatricula) {
                System.out.println("Usuario: " + emprestimoItem.getUsuario().getMatricula() + " - "
                        + emprestimoItem.getUsuario().getNome());
                System.out.println("-----------------------------------------------------------");
                System.out.println("Titulo: " + emprestimoItem.getLivro().getTitulo());
                System.out.println("Autor: " + emprestimoItem.getLivro().getAutor());
                System.out.println("Editora: " + emprestimoItem.getLivro().getEditora());

                String dataDevolucaoAuxiliar = "";
                String dataPrevistaDevolucaoAuxiliar = "";
                if (emprestimoItem.getDataDevolucao() != null)
                    dataDevolucaoAuxiliar = emprestimoItem.getDataDevolucao().dataFormatada();

                if (emprestimoItem.getDataPrevistaDevolucao() != null)
                    dataPrevistaDevolucaoAuxiliar = emprestimoItem.getDataPrevistaDevolucao().dataFormatada();

                System.out.println("Data do Emprestimo: " + emprestimoItem.getDataEmprestimo().dataFormatada() + " | "
                        + "Data Prevista Devolução: " + dataPrevistaDevolucaoAuxiliar + " | " + "Data do Devolução: "
                        + dataDevolucaoAuxiliar);
                System.out.println("Pressione enter para volta para o menu");
                teclado.nextLine();
            }
        }
    }

    // Método para visuzalizar e imprimir os alunos que estão suspensos.
    public static void visualizarAlunosSuspensos(Scanner teclado, LinkedList<Usuarios> usuarios) {
        for (Usuarios usuario : usuarios) {
            if (usuario.getDiasSuspensao() > 0) {
                System.out.println(
                        "Usuario: " + usuario.ToString() + " - suspenso: " + usuario.getDiasSuspensao() + " dias");
            }
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println("Pressione enter para volta para o menu");
        teclado.nextLine();
    }

    // Método para visualizar e imprimir os livros que estão atrasados.
    public static void visualizarLivrosAtrasados(Scanner teclado, LinkedList<Emprestimo> emprestimos) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.hashCode() > 0) {
                System.out.println(
                        "Usuario: " + emprestimo.ToString() + " - suspenso: " + emprestimo + " dias");
            }
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println("Pressione enter para volta para o menu");
        teclado.nextLine();
    }

    // Método para visualizar e imprimir os livros que foram mais emprestados.
    public static void visualizarLivroMaisEmprestado(Scanner teclado, LinkedList<Livros> livros) {
        Livros livroMaisEmprestado = null;
        LivrosFisicos mock = new LivrosFisicos("", "", "");
        for (Livros livroFisico : livros) {
            if (livroMaisEmprestado == null
                    && livroFisico.getClass().getSimpleName() == (mock.getClass().getSimpleName())) {
                livroMaisEmprestado = livroFisico;
            } else if (livroFisico.getClass().getSimpleName() == mock.getClass().getSimpleName()
                    && ((LivrosFisicos) livroFisico).getEmprestimos() > ((LivrosFisicos) livroMaisEmprestado)
                            .getEmprestimos()) {
                livroMaisEmprestado = livroFisico;
            }
        }
        System.out.println("O livro mais emprestado é " + livroMaisEmprestado.ToString());
        System.out.println("-----------------------------------------------------------");
        System.out.println("Pressione enter para volta para o menu");
        teclado.nextLine();
    }

    // Método para visualizar e imprimir os livros que foram mais visualizados.
    public static void visualizarLivroMaisVisualizado(Scanner teclado, LinkedList<Livros> livros) {
        Livros livroMaisVisualizado = null;
        LivrosDigitais mock = new LivrosDigitais("", "", "");
        for (Livros livroDigital : livros) {
            if (livroMaisVisualizado == null
                    && livroDigital.getClass().getSimpleName() == (mock.getClass().getSimpleName())) {
                livroMaisVisualizado = livroDigital;
            } else if (livroDigital.getClass().getSimpleName() == (mock.getClass().getSimpleName())
                    && ((LivrosDigitais) livroDigital).getVisualizacao() > ((LivrosDigitais) livroMaisVisualizado)
                            .getVisualizacao()) {
                livroMaisVisualizado = livroDigital;
            }
        }
        System.out.println("O livro mais visualizado é " + livroMaisVisualizado.ToString());
        System.out.println("-----------------------------------------------------------");
        System.out.println("Pressione enter para volta para o menu");
        teclado.nextLine();
    }

    // Opção 3 - Método para realização de empréstimos, ele chama o método da classe
    // Usuarios e cadastra um emprestimo na lista dele.
    public static void cadastrarEmprestimo(Scanner teclado, LinkedList<Usuarios> usuarios,
            LinkedList<Livros> livros, LinkedList<Emprestimo> Emprestimos) {
        int novaMatricula;
        String novoTitulo;
        Emprestimo novoEmprestimo;
        Usuarios usuario;
        Livros livro = null;
        System.out.println("------------------------------------------------------");
        System.out.println("Digite o numero da matricula: ");
        novaMatricula = Integer.parseInt(teclado.nextLine());
        System.out.println("Digite o título do livro: ");
        novoTitulo = teclado.nextLine();

        usuario = usuarios.get(novaMatricula - 1);
        for (Livros itemLivro : livros) {
            if (itemLivro.getTitulo().equals(novoTitulo)) {
                livro = itemLivro;
                break;
            }
        }
        novoEmprestimo = new Emprestimo(usuario, livro);
        usuario.emprestar(novoEmprestimo);
    }

    // Métodos de arquivos para salvar dados no arquivo
    public static void SalvarDaddosNoArquivo(LinkedList<Usuarios> usuarios, LinkedList<Livros> livros,
            LinkedList<Emprestimo> emprestimos) throws IOException {
        FileWriter arq = new FileWriter(caminhoPastaArquivo + "\\usuarios.txt");
        PrintWriter gravarArq = new PrintWriter(arq);

        for (Usuarios usuario : usuarios) {
            var nomeClass = usuario.getClass().getSimpleName();
            gravarArq.println(nomeClass + "|" + usuario.ToString());
        }

        arq.close();

        arq = new FileWriter(caminhoPastaArquivo + "\\livros.txt");
        gravarArq = new PrintWriter(arq);

        for (Livros livro : livros) {
            var nomeClass = livro.getClass().getSimpleName();
            gravarArq.println(nomeClass + "|" + livro.ToString());
        }

        arq.close();

        arq = new FileWriter(caminhoPastaArquivo + "\\emprestimos.txt");
        gravarArq = new PrintWriter(arq);

        for (Emprestimo emprestimo : emprestimos) {
            var nomeClass = emprestimo.getClass().getSimpleName();
            gravarArq.println(nomeClass + "|" + emprestimo.ToString());
        }

        arq.close();
    }

    public static String caminhoPastaArquivo = "C:";

    // Métodos de arquivos para recuperar os dados no arquivo
    public static void RecuperarDaddosNoArquivo(LinkedList<Usuarios> usuarios, LinkedList<Livros> livros,
            LinkedList<Emprestimo> emprestimos) throws FileNotFoundException {
        try {
            Usuarios novoUsuario = null;
            FileReader file = new FileReader(caminhoPastaArquivo + "\\usuarios.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                if (!linha.isEmpty()) {
                    String[] atributos = linha.split("\\|");
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
            scanner = new Scanner(new FileReader(caminhoPastaArquivo + "\\livros.txt"));
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                if (!linha.isEmpty()) {
                    String[] atributos = linha.split("\\|");
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
            scanner = new Scanner(new FileReader(caminhoPastaArquivo + "\\emprestimos.txt"));
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                if (!linha.isEmpty()) {
                    String[] atributos = linha.split("\\|");
                    // Pesquisa livros para instanciar o emprestiom
                    for (Livros itemLivro : livros) {
                        if (atributos[1].equals(itemLivro.getTitulo())) {
                            livroEmprestimoAtual = itemLivro;
                            break;
                        }
                    }
                    // Busca Usuario pela matricula
                    usuarioEmprestimoAtual = usuarios.get(Integer.parseInt(atributos[2]));
                    novoEmprestiom = new Emprestimo(usuarioEmprestimoAtual, livroEmprestimoAtual);
                    emprestimos.add(novoEmprestiom);
                }
            }
        } catch (Exception ex) {
        }
    }

    // Metodo de inicialização do sistema da biblioteca.
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
        // Recupera dados em arquivos antes de iniciar os menus com as operaçoes de
        // usuarios, livros e emprestimos
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
                    cadastrarEmprestimo(teclado, usuarios, livros, emprestimos);
                    break;
                case 4:
                    visualizarEmprestimo(teclado, usuarios, emprestimos);
                    break;
                case 5:
                    visualizarAlunosSuspensos(teclado, usuarios);
                    break;
                case 6:
                    visualizarLivrosAtrasados(teclado, emprestimos);
                    break;
                case 7:
                    visualizarLivroMaisEmprestado(teclado, livros);
                    break;
                case 8:
                    visualizarLivroMaisVisualizado(teclado, livros);
                    break;
            }
            // pausa(teclado);
        } while (opcao != 0);
        // Caso o usuario saia do sistema os dados que estão nos objetos serão salvos
        SalvarDaddosNoArquivo(usuarios, livros, emprestimos);
    }
}
