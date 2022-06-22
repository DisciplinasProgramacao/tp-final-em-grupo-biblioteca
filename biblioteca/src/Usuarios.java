import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;

public class Usuarios implements IUsuarios, Serializable {
    private String nome;
    public int matricula;
    public int MATRICULA_ATUAL = 0;
    private LinkedList<Emprestimo> emprestimos;
    private LinkedList<Livros> livrosDigitais;

    public Usuarios(String novoNome) {
        this.nome = novoNome;
        this.matricula = MATRICULA_ATUAL;
        this.MATRICULA_ATUAL++;
        this.emprestimos = new LinkedList<>();
    }

    // Método em que retorna o nome e a matricul do usuario.
    public String ToString() {
        return "";
    }

    public LinkedList<Emprestimo> visualizarHistorico(LocalDate dataInic, LocalDate dataFinal) {
        LinkedList<Emprestimo> emprestimosHistorico = new LinkedList<Emprestimo>();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getDataEmprestimo().isAfter(dataInic)
                    && emprestimo.getDataEmprestimo().isBefore(dataFinal)) {
                emprestimosHistorico.add(emprestimo);
            }
        }
        if (emprestimosHistorico.size() != 0) {
            return emprestimosHistorico;
        } else {
            return null;
        }
    }

    // Método de emprestimo em que adiciona uma classe do tipo Emprestimo na lista
    // do usuario.
    public void emprestar(Emprestimo emprestimo) {
        if (emprestimo.getUsuario().verificarSuspensao()) {
            this.emprestimos.add(emprestimo);
        } else {
            System.out.println("-----------------------------------------------------------");
            System.out.println("Se você for um professor, você tem livros atrasados.");
            System.out.println("Se você for um aluno, você está suspenso.");
        }
    }

    // Metodo que adiciona um livro digital na lista de visualizacao.
    public void addLivroDigital(Livros livroDigital) {
        this.livrosDigitais.add(livroDigital);
        livroDigital.visualizar();
    }

    // Método de devolução do usuario.
    public void devolver(LinkedList<Emprestimo> emprestimos2, Livros livroLocalizado, LocalDate dataDevolucao) {
        int cont = 0;
        for (Emprestimo emprestimo : emprestimos2) {
            if (emprestimo.getLivro().getTitulo().equalsIgnoreCase(livroLocalizado.getTitulo())) {
                emprestimo.setDataDevolucao(dataDevolucao);
                cont++;
                if (this.calculoDiasSuspensao() != 0) {
                    System.out.println("Você está suspenso por " + this.calculoDiasSuspensao());
                }
            }
        }

        if (cont == 0) {
            System.out.println("Emprestimo não encontrado.");
        }
    }

    // Método para renovar algum livro que já esteja emprestado.
    public void renovar(LocalDate novaDataEmprestimo, Emprestimo emprestimo) {
        if (emprestimo.getLivro().verificarEmprestimo(emprestimo, novaDataEmprestimo)) {
            emprestimo.setDataEmprestimo(novaDataEmprestimo);
            System.out.println("Emprestimo renovado com sucesso");

        }
    }

    // Método para retornar livros atrasados.
    public String listarLivrosAtrasados(Usuarios usuario) {
        String texto = "";
        LinkedList<Emprestimo> emprestimos = usuario.getEmprestimos();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getDataPrevistaDevolucao().isBefore(emprestimo.getDataDevolucao())) {
                texto += "Livro Atrasado:" + emprestimo.getLivro().ToString() + "\r\n"
                        + "------------------------------------------------------ \r\n";
            }
        }
        return texto;
    }

    public int getMatricula() {
        return this.matricula;
    }

    public String getNome() {
        return this.nome;
    }

    public LinkedList<Emprestimo> getEmprestimos() {
        return this.emprestimos;
    }

    public LinkedList<Livros> getLivrosVisualizados() {
        return this.livrosDigitais;
    }

    public Livros livroMaisVisualizado(LinkedList<Livros> livros) {
        Livros livro = null;
        int auxiliar = 0;
        for (Livros livroDigital : livros) {
            if (livroDigital.getVisualizacao() > auxiliar) {
                auxiliar = livroDigital.getVisualizacao();
                livro = livroDigital;
            }
        }
        return livro;
    }

    // Método sobreposto.
    public int getDiasDevolucao() {
        // TODO Auto-generated method stub
        return 0;
    }

    // Método sobreposto.
    public int getMaxLivros() {
        // TODO Auto-generated method stub
        return 0;
    }

    public int calculoDiasSuspensao() {
        return 0;
    }

    // Metodo sobreposto.
    public boolean verificarSuspensao() {
        return false;
    }

    // Método sobreposto.
    public int getDiasSuspensao() {
        // TODO Auto-generated method stub
        return 0;
    }

}