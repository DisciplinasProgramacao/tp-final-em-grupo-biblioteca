import java.time.LocalDate;

public class Emprestimo {
    private Livros livro;
    private Usuarios usuario;
    private Data dataEmprestimo;
    private Data dataDevolucao;
    private Data dataPrevistaDevolucao;

    public Emprestimo(Usuarios novoUsuario, Livros novoLivro) {
        this.usuario = novoUsuario;
        this.livro = novoLivro;

        this.dataEmprestimo = new Data(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(),
                LocalDate.now().getYear());
    }

    public boolean saberAtraso() {
        return true;
    }

    public boolean punicao() {
        return true;
    }
}