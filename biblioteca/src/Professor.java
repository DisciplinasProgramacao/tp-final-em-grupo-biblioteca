import java.util.LinkedList;

public class Professor extends Usuarios {
    private static int DEVOLUCAO_DIAS = 14;
    private static int MAXLIVROS = 7;

    public Professor(String novoNome) {
        super(novoNome);
    }

    public Professor(String novoNome, int matricula) {
        super(novoNome, matricula);
    }

    @Override
    public int getDiasDevolucao() {
        // TODO Auto-generated method stub
        return DEVOLUCAO_DIAS;
    }

    @Override
    public boolean verificarSuspensao() {
        LinkedList<Emprestimo> emprestimos = super.getEmprestimos();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getDataDevolucao().isAfter(emprestimo.getDataPrevistaDevolucao())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getMaxLivros() {
        // TODO Auto-generated method stub
        return MAXLIVROS;
    }

    @Override
    public String getCategoria() {
        return "Professor";
    }
}
