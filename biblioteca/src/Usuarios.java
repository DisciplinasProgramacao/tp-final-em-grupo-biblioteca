import java.util.Date;
import java.time.LocalDate;
import java.util.LinkedList;

public class Usuarios implements IUsuarios {
    private String nome;
    private int matricula;
    protected int qtdDiasSuspenso = 0;
    private static int MATRICULA_ATUAL = 1;
    private LinkedList<Emprestimo> emprestimos;
    private LinkedList<LivrosDigitais> livrosDigitais;

    public Usuarios(String novoNome) {
        this.nome = novoNome;
        this.matricula = MATRICULA_ATUAL;
        MATRICULA_ATUAL++;
        this.emprestimos = new LinkedList<>();
    }

    public Usuarios(String novoNome, int matricula) {
        this.nome = novoNome;
        MATRICULA_ATUAL = matricula;
        this.matricula = matricula;
    }

    @Override
    public String ToString() {
        return this.nome + "|" + this.matricula;
    }

    public String visualizarHistorico(Data dataInic, Data dataFinal) {
        return "";
    }

    public String visualizarDadosCadastrais() {
        return "";
    }

    // public void emprestar(LinkedList<Emprestimo> emprestimos, Livros livro) {
    // emprestimos.add(new Emprestimo(this, livro));
    // }

    public void emprestar(Emprestimo emprestimo) {
        this.emprestimos.add(emprestimo);
    }

    public void addLivroDigital(LivrosDigitais livroDigital) {
        this.livrosDigitais.add(livroDigital);
        livroDigital.visualizar();
    }

    public void devolver(Emprestimo emprestimo) {

        // Caso a data prevista for maior que a atual do sistema suspende o usuario
        int dataAtual = 0;
        int dataDevolucao = 0; // emprestimo.getDataPrevistaDevolucao()
        int diasAtraso = 10;

        // if(emprestimo.getDataPrevistaDevolucao() > dataAtual){
        // }
        if (dataDevolucao > dataAtual) {
            this.suspensao(diasAtraso);
        }
    }

    public void renovar(Emprestimo emprestimo) {
    }

    public String listarLivrosAtrasados() {
        return "";
    }

    @Override
    public int getMatricula() {
        return this.matricula;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public int getDiasDevolucao() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getDiasSuspensao() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int suspensao(int diasAtrazo) {
        // TODO Auto-generated method stub
        this.qtdDiasSuspenso = 0 * diasAtrazo;
        return qtdDiasSuspenso;
    }

}