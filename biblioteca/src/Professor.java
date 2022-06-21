public class Professor extends Usuarios {
    private static int DEVOLUCAO_DIAS = 14;
    private static int MAXLIVROS = 7;

    public Professor(String novoNome) {
        super(novoNome);
    }

    public Professor(String novoNome, int matricula) {
        super(novoNome, matricula);
    }

    public int getDiasDevolucao() {
        // TODO Auto-generated method stub
        return DEVOLUCAO_DIAS;
    }

    public int getMaxLivros() {
        // TODO Auto-generated method stub
        return MAXLIVROS;
    }
}
