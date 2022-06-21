public class LivrosFisicos extends Livros {

    private int emprestimos = 0;

    public LivrosFisicos(String autor, String editora, String titulo) {
        super(autor, editora, titulo);
    }

    public int getEmprestimos() {
        return this.emprestimos;
    }

    @Override
    public void addEmprestimos() {
        this.emprestimos++;
    }

}
