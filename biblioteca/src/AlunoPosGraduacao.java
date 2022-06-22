public class AlunoPosGraduacao extends Alunos {

    private static int MAXLIVROS = 5;

    public AlunoPosGraduacao(String novoNome) {
        super(novoNome);
    }

    @Override
    public int getMaxLivros() {
        // TODO Auto-generated method stub
        return MAXLIVROS;
    }

    @Override
    public String ToString() {
        // TODO Auto-generated method stub
        return super.getNome() + " | " + super.getMatricula() + "Categoria: " + this.getCategoria();
    }

    private String getCategoria() {
        return "Aluno de Pós-Graduação";
    }
}