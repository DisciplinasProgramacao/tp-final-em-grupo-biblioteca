import java.io.Serializable;
import java.time.LocalDate;

public class Livros implements Serializable {
    private String autor;
    private String editora;
    private String titulo;

    public Livros(String autor, String editora, String titulo) {
        this.autor = autor;
        this.editora = editora;
        this.titulo = titulo;
    }

    public String ToString() {
        return "";
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getAutor() {
        return this.autor;
    }

    public String getEditora() {
        return this.editora;
    }

    public void addEmprestimos() {
    }

    public void visualizar() {
    }

    public int getEmprestimos() {
        return 0;
    }

    public int getVisualizacao() {
        return 0;
    }

    public boolean verificarEmprestimo(Emprestimo emprestimo, LocalDate novaDataEmprestimo) {
        return true;
    }

}
