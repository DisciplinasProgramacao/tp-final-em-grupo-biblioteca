public class Usuarios {
    private String nome;
    private int matricula = 0;
    private static int MATRICULA_ATUAL = 0;

    public Usuarios(String novoNome) {
        this.nome = novoNome;
        this.matricula = MATRICULA_ATUAL++;
    }

    public Usuarios(String novoNome, int matricula) {
        this.nome = novoNome;
        MATRICULA_ATUAL = matricula;
        this.matricula = matricula;
    }

    
    public String ToString() {
        return this.nome + "|" + this.matricula ;
    }

    public String visualizarHistorico(Data dataInic, Data dataFinal) {
        return "";
    }

    public String visualizarDadosCadastrais() {
        return "";
    }

    public void emprestar(Emprestimo emprestimo) {

    }

    public void devolver(Emprestimo emprestimo) {

    }

    public void renovar(Emprestimo emprestimo) {
    }

    public String listarLivrosAtrasados() {
        return "";
    }

    public int getMatricula() {
        return this.matricula;
    }
}