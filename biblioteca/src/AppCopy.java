import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

public class AppCopy {

    static String arqDados = "tudo.bin";
    static String arqDadosLivros = "livros.bin";

    public static void gravarDados(LinkedList<Usuarios> usuarios) throws IOException {
        ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(arqDados));
        for (Usuarios usuario : usuarios) {
            obj.writeObject(usuario);
        }
        obj.close();
    }

    public static void gravarLivros(LinkedList<Livros> livros) throws IOException {
        ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(arqDadosLivros));
        for (Livros livro : livros) {
            obj.writeObject(livro);
        }
        obj.close();
    }

    // Método para introduzir uma data.
    public static LocalDate criarData(Scanner teclado) {
        int dia, mes, ano;
        System.out.println("------------------------------------------------------");
        System.out.println("Digite número do dia: ");
        dia = Integer.parseInt(teclado.nextLine());
        System.out.println("Digite número do mes: ");
        mes = Integer.parseInt(teclado.nextLine());
        System.out.println("Digite número do ano: ");
        ano = Integer.parseInt(teclado.nextLine());

        LocalDate dataRetorno = LocalDate.of(ano, mes, dia);
        return dataRetorno;
    }

    // Menu Principal
    public static int menu(Scanner teclado) {
        System.out.println("------------------------------------------------------");
        System.out.println("Biblioteca");
        System.out.println("1 - Cadastrar Usuário");
        System.out.println("2 - Cadastrar Livro");
        System.out.println("3 - Cadastrar Livro Digital");
        System.out.println("4 - Realizar empréstimo");
        System.out.println("5 - Visualizar histórico de empréstimo do usuario");
        System.out.println("6 - Devolver livro");
        System.out.println("7 - Baixar livro Digital");
        System.out.println("8 - Ver relatórios");
        System.out.println("9 - Alterar data de devolucao do usuário");
        System.out.println("10 - Renovar emprestimo");
        System.out.println("0 - Sair");
        System.out.println("------------------------------------------------------");

        int opcao = teclado.nextInt();
        teclado.nextLine();
        return opcao;
    }

    public static int menuRelatorio(Scanner teclado) {
        System.out.println("------------------------------------------------------");
        System.out.println("Biblioteca");
        System.out.println("1 - imprimir Livro Mais Visualizado");
        System.out.println("2 - visualizar Livro Mais Emprestado");
        System.out.println("3 - visualizar Alunos Suspensos e livros Atrasados");
        System.out.println("4 - visualizar todos alunos");
        System.out.println("5 - visualizar todos os livros");
        System.out.println("6 - visualizar livros atrasados");
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
        System.out.println("2 - Cadastrar Aluno de Graduação");
        System.out.println("3 - Cadastrar Aluno de Pos Graduação");
        System.out.println("------------------------------------------------------");
        int opcao = Integer.parseInt(teclado.nextLine());
        return opcao;
    }

    // Opção 2 - Cadastro de livros
    // Método para indicar o tipo de livro que será cadastrado
    public static int menuLivro(Scanner teclado) {
        System.out.println("------------------------------------------------------");
        System.out.println("1 - Cadastrar Livro Físico");
        System.out.println("2 - Cadastrar Livro Físico Prioritário");
        System.out.println("------------------------------------------------------");
        int opcao = Integer.parseInt(teclado.nextLine());
        return opcao;
    }

    public static void criarRelatorio(Scanner teclado, LinkedList<Livros> livros, LinkedList<Usuarios> usuarios) {
        int opcao = menuRelatorio(teclado);
        switch (opcao) {
            case 1:
                imprimirLivroMaisVisualizado(teclado, usuarios);
                break;
            case 2:
                visualizarLivroMaisEmprestado(teclado, livros);
                break;
            case 3:
                visualizarAlunosSuspensos(teclado, usuarios);
                break;
            case 4:
                visualizarUsuarios(teclado, usuarios);
                break;
            case 5:
                visualizarTodosLivros(teclado, livros);
                break;
            case 6:
                for (Usuarios usuarios2 : usuarios) {
                    usuarios2.listarLivrosAtrasados();
                }
                break;
        }
    }

    // Método para cadastro de usuário
    public static Usuarios cadastrarUsuario(Scanner teclado) {
        Usuarios novoUsuario = null;
        String novoNome;
        System.out.println("------------------------------------------------------");
        System.out.println("Incluir um usuário");
        System.out.println("Digite o nome do usuário: ");
        novoNome = teclado.nextLine();
        int opcao = menuUsuario(teclado);
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

    // Método para cadastro de livro
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
                novoLivro = new LivrosFisicosPrioritarios(novoAutor, novaEditora,
                        novoTitulo);
                break;
        }
        return novoLivro;
    }

    public static Livros cadastrarLivroDigital(Scanner teclado) {
        Livros novoLivro = null;
        String novoAutor, novaEditora, novoTitulo;
        System.out.println("------------------------------------------------------");
        System.out.println("Digite o nome do autor: ");
        novoAutor = teclado.nextLine();
        System.out.println("Digite a editora: ");
        novaEditora = teclado.nextLine();
        System.out.println("Digite o título do livro: ");
        novoTitulo = teclado.nextLine();
        novoLivro = new LivrosDigitais(novoAutor, novaEditora, novoTitulo);
        return novoLivro;
    }

    // Opção 3 - Método para realização de empréstimos
    public static void cadastrarEmprestimo(Scanner teclado, Usuarios usuario,
            Livros livro) {
        Emprestimo novoEmprestimo;
        System.out.println("------------------------------------------------------");

        novoEmprestimo = new Emprestimo(usuario, livro);
        usuario.emprestar(novoEmprestimo);
        System.out.println("Emprestimo realizado com sucesso.");
    }

    public static void visualizarEmprestimosPorData(Scanner teclado, Usuarios usuario) {
        LinkedList<Emprestimo> emprestimos;
        System.out.println("Data inicial");
        LocalDate dataInic = criarData(teclado);

        System.out.println("Data final");
        LocalDate dataFinal = criarData(teclado);

        System.out.println("Usuario: " + usuario.ToString());
        System.out.println("Dias suspenso: " + usuario.calculoDiasSuspensao());
        try {
            emprestimos = usuario.visualizarHistorico(dataInic, dataFinal);
            for (Emprestimo emprestimo : emprestimos) {
                System.out.println(emprestimo.ToString(emprestimo));
            }
        } catch (NullPointerException e) {
            // TODO: handle exception
            System.out.println("-----------------------------------------------------------");
            System.out.println("Não existe emprestimos entre essas datas.");
        }
        System.out.println("Pressione enter para volta para o menu");
        teclado.nextLine();
    }

    public static void visualizarAlunosSuspensos(Scanner teclado, LinkedList<Usuarios> usuarios) {
        int cont = 0;
        for (Usuarios usuario : usuarios) {
            if (!usuario.verificarSuspensao()) {
                System.out.println("Usuario:" + usuario.ToString());
                System.out.println("Dias Suspenso:" + usuario.calculoDiasSuspensao());
                System.out.println("=====================");
                cont++;
            }
        }

        if (cont == 0) {
            System.out.println("-----------------------------------------------------------");
            System.out.println("Não existe alunos suspensos.");
        }

        System.out.println("Pressione enter para volta para o menu");
        teclado.nextLine();
    }

    // // Método para visualizar e imprimir os livros que foram mais visualizados.
    public static void imprimirLivroMaisVisualizado(Scanner teclado,
            LinkedList<Usuarios> usuarios) {
        Livros livroMaisVisualizado = null;
        for (Usuarios usuario : usuarios) {
            livroMaisVisualizado = usuario.livroMaisVisualizado(usuario.getLivrosVisualizados());
        }
        System.out.println("O livro mais visualizado é " +
                livroMaisVisualizado.ToString());
        System.out.println("-----------------------------------------------------------");
        System.out.println("Pressione enter para volta para o menu");
        teclado.nextLine();
    }

    // // Método para visualizar e imprimir os livros que foram mais emprestados.
    public static void visualizarLivroMaisEmprestado(Scanner teclado,
            LinkedList<Livros> livros) {
        Livros livroMaisEmprestado = null;
        int auxiliar = 0;
        for (Livros livroFisico : livros) {
            if (livroFisico.getEmprestimos() > auxiliar) {
                auxiliar = livroFisico.getEmprestimos();
                livroMaisEmprestado = livroFisico;
            }
        }
        System.out.println("O livro mais emprestado é " +
                livroMaisEmprestado.ToString());
        System.out.println("-----------------------------------------------------------");
        System.out.println("Pressione enter para volta para o menu");
        teclado.nextLine();
    }

    public static void visualizarUsuarios(Scanner teclado,
            LinkedList<Usuarios> usuarios) {
        for (Usuarios usuarios2 : usuarios) {
            System.out.println(
                    "Usuario:" + " " + usuarios2.ToString());
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println("Pressione enter para volta para o menu");
        teclado.nextLine();
    }

    public static void visualizarTodosLivros(Scanner teclado,
            LinkedList<Livros> livros) {
        for (Livros livro : livros) {
            System.out.println("Livro:" + livro.ToString());
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println("Pressione enter para volta para o menu");
        teclado.nextLine();
    }

    private static Usuarios localizarUsuario(Scanner teclado, LinkedList<Usuarios> usuarios, String nome) {
        Usuarios usuarioEncontrado = null;
        for (Usuarios usuario : usuarios) {
            if (usuario.getNome().equalsIgnoreCase(nome)) {
                usuarioEncontrado = usuario;
            }
        }
        if (usuarioEncontrado != null)
            return usuarioEncontrado;
        else
            return null;
    }

    private static Livros localizarLivros(Scanner teclado, LinkedList<Livros> livros, String titulo) {
        Livros livroEncontrado = null;
        for (Livros livro : livros) {
            if (livro.getTitulo().equals(titulo)) {
                livroEncontrado = livro;
            }
        }
        if (livroEncontrado != null)
            return livroEncontrado;
        else
            return null;
    }

    public static LinkedList<Livros> carregarDadosLivros(Scanner teclado) {
        FileInputStream dados;
        LinkedList<Livros> todosLivros = new LinkedList<>();

        try {
            dados = new FileInputStream(arqDadosLivros);
            ObjectInputStream obj = new ObjectInputStream(dados);
            while (dados.available() != 0) {
                Livros novo = (Livros) obj.readObject();
                todosLivros.add(novo);
            }
            obj.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
            System.out.println("Clientes e pedidos em branco.");
            System.out.print("Nome do arquivo de dados: ");
            // arqDados = teclado.nextLine();
            // pausa(teclado);
        } catch (IOException ex) {
            System.out.println("Problema no uso do arquivo.");
            System.out.println("Favor reiniciar o sistema.");
            // pausa(teclado);
        } catch (ClassNotFoundException cex) {
            System.out.println("Classe não encontrada: avise ao suporte.");
            System.out.println("Clientes e pedidos em branco.");
            todosLivros = new LinkedList<>();
            // pausa(teclado);
        }

        return todosLivros;
    }

    public static LinkedList<Usuarios> carregarDados(Scanner teclado) {
        FileInputStream dados;
        LinkedList<Usuarios> todosUsuarios = new LinkedList<>();

        try {
            dados = new FileInputStream(arqDados);
            ObjectInputStream obj = new ObjectInputStream(dados);
            while (dados.available() != 0) {
                Usuarios novo = (Usuarios) obj.readObject();
                todosUsuarios.add(novo);
            }
            obj.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
            System.out.println("Usuarios em branco.");
            System.out.print("Nome do arquivo de dados: ");
            // arqDados = teclado.nextLine();
            // pausa(teclado);
        } catch (IOException ex) {
            System.out.println("Problema no uso do arquivo.");
            System.out.println("Favor reiniciar o sistema.");
            // pausa(teclado);
        } catch (ClassNotFoundException cex) {
            System.out.println("Usuarios em branco.");
            todosUsuarios = new LinkedList<>();
            // pausa(teclado);
        }

        return todosUsuarios;
    }

    public static String retornaNomeUsuario(Scanner teclado) {
        String nome;

        System.out.println("Biblioteca");
        System.out.println("==========================");
        System.out.println("Encontrar usuário");
        System.out.print("Nome do usuário: ");

        nome = teclado.nextLine();
        return nome;
    }

    public static String retornaTituloLivro(Scanner teclado) {
        String titulo;

        System.out.println("Biblioteca");
        System.out.println("==========================");
        System.out.println("Encontrar livro");
        System.out.print("Titulo do livro: ");

        titulo = teclado.nextLine();
        return titulo;
    }

    public static void main(String[] args) throws Exception {
        int opcao = -1;
        Scanner teclado = new Scanner(System.in);

        Usuarios usuarioAux;
        Livros livroEncontradoAux;
        LinkedList<Emprestimo> emprestimosAux;
        LocalDate dataDevolucaoAux;

        Usuarios novoUsuario = null;
        LinkedList<Usuarios> usuarios = new LinkedList<Usuarios>();

        Livros novoLivro = null;
        LinkedList<Livros> livros = new LinkedList<Livros>();

        Livros novoLivroDigital = null;
        LinkedList<Livros> livrosDigitais = new LinkedList<Livros>();

        // Emprestimo emprestimoAtual;
        LinkedList<Emprestimo> emprestimos = new LinkedList<Emprestimo>();
        // Recupera dados em arquivos antes de iniciar os menus com as operaçoes de
        // usuarios, livros e emprestimos
        LinkedList<Usuarios> todosUsuarios = (LinkedList<Usuarios>) carregarDados(teclado);
        LinkedList<Livros> todosLivros = (LinkedList<Livros>) carregarDadosLivros(teclado);

        if (todosUsuarios != null) {
            for (Usuarios usuario : todosUsuarios) {
                usuarios.add(usuario);
            }
        }

        if (todosLivros != null) {
            for (Livros livro : todosLivros) {
                livros.add(livro);
            }
        }

        System.out.println(usuarios);
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
                    novoLivroDigital = cadastrarLivroDigital(teclado);
                    livrosDigitais.add(novoLivroDigital);
                    break;
                case 4:
                    Usuarios usuarioAtual;
                    try {
                        usuarioAtual = localizarUsuario(teclado, usuarios, retornaNomeUsuario(teclado));
                    } catch (NullPointerException e) {
                        // TODO: handle exception
                        System.out.println("Usuario não localizado! Cadastre-se primeiro ou selecione um existente");
                        System.out.println("-----------------------------------------------------------");

                        usuarioAtual = cadastrarUsuario(teclado);
                        usuarios.add(usuarioAtual);
                    }
                    // limparTela();
                    Livros livroAtual;
                    try {
                        livroAtual = localizarLivros(teclado, livros, retornaTituloLivro(teclado));
                    } catch (NullPointerException e) {
                        // TODO: handle exception
                        System.out
                                .println("Livro não localizado! Faça o cadastro  primeiro ou selecione um existente.");
                        System.out.println("-----------------------------------------------------------");
                        livroAtual = cadastrarLivro(teclado);
                        livros.add(livroAtual);
                    }
                    if (usuarioAtual.getEmprestimos().size() < usuarioAtual.getMaxLivros()) {
                        cadastrarEmprestimo(teclado, usuarioAtual, livroAtual);
                    } else {
                        System.out.println("-----------------------------------------------------------");
                        System.out.println(
                                "O seu número de emprestimos está cheio. Devolva algum livro antes de realizar um novo empréstimo");
                    }
                    break;
                case 5:
                    try {
                        String nome1 = retornaNomeUsuario(teclado);
                        usuarioAux = localizarUsuario(teclado, usuarios, nome1);
                        visualizarEmprestimosPorData(teclado, usuarioAux);
                    } catch (NullPointerException e) {
                        // TODO: handle exception
                        System.out.println("Usuario não localizado! Faça o cadastro primeiro.");
                    }
                    break;
                case 6:
                    usuarioAux = localizarUsuario(teclado, usuarios, retornaNomeUsuario(teclado));
                    livroEncontradoAux = localizarLivros(teclado, livros, retornaTituloLivro(teclado));

                    System.out.println("------------------------------------------------------");
                    System.out.println("Data de devolução");
                    dataDevolucaoAux = criarData(teclado);
                    emprestimosAux = usuarioAux.getEmprestimos();
                    usuarioAux.devolver(emprestimosAux, livroEncontradoAux, dataDevolucaoAux);
                    break;
                case 7:
                    usuarioAux = localizarUsuario(teclado, usuarios, retornaNomeUsuario(teclado));
                    livroEncontradoAux = localizarLivros(teclado, livrosDigitais, retornaTituloLivro(teclado));

                    usuarioAux.addLivroDigital(livroEncontradoAux);
                    break;
                case 8:
                    criarRelatorio(teclado, livros, usuarios);
                    break;
                case 9:
                    usuarioAux = localizarUsuario(teclado, usuarios, retornaNomeUsuario(teclado));
                    livroEncontradoAux = localizarLivros(teclado, livros, retornaTituloLivro(teclado));

                    System.out.println("------------------------------------------------------");
                    System.out.println("Nova data de devolução");
                    dataDevolucaoAux = criarData(teclado);
                    emprestimosAux = usuarioAux.getEmprestimos();
                    for (Emprestimo empr : emprestimosAux) {
                        if (empr.getLivro().getTitulo().equalsIgnoreCase(livroEncontradoAux.getTitulo())) {
                            empr.setDataDevolucao(dataDevolucaoAux);
                            System.out.println("Data de devolução alterada com sucesso!");
                        }
                    }
                    break;
                case 10:
                    usuarioAux = localizarUsuario(teclado, usuarios, retornaNomeUsuario(teclado));
                    livroEncontradoAux = localizarLivros(teclado, livros, retornaTituloLivro(teclado));

                    System.out.println("------------------------------------------------------");
                    System.out.println("Nova data de empréstimo");
                    dataDevolucaoAux = criarData(teclado);
                    emprestimosAux = usuarioAux.getEmprestimos();
                    for (Emprestimo empr : emprestimosAux) {
                        if (empr.getLivro().getTitulo().equalsIgnoreCase(livroEncontradoAux.getTitulo())) {
                            usuarioAux.renovar(dataDevolucaoAux, empr);
                        }
                    }
                    break;
            }
            // pausa(teclado);
        } while (opcao != 0);
        // Caso o usuario saia do sistema os dados que estão nos objetos serão salvos
        gravarDados(usuarios);
        gravarLivros(livros);
    }
}
