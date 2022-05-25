public class Professor extends Usuarios {

    public Professor(String novoNome) {
        super(novoNome);
    }

    public Professor(String novoNome, int matricula) {
        super(novoNome, matricula);
    }

    @Override
    public int getDiasDevolucao() {
        // TODO Auto-generated method stub
        return 14;
    }

    @Override
    public int getDiasSuspensao() {
        // TODO Auto-generated method stub
        return 0;
    }
}
