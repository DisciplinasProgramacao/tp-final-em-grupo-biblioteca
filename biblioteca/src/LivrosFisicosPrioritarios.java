import java.time.LocalDate;
import java.time.Period;

public class LivrosFisicosPrioritarios extends LivrosFisicos {

    private static final int QTDDIASRETENCAO = 3;
    private int emprestimos = 0;

    public LivrosFisicosPrioritarios(String autor, String editora, String titulo) {
        super(autor, editora, titulo);
    }

    @Override
    public int getEmprestimos() {
        return emprestimos;
    }

    @Override
    public void addEmprestimos() {
        this.emprestimos++;
    }

    @Override
    public String ToString() {
        return super.getAutor() + "|" + super.getEditora() + "|" + super.getTitulo()
                + "  Tipo: Livro Fisico prioritário";
    }

    @Override
    public boolean verificarEmprestimo(Emprestimo emprestimo, LocalDate novaDataEmprestimo) {
        Period periodo = Period.between(emprestimo.getDataDevolucao(), novaDataEmprestimo);
        if (periodo.getMonths() != 0 && periodo.getDays() >= QTDDIASRETENCAO
                && emprestimo.getDataDevolucao().equals(emprestimo.getDataPrevistaDevolucao())) {
            return true;
        }
        return false;
    }
}
