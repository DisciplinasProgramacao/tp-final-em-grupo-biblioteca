public class Livros {
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

}
