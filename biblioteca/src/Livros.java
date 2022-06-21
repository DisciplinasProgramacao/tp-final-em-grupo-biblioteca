import java.io.Serializable;

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
        return this.autor + "|" + this.editora + "|" + this.titulo;
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

    public boolean verificarEmprestimo() {
        return false;
    }

}
