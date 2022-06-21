public class AlunoPosGraduacao extends Alunos {

    private static int MAXLIVROS = 5;

    public AlunoPosGraduacao(String novoNome) {
        super(novoNome);
    }

    public AlunoPosGraduacao(String novoNome, int matricula) {
        super(novoNome, matricula);
    }

    @Override
    public int getMaxLivros() {
        // TODO Auto-generated method stub
        return MAXLIVROS;
    }

    @Override
    public String getCategoria() {
        return "Aluno de Pós-Graduação";
    }
}