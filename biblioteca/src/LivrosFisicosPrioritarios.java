public class LivrosFisicosPrioritarios extends LivrosFisicos {

    // private static final int QTDDIASRETENCAO = 3;
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
}
