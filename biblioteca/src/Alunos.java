public class Alunos extends Usuarios {
    private static int DEVOLUCAO_DIAS = 7;
    private static int SUSPENSAO_DIAS = 2;

    public Alunos(String novoNome) {
        super(novoNome);
    }

    public boolean suspensao() {
        return true;
    }
}