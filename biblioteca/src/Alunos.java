import java.time.Period;
import java.util.LinkedList;

public class Alunos extends Usuarios {
    private static int DEVOLUCAO_DIAS = 7;
    private static int SUSPENSAO_DIAS = 2;

    public Alunos(String novoNome) {
        super(novoNome);
    }

    @Override
    public int getDiasDevolucao() {
        // TODO Auto-generated method stub
        return DEVOLUCAO_DIAS;
    }

    public int getConstDiasSuspensao() {
        // TODO Auto-generated method stub
        return SUSPENSAO_DIAS;
    }

    @Override
    public int calculoDiasSuspensao() {
        int somaDias = 0;
        int meses = 0;
        // TODO Auto-generated method stub
        LinkedList<Emprestimo> emprestimos = super.getEmprestimos();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getDataDevolucao().isAfter(emprestimo.getDataPrevistaDevolucao())) {
                Period periodo = Period.between(emprestimo.getDataPrevistaDevolucao(), emprestimo.getDataDevolucao());
                if (periodo.getMonths() != 0) {
                    meses = periodo.getMonths() * 30;
                    somaDias += periodo.getDays() + meses;
                } else {
                    somaDias += periodo.getDays();
                }
            }
        }
        // return 2 * diasAtrazo;
        return somaDias * SUSPENSAO_DIAS;
    }

    @Override
    public boolean verificarSuspensao() {
        if (calculoDiasSuspensao() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int getMaxLivros() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String ToString() {
        // TODO Auto-generated method stub
        return "";
    }
}