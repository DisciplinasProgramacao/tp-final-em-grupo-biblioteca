public class AlunoGraduacao extends Alunos {
    private static int MAXLIVROS = 3;

    public AlunoGraduacao(String novoNome) {
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
        return "Aluno de Graduação";
    }
}