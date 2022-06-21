import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class AppCopy {

    static String arqDados = "tudo.bin";
    static String arqDadosLivros = "livros.txt";

    public static void gravarDados(Set<Usuarios> usuarios) throws IOException {
        ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(arqDados));
        for (Usuarios usuario : usuarios) {
            obj.writeObject(usuario);
        }
        obj.close();
    }

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
        System.out.println("2 - Cadastrar Aluno de Graduação");
        System.out.println("3 - Cadastrar Aluno de Pos Graduação");
        System.out.println("------------------------------------------------------");
        int opcao = Integer.parseInt(teclado.nextLine());
        return opcao;
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

    // Opção 2 - Cadastro de livros
    // Método para indicar o tipo de livro que será cadastrado
    public static int menuLivro(Scanner teclado) {
        System.out.println("------------------------------------------------------");
        System.out.println("1 - Cadastrar Livro Físico");
        System.out.println("2 - Cadastrar Livro Físico Prioritário");
        System.out.println("3 - Cadastrar Livro Digital");
        System.out.println("------------------------------------------------------");
        int opcao = Integer.parseInt(teclado.nextLine());
        return opcao;
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
            case 3:
                novoLivro = new LivrosDigitais(novoAutor, novaEditora, novoTitulo);
                break;
        }

        return novoLivro;
    }

    // Opção 3 - Método para realização de empréstimos
    public static void cadastrarEmprestimo(Scanner teclado, Usuarios usuario,
            Livros livro) {
        Emprestimo novoEmprestimo;
        System.out.println("------------------------------------------------------");

        // usuario = localizarUsuario(teclado, usuarios);
        // System.out.println("Digite o numero da matricula: ");
        // novaMatricula = Integer.parseInt(teclado.nextLine());
        // System.out.println("Digite o título do livro: ");
        // novoTitulo = teclado.nextLine();

        // for (Livros itemLivro : livros) {
        // if (itemLivro.getTitulo().equals(novoTitulo)) {
        // livro = itemLivro;
        // break;
        // }
        // }
        novoEmprestimo = new Emprestimo(usuario, livro);
        usuario.emprestar(novoEmprestimo);
        System.out.println("Emprestimo realizado com sucesso.");
    }

    public static void visualizarEmprestimos(Scanner teclado,
            TreeSet<Usuarios> usuarios) {
        int novaMatricula;
        Usuarios usuarioEncontrado;
        LinkedList<Emprestimo> emprestimos;
        System.out.println("------------------------------------------------------");
        System.out.println("Digite o numero da matricula: ");
        novaMatricula = Integer.parseInt(teclado.nextLine());

        usuarioEncontrado = localizarUsuario(teclado, usuarios);
        emprestimos = usuarioEncontrado.getEmprestimos();

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getUsuario().getMatricula() == novaMatricula) {
                System.out.println("Usuario: " + emprestimo.getUsuario().getMatricula() +
                        " - "
                        + emprestimo.getUsuario().getNome());
                System.out.println("-----------------------------------------------------------");
                System.out.println("Titulo: " + emprestimo.getLivro().getTitulo());
                System.out.println("Autor: " + emprestimo.getLivro().getAutor());
                System.out.println("Editora: " + emprestimo.getLivro().getEditora());

                String dataDevolucaoAuxiliar = "";
                String dataPrevistaDevolucaoAuxiliar = "";
                if (emprestimo.getDataDevolucao() != null)
                    dataDevolucaoAuxiliar = emprestimo.getDataDevolucao().dataFormatada();

                if (emprestimo.getDataPrevistaDevolucao() != null)
                    dataPrevistaDevolucaoAuxiliar = emprestimo.getDataPrevistaDevolucao().dataFormatada();

                System.out.println("Data do Emprestimo: " +
                        emprestimo.getDataEmprestimo().dataFormatada() + " | "
                        + "Data Prevista Devolução: " + dataPrevistaDevolucaoAuxiliar + " | " + "Data do Devolução: "
                        + dataDevolucaoAuxiliar);
                System.out.println("Pressione enter para volta para o menu");
                teclado.nextLine();
            }
        }
    }

    private static Usuarios localizarUsuario(Scanner teclado, TreeSet<Usuarios> usuarios) {
        String nome;
        Usuarios usuario;
        // limparTela();
        System.out.println("Biblioteca");
        System.out.println("==========================");
        System.out.println("Encontrar usuario");
        System.out.print("NOME: ");

        nome = teclado.nextLine();
        usuario = new Usuarios(nome);

        Usuarios usuarioEncontrado = null;
        usuarioEncontrado = usuarios.floor(usuario);
        if (usuarioEncontrado != null)
            return usuarioEncontrado;
        else
            return null;
    }

    private static Livros localizarLivros(Scanner teclado, TreeSet<Livros> livros) {
        String autor;
        String editora;
        String titulo;
        Livros livro;
        // limparTela();
        System.out.println("Biblioteca");
        System.out.println("==========================");
        System.out.println("Encontrar livro");
        System.out.println("AUTOR: ");
        autor = teclado.nextLine();
        System.out.println("EDITORA: ");
        editora = teclado.nextLine();
        System.out.println("TITULO: ");
        titulo = teclado.nextLine();

        livro = new Livros(autor, editora, titulo);

        Livros livroEncontrado = null;
        livroEncontrado = livros.floor(livro);
        if (livroEncontrado != null)
            return livroEncontrado;
        else
            return null;
    }

    // Métodos de arquivos
    public static void SalvarDaddosNoArquivo(LinkedList<Usuarios> usuarios,
            LinkedList<Livros> livros,
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

    public static String caminhoPastaArquivo = "D:";

    public static Set<Livros> carregarDadosLivros(Scanner teclado) {
        FileInputStream dados;
        TreeSet<Livros> todosLivros = new TreeSet<>();

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
            arqDados = teclado.nextLine();
            // pausa(teclado);
        } catch (IOException ex) {
            System.out.println("Problema no uso do arquivo.");
            System.out.println("Favor reiniciar o sistema.");
            // pausa(teclado);
        } catch (ClassNotFoundException cex) {
            System.out.println("Classe não encontrada: avise ao suporte.");
            System.out.println("Clientes e pedidos em branco.");
            todosLivros = new TreeSet<>();
            // pausa(teclado);
        }

        return todosLivros;
    }

    public static Set<Usuarios> carregarDados(Scanner teclado) {
        FileInputStream dados;
        TreeSet<Usuarios> todosUsuarios = new TreeSet<>();

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
            arqDados = teclado.nextLine();
            // pausa(teclado);
        } catch (IOException ex) {
            System.out.println("Problema no uso do arquivo.");
            System.out.println("Favor reiniciar o sistema.");
            // pausa(teclado);
        } catch (ClassNotFoundException cex) {
            System.out.println("Usuarios em branco.");
            todosUsuarios = new TreeSet<>();
            // pausa(teclado);
        }

        return todosUsuarios;
    }

    // public static void RecuperarDaddosNoArquivo(LinkedList<Usuarios> usuarios,
    // LinkedList<Livros> livros,
    // LinkedList<Emprestimo> emprestimos) throws FileNotFoundException {
    // try {
    // Usuarios novoUsuario = null;
    // FileReader file = new FileReader(caminhoPastaArquivo + "\\usuarios.txt");
    // Scanner scanner = new Scanner(file);
    // while (scanner.hasNextLine()) {
    // String linha = scanner.nextLine();
    // if (!linha.isEmpty()) {
    // String[] atributos = linha.split("\\|");
    // switch (atributos[0]) {
    // case "Professor":
    // novoUsuario = new Professor(atributos[1], Integer.parseInt(atributos[2]));
    // usuarios.add(novoUsuario);
    // break;
    // case "AlunoGraduacao":
    // novoUsuario = new AlunoGraduacao(atributos[1],
    // Integer.parseInt(atributos[2]));
    // usuarios.add(novoUsuario);
    // break;
    // case "AlunoPosGraduacao":
    // novoUsuario = new AlunoPosGraduacao(atributos[1],
    // Integer.parseInt(atributos[2]));
    // usuarios.add(novoUsuario);
    // break;
    // }
    // }
    // }

    // Livros novoLivro = null;
    // scanner = new Scanner(new FileReader(caminhoPastaArquivo + "\\livros.txt"));
    // while (scanner.hasNextLine()) {
    // String linha = scanner.nextLine();
    // if (!linha.isEmpty()) {
    // String[] atributos = linha.split("\\|");
    // switch (atributos[0]) {
    // case "LivrosFisicos":
    // novoLivro = new LivrosFisicos(atributos[1], atributos[2], atributos[3]);
    // livros.add(novoLivro);
    // break;
    // case "LivrosFisicosPrioritarios":
    // novoLivro = new LivrosFisicosPrioritarios(atributos[1], atributos[2],
    // atributos[3]);
    // livros.add(novoLivro);
    // break;
    // case "LivrosDigitais":
    // novoLivro = new LivrosDigitais(atributos[1], atributos[2], atributos[3]);
    // livros.add(novoLivro);
    // break;
    // }
    // }
    // }

    // Emprestimo novoEmprestiom = null;
    // Usuarios usuarioEmprestimoAtual = null;
    // Livros livroEmprestimoAtual = null;
    // scanner = new Scanner(new FileReader(caminhoPastaArquivo +
    // "\\emprestimos.txt"));
    // while (scanner.hasNextLine()) {
    // String linha = scanner.nextLine();
    // if (!linha.isEmpty()) {
    // String[] atributos = linha.split("\\|");
    // // Pesquisa livros para instanciar o emprestiom
    // for (Livros itemLivro : livros) {
    // if (atributos[1].equals(itemLivro.getTitulo())) {
    // livroEmprestimoAtual = itemLivro;
    // break;
    // }
    // }
    // // Busca Usuario pela matricula
    // usuarioEmprestimoAtual = usuarios.get(Integer.parseInt(atributos[2]));
    // novoEmprestiom = new Emprestimo(usuarioEmprestimoAtual,
    // livroEmprestimoAtual);
    // emprestimos.add(novoEmprestiom);
    // }
    // }
    // } catch (Exception ex) {
    // }
    // }

    public static void main(String[] args) throws Exception {
        int opcao = -1;
        Data dataInicial = null;
        Data dataFinal = null;
        Scanner teclado = new Scanner(System.in);

        Usuarios novoUsuario = null;
        Usuarios usuarioAtual = null;
        LinkedList<Usuarios> usuarios = new LinkedList<Usuarios>();

        Livros novoLivro = null;
        Livros livroAtual = null;
        LinkedList<Livros> livros = new LinkedList<Livros>();

        // Emprestimo emprestimoAtual;
        LinkedList<Emprestimo> emprestimos = new LinkedList<Emprestimo>();

        String escolhaRecorrencia;
        int recorrencia, qtdDeVezes;
        // Recupera dados em arquivos antes de iniciar os menus com as operaçoes de
        // usuarios, livros e emprestimos
        TreeSet<Usuarios> todosUsuarios = (TreeSet<Usuarios>) carregarDados(teclado);
        TreeSet<Livros> todosLivros = (TreeSet<Livros>) carregarDadosLivros(teclado);
        // RecuperarDaddosNoArquivo(usuarios, livros, emprestimos);

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
                    if (usuarioAtual == null) {
                        // usuarioAtual = localizarUsuario(teclado, todosUsuarios);
                        if (usuarioAtual == null) {
                            String nome;
                            // limparTela();
                            System.out.println("Biblioteca");
                            System.out.println("==========================");
                            System.out.println("Encontrar usuario");
                            System.out.print("NOME: ");

                            nome = teclado.nextLine();
                            // usuario = new Usuarios(nome);
                            for (Usuarios usuario : usuarios) {
                                if (usuario.getNome().equals(nome)) {
                                    usuarioAtual = usuario;
                                }
                            }
                        }
                    }
                    if (livroAtual == null) {
                        livroAtual = localizarLivros(teclado, todosLivros);
                        if (livroAtual == null) {
                            String titulo;
                            // limparTela();
                            System.out.println("Biblioteca");
                            System.out.println("==========================");
                            System.out.println("Encontrar livro");
                            System.out.print("Titulo: ");

                            titulo = teclado.nextLine();
                            // usuario = new Usuarios(nome);
                            for (Livros livro : livros) {
                                if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                                    livroAtual = livro;
                                }
                            }
                            // livroAtual = cadastrarLivro(teclado);
                            // if (livroAtual == null) {
                            // System.out.println("Problema na criação do usuario.");
                            // break;
                            // }
                            // livros.add(livroAtual);
                            // System.out.println("livro ainda não cadastrado.");
                            // break;
                        }
                    }
                    cadastrarEmprestimo(teclado, usuarioAtual, livroAtual);
                    break;
                case 4:
                    visualizarEmprestimos(teclado, todosUsuarios);
                    break;
            }
            // pausa(teclado);
        } while (opcao != 0);
        // Caso o usuario saia do sistema os dados que estão nos objetos serão salvos
        gravarDados(todosUsuarios);
        // SalvarDaddosNoArquivo(usuarios, livros, emprestimos);

    }
}
