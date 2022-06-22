import java.time.LocalDate;

public class LivrosFisicos extends Livros {

    private int emprestimos = 0;

    public LivrosFisicos(String autor, String editora, String titulo) {
        super(autor, editora, titulo);
    }

    @Override
    public int getEmprestimos() {
        return this.emprestimos;
    }

    @Override
    public void addEmprestimos() {
        this.emprestimos++;
    }

    @Override
    public String ToString() {
        return super.getAutor() + "|" + super.getEditora() + "|" + super.getTitulo() + "  Tipo: Livro Fisico";
    }

    @Override
    public boolean verificarEmprestimo(Emprestimo emprestimo, LocalDate novaDataEmprestimo) {
        return true;
    }
}
