public class AlunoGraduacao extends Alunos {
    private static int MAXLIVROS = 3;

    public AlunoGraduacao(String novoNome) {
        super(novoNome);
    }

    public AlunoGraduacao(String novoNome, int matricula) {
        super(novoNome, matricula);
    }

    @Override
    public int getMaxLivros() {
        // TODO Auto-generated method stub
        return MAXLIVROS;
    }

    @Override
    public String getCategoria() {
        return "Aluno de Graduação";
    }
}