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

    public Usuarios(String novoNome, int matricula) {
        this.nome = novoNome;
        MATRICULA_ATUAL = matricula;
        this.matricula = matricula;
    }

    // Método em que retorna o nome e a matricul do usuario.
    @Override
    public String ToString() {
        return this.nome;
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
        this.emprestimos.add(emprestimo);
    }

    // Metodo que adiciona um livro digital na lista de visualizacao.
    public void addLivroDigital(Livros livroDigital) {
        this.livrosDigitais.add(livroDigital);
        livroDigital.visualizar();
    }

    // Método de devolução do usuario.
    // public void devolver(Emprestimo emprestimo) {

    // // Caso a data prevista for maior que a atual do sistema suspende o usuario
    // int dataAtual = 0;
    // int dataDevolucao = 0; // emprestimo.getDataPrevistaDevolucao()
    // int diasAtraso = 10;

    // // if(emprestimo.getDataPrevistaDevolucao() > dataAtual){
    // // }
    // if (dataDevolucao > dataAtual) {
    // this.suspensao(diasAtraso);
    // }
    // }

    // Método para renovar algum livro que já esteja emprestado.
    public void renovar(Emprestimo emprestimo) {
    }

    // Método para retornar livros atrasados.
    public String listarLivrosAtrasados() {
        return "";
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

    // Método sobreposto.
    public int getDiasDevolucao() {
        // TODO Auto-generated method stub
        return 0;
    }

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

    public String getCategoria() {
        return "";
    }
}